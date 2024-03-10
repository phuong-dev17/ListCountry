package se.linerotech.module206.project1.onboarding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import se.linerotech.module206.project1.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val viewmodel: OnboardingViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showOnboardingScreen()
        showPageIndicator()
    }

    private fun showPageIndicator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {_, _ ->}.attach()
    }

    private fun showOnboardingScreen() {
        val adapter = OnboardingViewPageAdapter(activity = this, viewmodel)
        binding.viewPager.adapter = adapter
    }
}