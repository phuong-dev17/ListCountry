package se.linerotech.module206.project1.onboarding

import androidx.lifecycle.ViewModel
import se.linerotech.module206.project1.R

class OnboardingViewmodel : ViewModel() {
    private val _onboardingData: List<OnboardingData> = listOf(
        OnboardingData(
            title = R.string.onboarding1,
            isGetStartedShown = false
        ),
        OnboardingData(
            title = R.string.onboarding2,
            isGetStartedShown = false
        ),
        OnboardingData(
            title = R.string.onboarding3,
            isGetStartedShown = true
        )
    )

    val onboardingData = _onboardingData
}