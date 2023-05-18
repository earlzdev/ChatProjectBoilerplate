package com.earl.chatprojectboilerplate.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.earl.chatprojectboilerplate.databinding.FragmentChatBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment: BaseFragment<FragmentChatBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentChatBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}