package se.linerotech.module206.project1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.IntentCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import se.linerotech.module206.project1.common.CountryData
import se.linerotech.module206.project1.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if(intent.hasExtra(KEY)) {
            val countryData = IntentCompat.getParcelableExtra(intent, KEY, CountryData::class.java)
            if (countryData != null) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    LatLng(countryData.lat, countryData.long),3.0f
                ))
                val markerOptions = MarkerOptions()
                    .position(LatLng(countryData.lat, countryData.long))
                    .title(countryData.country)
                googleMap.addMarker(markerOptions)
            }
        }

    }

    companion object {
        private const val KEY = "DATA KEY"
        fun intent(context: Context, countryData: CountryData?) :Intent {
            val intent = Intent(context, MapsActivity::class.java)
            if(countryData != null) {
                intent.putExtra(KEY,countryData )
            }
            return intent
        }

    }
}