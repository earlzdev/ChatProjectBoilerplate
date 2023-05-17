package com.earl.chatprojectboilerplate.presentation.auth.singIn

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentSignInBinding
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.auth.AuthViewModel
import com.earl.chatprojectboilerplate.presentation.auth.TokenViewModel
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.NavUri
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    private val viewModel: AuthViewModel by activityViewModels()
    private val tokenViewModel: TokenViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignInBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCurrentRegionPhoneCodeAndFlag()
        initSuccessfulAuthCodeRequest()
        binding.signInBtn.setOnClickListener {
            viewModel.fetchAuthRequestCode(binding.phoneEditText.text.trim().toString())
        }
    }

    private fun initCurrentRegionPhoneCodeAndFlag() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentCountryPhoneNumber
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it is ApiResponse.Success) {
                        binding.chooseCountryFlagAndCodeView.initFlagAndCode(it.data)
                    }
                }
        }
    }

    private fun initSuccessfulAuthCodeRequest() {
        viewModel.successfulAuthCode.onEach {
            if (it) {
                findNavController().navigate(R.id.action_signInFragment_to_checkAuthCodeFragment)
            }
        }.launchIn(lifecycleScope)
        viewModel.accessTokens.onEach {
            when(it) {
                is ApiResponse.Failure -> {
                    log("fail ${it.errorMessage}, ${it.code}")
                }
                is ApiResponse.Success -> {
                    tokenViewModel.saveTokens(it.data)
                    log("success ${it.data}")
                    requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(Uri.parse(NavUri.authGraphToBottomNavFragment))
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}