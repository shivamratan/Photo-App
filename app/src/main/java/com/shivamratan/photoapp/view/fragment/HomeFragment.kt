package com.shivamratan.photoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.shivamratan.photoapp.R
import com.shivamratan.photoapp.databinding.FragmentHomeBinding
import com.shivamratan.photoapp.view.base.BaseFragment

class HomeFragment : BaseFragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(binding.root.findViewById(R.id.homeFragmentContainer))
        NavigationUI.setupWithNavController(binding.bnvHome, navController)

    }
}