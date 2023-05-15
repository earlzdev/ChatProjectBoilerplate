package com.earl.chatprojectboilerplate.presentation.signUp

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.chatprojectboilerplate.databinding.FragmentSignUpBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentSignUpBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentSignUpBinding.inflate(inflater, container, false)
}