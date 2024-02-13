package se.linerotech.module206.project1

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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

    }

    private fun showCountry(countries: List<CountryData>) {
    binding.recyclerView.adapter = CountryRecyclerViewAdapter(countries)
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
    }

    private fun showProgressBar() {
    }
}
