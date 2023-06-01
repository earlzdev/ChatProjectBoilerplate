package com.earl.chatprojectboilerplate.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentEditUserProfileBinding
import com.earl.chatprojectboilerplate.domain.models.DbResponse
import com.earl.chatprojectboilerplate.domain.models.UpdateAvatarModel
import com.earl.chatprojectboilerplate.domain.models.UpdateUserProfileModel
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<FragmentEditUserProfileBinding>() {

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentEditUserProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userProfileInfo
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when(it) {
                        is DbResponse.Loading -> {
                            log("user info loading")
                        }
                        is DbResponse.Success -> {
                            log("it edit -> ${it.value}")
                            binding.birthday.setText(it.value.birthday)
                            binding.city.setText(it.value.city)
                            binding.about.setText(it.value.about)
                            binding.vk.setText(it.value.vk)
                            binding.inst.setText(it.value.inst)
                            if (it.value.avatar != "") {
                                Glide.with(binding.userAvatar).load(it.value.avatar).into(binding.userAvatar)
                            }
                        }
                        is DbResponse.Fail -> {
                            Toast.makeText(requireContext(), "Failed, ${it.error}", Toast.LENGTH_SHORT).show()
                            log("failed -> ${it.error} ${it.error}")
                        }
                    }
                }
        }
        binding.saveBtn.setOnClickListener {
            viewModel.updateUserAvatarModel(
                UpdateUserProfileModel(
                    "",
                    "",
                    binding.birthday.text.toString(),
                    binding.city.text.toString(),
                    binding.vk.text.toString(),
                    binding.inst.text.toString(),
                    binding.about.text.toString(),
                    UpdateAvatarModel(
                        "", ""
                    )
                )
            )
            findNavController().popBackStack()
        }
    }
}