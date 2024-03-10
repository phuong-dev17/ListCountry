package se.linerotech.module206.project1.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingViewPageAdapter(
    activity: FragmentActivity,
    private val viewmodel: OnboardingViewmodel
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return viewmodel.onboardingData.size
    }

    override fun createFragment(position: Int): Fragment {
       return OnboardingFragment.instance(viewmodel.onboardingData[position])
    }

}