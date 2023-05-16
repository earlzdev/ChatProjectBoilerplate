package com.earl.chatprojectboilerplate.presentation.viewgroups

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.ViewgroupCountryFlagAndCodeBinding
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode

class ChooseCountryPhoneCodeView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
): ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context): this(context, null)

    private val binding: ViewgroupCountryFlagAndCodeBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.viewgroup_country_flag_and_code, this, true)
        binding = ViewgroupCountryFlagAndCodeBinding.bind(this)
    }

    fun initFlagAndCode(value: CurrentCountryCode) {
        Glide.with(binding.flagImage).load(value.flag).into(binding.flagImage)
        binding.countryCodeEd.setText("+${value.code}")
    }
}