package com.earl.chatprojectboilerplate.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

//    val userProfileInfo = flow {
//        try {
//         emit(repository.fetchUserProfileData(""))
//        } catch (e: Exception) {
//            log("e -> $e")
//        }
//    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun fetchUserProfileInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchUserProfileData("")
        }
    }
}