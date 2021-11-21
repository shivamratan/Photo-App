package com.shivamratan.photoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.shivamratan.photoapp.R
import com.shivamratan.photoapp.databinding.FragmentFavouriteBinding
import com.shivamratan.photoapp.db.entity.FavouritesEntity
import com.shivamratan.photoapp.view.adapter.FavouritePhotoAdapter
import com.shivamratan.photoapp.view.base.BaseFragment
import com.shivamratan.photoapp.viewmodel.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment() {

    lateinit var binding: FragmentFavouriteBinding

    @Inject
    lateinit var favoriteAdapter: FavouritePhotoAdapter

    val viewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
         binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeFavouriteList()
        loadFavouritePhotos()
    }

    private fun observeFavouriteList() {
        viewModel.favouriteLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.favouritePhotoList.clear()
            if(it.isNullOrEmpty().not()) {
                viewModel.favouritePhotoList.addAll(it)
                favoriteAdapter.updateFavouriteList(viewModel.favouritePhotoList)
            }
        })
    }

    private fun loadFavouritePhotos() {
        viewModel.getAllFavourites()
    }

    private fun setUpRecyclerView() {
        binding.favRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = favoriteAdapter.apply {
                onItemClick = {
                    redirectToImageDetails(it)
                }

                onFavouritePhotoItem = {
                    onPhotoItemUnFavourite(it)
                }
            }
        }
    }

    private fun onPhotoItemUnFavourite(photoItem: FavouritesEntity) {
        viewModel.favouritePhotoList.remove(photoItem)
        viewModel.deleteFavourite(photoItem)
        favoriteAdapter.notifyDataSetChanged()
    }

    private fun redirectToImageDetails(urlStr: String) {
        val fragment = requireActivity().findViewById<View>(R.id.mainFragmentContainer)
        val navController = Navigation.findNavController(fragment)
        val directions = HomeFragmentDirections.actionHomeFragmentToImageDetailsFragment(urlStr)
        navController.navigate(directions)
    }
}