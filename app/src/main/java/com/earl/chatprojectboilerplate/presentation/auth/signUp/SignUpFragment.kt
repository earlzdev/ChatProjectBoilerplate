package com.earl.chatprojectboilerplate.presentation.auth.signUp

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentSignUpBinding
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
class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: AuthViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignUpBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initAccessTokens()
        binding.signUpBtn.setOnClickListener {
            if (binding.enterNameEd.text.length <= 5) {
                binding.enterNameEd.error = getString(R.string.too_short_name)
            } else if (binding.enterUsernameEd.text.length <= 5) {
                binding.enterUsernameEd.error = getString(R.string.too_short_username)
            } else {
                viewModel.register(
                    getPhone(),
                    binding.enterNameEd.text.toString(),
                    binding.enterUsernameEd.text.toString(),
                    object : CoroutinesErrorHandler {
                        override fun onError(message: String) {
                            Toast.makeText(requireContext(), "Error! $message", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            }
        }
    }

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

    private fun initViews() {
        binding.phoneNumberTv.text = getPhone()
    }

    private fun getPhone() = arguments?.let { it.getString("phone_number") } ?: ""
}