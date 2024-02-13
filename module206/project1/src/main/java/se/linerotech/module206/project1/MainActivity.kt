package se.linerotech.module206.project1

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : CountryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("P123","SHOW ACTIVITY 6")
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect(){
                    when(it) {
                        CountryUIState.Loading -> showProgressBar()
                        is CountryUIState.Loaded -> showCountry(it.countries)
                        CountryUIState.Failure -> showErrorMessage()
                    }

                }
            }
        }
    }

    private fun showErrorMessage() {
        Log.d("P123","SHOW ERROR")
    }

    private fun showCountry(countries: List<CountryData>) {
        Log.d("P123", "DANH SACH DAT NUOC ${countries.first()}")
    }

    private fun showProgressBar() {
        Log.d("P123","SHOW PROGRESS BAR")
    }
}
