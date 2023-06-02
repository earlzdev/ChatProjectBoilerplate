package com.earl.chatprojectboilerplate.presentation.profile

import android.os.Bundle
import android.os.DropBoxManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentProfileBinding
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.DbResponse
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.earl.chatprojectboilerplate.presentation.utils.BitmapFromStringDecoder
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentProfileBinding.inflate(inflater, container, false)

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
                            binding.userInfo.visibility = View.GONE
                            binding.progressbar.visibility = View.VISIBLE
                        }
                        is DbResponse.Success -> {
                            log("it -> ${it.value}")
                            binding.city.text = String.format(getString(R.string.city_s), it.value.city.takeIf { it != "" } ?: "No data")
                            binding.username.text = String.format(getString(R.string.e), it.value.username.takeIf { it != "" } ?: "No data")
                            binding.phone.text = String.format(getString(R.string.phone_s), it.value.phone.takeIf { it != "" } ?: "No data")
                            binding.birthday.text = String.format(getString(R.string.birthday), it.value.birthday.takeIf { it != "" } ?: "No data")
                            binding.progressbar.visibility = View.GONE
                            binding.userInfo.visibility = View.VISIBLE
                            if (it.value.avatar != "") {
                                binding.userAvatar.setImageBitmap(BitmapFromStringDecoder().decode(it.value.avatar))
                            }
                        }
                        is DbResponse.Fail -> {
                            Toast.makeText(requireContext(), "Failed, ${it.error}", Toast.LENGTH_SHORT).show()
                            log("failed -> ${it.error} ${it.error}")
                        }
                    }
                }
        }
        binding.editBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }
}