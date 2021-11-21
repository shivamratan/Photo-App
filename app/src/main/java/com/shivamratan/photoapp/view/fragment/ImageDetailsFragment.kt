package com.shivamratan.photoapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.shivamratan.photoapp.databinding.FragmentImageDetailsBinding
import com.shivamratan.photoapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageDetailsFragment : BaseFragment() {

    lateinit var binding: FragmentImageDetailsBinding

    private val args: ImageDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()

        val url = args.imageurl
        if(url?.isNullOrEmpty()?.not() == true) {
            Log.e("DETails", url)
            requestManager.load(url).into(binding.ivImageDetail)
        }
    }

    private fun setOnClickListener() {
        binding.btnBackDetails.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}