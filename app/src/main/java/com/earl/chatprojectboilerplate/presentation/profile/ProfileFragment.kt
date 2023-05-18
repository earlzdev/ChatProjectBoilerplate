package com.earl.chatprojectboilerplate.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.earl.chatprojectboilerplate.databinding.FragmentProfileBinding
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewModel.userProfileInfo.onEach {
            when(it) {
                is ApiResponse.Failure -> {
                    Toast.makeText(requireContext(), "Failed, ${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    log("failed -> ${it.errorMessage} ${it.code}")
                }
                is ApiResponse.Success -> {
                    log("it -> ${it.data}")
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }
}