package se.linerotech.module206.project1.onboarding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnboardingData(
    val title: Int,
    val isGetStartedShown : Boolean
) : Parcelable
