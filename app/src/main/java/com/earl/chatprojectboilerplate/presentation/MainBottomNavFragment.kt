package com.earl.chatprojectboilerplate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.earl.chatprojectboilerplate.R
import com.earl.chatprojectboilerplate.databinding.FragmentBottomNavBinding
import com.earl.chatprojectboilerplate.presentation.utils.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainBottomNavFragment: BaseFragment<FragmentBottomNavBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = FragmentBottomNavBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHostFragment.findNavController()
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)
    }
}