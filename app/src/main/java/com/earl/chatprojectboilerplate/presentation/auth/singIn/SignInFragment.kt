package com.earl.chatprojectboilerplate.presentation.auth.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentSignInBinding
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.auth.AuthViewModel
import com.earl.chatprojectboilerplate.presentation.auth.TokenViewModel
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    private val viewModel: AuthViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignInBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCurrentRegionPhoneCodeAndFlag()
        initSuccessfulAuthCodeRequest()
        binding.signInBtn.setOnClickListener {
            viewModel.fetchAuthRequestCode(binding.phoneNumber.getFullPhoneNumber())
        }
    }

    private fun initCurrentRegionPhoneCodeAndFlag() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCountryPhoneNumber
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it is ApiResponse.Success) {
                        binding.phoneNumber.setDefaultCountry(it.data.code)
                    }
                }
        }
    }

    private fun initSuccessfulAuthCodeRequest() {
        viewModel.successfulAuthCode.onEach {
            if (it) {
                val bundle = bundleOf("phone_number" to binding.phoneNumber.getFullPhoneNumber())
                findNavController().navigate(R.id.action_signInFragment_to_checkAuthCodeFragment, bundle)
            }
        }.launchIn(lifecycleScope)
    }
}