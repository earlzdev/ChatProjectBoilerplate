package com.earl.chatprojectboilerplate.presentation.chat

import androidx.lifecycle.ViewModel
import com.earl.chatprojectboilerplate.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
}