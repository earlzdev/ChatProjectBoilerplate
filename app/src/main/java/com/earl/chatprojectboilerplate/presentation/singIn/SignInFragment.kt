package com.earl.chatprojectboilerplate.presentation.singIn

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.chatprojectboilerplate.databinding.FragmentSignInBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignInBinding.inflate(inflater, container, false)
}