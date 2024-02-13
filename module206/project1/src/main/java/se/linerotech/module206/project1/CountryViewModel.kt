package se.linerotech.module206.project1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.dto.CountryDTOItem
import se.linerotech.module206.project1.network.RetrofitClient
import java.io.IOException

class CountryViewModel : ViewModel() {
    private val _state = MutableStateFlow<CountryUIState>(CountryUIState.Loading)
    val state: StateFlow<CountryUIState> = _state

    init {
        Log.d("P123", " CALLING INIT")
        getAllCountry()
    }

    private fun getAllCountry() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RetrofitClient.countryApiService.all()
                val dataCountry = convertToCountryData(result)

                _state.value = CountryUIState.Loaded(dataCountry)
            } catch (error: IOException) {
                _state.value = CountryUIState.Failure
                throw error
            } catch (error: HttpException) {
                _state.value = CountryUIState.Failure
                throw error
            }
        }
    }

    private fun convertToCountryData(result: List<CountryDTOItem>): List<CountryData> {
        return result.map {
            CountryData(
                it.name.common,
                it.languages?.toList()?.first()?.second ?: "",
                it.flags.png
            )
        }.sortedBy { it.country.first() }
    }
}
