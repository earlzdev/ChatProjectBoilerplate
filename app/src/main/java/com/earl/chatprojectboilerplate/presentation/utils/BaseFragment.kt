package com.earl.chatprojectboilerplate.presentation.utils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) : VB

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected lateinit var preferenceManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        preferenceManager = SharedPreferenceManager(requireContext())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}