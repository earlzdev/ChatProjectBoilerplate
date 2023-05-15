package com.earl.chatprojectboilerplate.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.chatprojectboilerplate.databinding.FragmentChatBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentChatBinding.inflate(inflater, container, false)
}