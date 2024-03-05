package se.linerotech.module206.project1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.IntentCompat
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.ActivityCountryDetailBinding

class CountryDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCountryDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCountryData()


        binding.apply {
            countryName.text = getCountryData().country
            language.text = getCountryData().language
            flag.text = getCountryData().flagUrl
            population.text = getCountryData().population.toString()
            capital.text = getCountryData().area.toString()
        }
    }

    private fun getCountryData() : CountryData {
        val countryData = IntentCompat.getParcelableExtra(intent, KEY, CountryData::class.java)
        return countryData!!
    }
    companion object {
        private const val KEY = "DATA KEY"
        @SuppressLint("SuspiciousIndentation")
        fun intent(context: Context, countryData: CountryData?) : Intent {
            val intent = Intent(context, CountryDetailActivity::class.java)
                intent.putExtra(KEY,countryData )
            return intent
        }

    }
}