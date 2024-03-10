package se.linerotech.module206.project1.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import se.linerotech.module206.project1.MainActivity
import se.linerotech.module206.project1.R
import se.linerotech.module206.project1.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
   private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onboardingData: OnboardingData

    companion object {
        const val DATA_KEY = "DATA_KEY"
        fun instance(data: OnboardingData): OnboardingFragment {
            val fragment = OnboardingFragment()
            val bundle = Bundle()
            bundle.putParcelable(
                OnboardingFragment.DATA_KEY,
                data
            )
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            onboardingData = BundleCompat.getParcelable(it, DATA_KEY, OnboardingData::class.java)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.txtOnboarding.text = requireContext().getString(onboardingData.title) ?: ""
        if (onboardingData.isGetStartedShown) {
            Log.d("P123", "get started shown")
            binding.btnGetStarted.visibility = View.VISIBLE
            binding.btnGetStarted.setOnClickListener {
                Log.d("P123", "START MAIN ACTIVITY")
                activity?.startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }

        }  else {
            binding.btnGetStarted.visibility = View.GONE
        }
    }
}