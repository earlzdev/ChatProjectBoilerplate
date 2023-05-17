package com.earl.chatprojectboilerplate.presentation.auth.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.earl.chatprojectboilerplate.databinding.FragmentAuthCodeBinding
import com.earl.chatprojectboilerplate.presentation.auth.AuthViewModel
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.CoroutinesErrorHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckAuthCodeFragment: BaseFragment<FragmentAuthCodeBinding>() {

    private val viewModel: AuthViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentAuthCodeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInBtn.setOnClickListener {
            viewModel.login("", "133337", object: CoroutinesErrorHandler {
                override fun onError(message: String) {
                    Toast.makeText(requireContext(), "Error! $message", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}