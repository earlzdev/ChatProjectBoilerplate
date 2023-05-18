package com.earl.chatprojectboilerplate.presentation.auth.singIn

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentAuthCodeBinding
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.auth.AuthViewModel
import com.earl.chatprojectboilerplate.presentation.auth.TokenViewModel
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.CoroutinesErrorHandler
import com.earl.chatprojectboilerplate.presentation.utils.NavUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CheckAuthCodeFragment: BaseFragment<FragmentAuthCodeBinding>() {

    private val viewModel: AuthViewModel by activityViewModels()
    private val tokenViewModel: TokenViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentAuthCodeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAccessTokens()
        binding.signInBtn.setOnClickListener {
            if (binding.coeEd.text.isNotEmpty()) {
                viewModel.login(getPhoneNumber(), binding.coeEd.text.toString(), object: CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        Toast.makeText(requireContext(), "Error! $message", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                binding.coeEd.error = getString(R.string.filed_cannot_be_empty)
            }
        }
    }

    private fun getPhoneNumber() =  arguments?.let { it.getString(getString(R.string.phone_number_str)) } ?: ""

    private fun initAccessTokens() {
        viewModel.accessTokens.onEach {
            when(it) {
                is ApiResponse.Failure -> {
                    Toast.makeText(requireContext(), "Failed, ${it.errorMessage}", Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Success -> {
                    tokenViewModel.saveTokens(it.data)
                    requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(
                        Uri.parse(
                            NavUri.authGraphToBottomNavFragment))
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}