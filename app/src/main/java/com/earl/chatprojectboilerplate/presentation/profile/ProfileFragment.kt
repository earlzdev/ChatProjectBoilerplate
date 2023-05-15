package com.earl.chatprojectboilerplate.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.chatprojectboilerplate.databinding.FragmentProfileBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment

class ProfileFragment: BaseFragment<FragmentProfileBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentProfileBinding.inflate(inflater, container, false)
}