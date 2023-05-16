package com.earl.chatprojectboilerplate.presentation.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.earl.chatprojectboilerplate.databinding.FragmentAuthCodeBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckAuthCodeFragment: BaseFragment<FragmentAuthCodeBinding>() {

    private val viewModel: SignInViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentAuthCodeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInBtn.setOnClickListener {
            viewModel.checkAuthCode("", "133337")
        }
    }
}