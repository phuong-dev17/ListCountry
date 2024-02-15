package se.linerotech.module206.project1

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureActionBar()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect() {
                    when (it) {
                        CountryUIState.Loading -> showProgressBar()
                        is CountryUIState.Loaded -> showCountry(it.countries)
                        CountryUIState.Failure -> showErrorMessage()
                    }
                }
            }
        }
    }

    private fun configureActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("P123", "Create Option Menu")
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("P123", "Starting Map")
        startActivity(MapsActivity.intent(this, null))
        return true
    }

    private fun showErrorMessage() {
        // show error message
    }

    private fun showCountry(countries: List<CountryData>) {
        binding.recyclerView.adapter = CountryRecyclerViewAdapter(countries, ::showCountrysMap)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun showCountrysMap(countryData: CountryData) {
        startActivity(MapsActivity.intent(this,countryData))
    }

    private fun showProgressBar() {
        // show progress bar
    }
}
