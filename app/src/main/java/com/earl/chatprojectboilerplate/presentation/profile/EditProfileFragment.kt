package com.earl.chatprojectboilerplate.presentation.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.earl.chatprojectboilerplate.presentation.utils.BitmapFromStringDecoder
import com.earl.chatprojectboilerplate.presentation.utils.DateValidator
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<FragmentEditUserProfileBinding>() {

    private val viewModel: ProfileViewModel by activityViewModels()
    private var encodedImage: String? = null
    private var userName: String = ""
    private var userUsername: String = ""
    private var userAvatar: String = ""

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
                        is DbResponse.Loading -> {}
                        is DbResponse.Success -> {
                            userName = it.value.name
                            userUsername = it.value.username
                            binding.birthday.setText(it.value.birthday)
                            binding.city.setText(it.value.city)
                            binding.about.setText(it.value.about)
                            binding.vk.setText(it.value.vk)
                            binding.inst.setText(it.value.inst)
                            if (it.value.avatar != "") {
                                binding.userAvatar.setImageBitmap(BitmapFromStringDecoder().decode(it.value.avatar))
                                userAvatar = it.value.avatar
                            }
                        }
                        is DbResponse.Fail -> {
                            Toast.makeText(requireContext(), "Failed, ${it.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
        binding.saveBtn.setOnClickListener {
            val dateValidator = DateValidator.Base(binding.birthday)
            if (dateValidator.validate()) {
                viewModel.updateUserAvatarModel(
                    UpdateUserProfileModel(
                        userName,
                        userUsername,
                        binding.birthday.text.toString(),
                        binding.city.text.toString(),
                        binding.vk.text.toString(),
                        binding.inst.text.toString(),
                        binding.about.text.toString(),
                        UpdateAvatarModel(
                            "file", encodedImage ?: userAvatar
                        )
                    )
                )
                findNavController().popBackStack()
            }
        }
        binding.addNewAvatarBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val previewWidth = 150
        val previewHeight = bitmap.height * previewWidth / bitmap.width
        val previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream = ByteArrayOutputStream()
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val imageUri = result.data!!.data
                try {
                    val inputStream =
                        requireContext().contentResolver.openInputStream(
                            imageUri!!
                        )
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.userAvatar.setImageBitmap(bitmap)
                    encodedImage = encodeImage(bitmap)
                } catch (exception: FileNotFoundException) {
                    exception.printStackTrace()
                }
            }
        }
    }

}