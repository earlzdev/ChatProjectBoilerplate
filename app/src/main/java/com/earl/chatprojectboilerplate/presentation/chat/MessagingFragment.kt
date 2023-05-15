package com.earl.chatprojectboilerplate.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.chatprojectboilerplate.databinding.FragmentMessagingBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment

class MessagingFragment: BaseFragment<FragmentMessagingBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentMessagingBinding.inflate(inflater, container, false)
}