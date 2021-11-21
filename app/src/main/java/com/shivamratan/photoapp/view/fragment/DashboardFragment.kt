package com.shivamratan.photoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.shivamratan.photoapp.R
import com.shivamratan.photoapp.utils.setInvisible
import com.shivamratan.photoapp.utils.setVisible
import com.shivamratan.photoapp.data.response.PhotoItem
import com.shivamratan.photoapp.databinding.FragmentDashboardBinding
import com.shivamratan.photoapp.utils.DebounceQueryTextListener
import com.shivamratan.photoapp.utils.showLongToast
import com.shivamratan.photoapp.view.adapter.ErrorLoadingAdapter
import com.shivamratan.photoapp.view.adapter.PhotoListAdapter
import com.shivamratan.photoapp.view.base.BaseFragment
import com.shivamratan.photoapp.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : BaseFragment() {

    private lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var photoAdapter: PhotoListAdapter
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setUpRecyclerView()
        loadPhotoListData()
    }

    private fun setListeners() {
        binding.searchViewDashboard.setOnQueryTextListener(object: DebounceQueryTextListener(){
            override fun onQueryDebounce(newText: String?) {
                newText?.let{
                    if(it.isNotEmpty())
                        loadPhotoListData(it)
                    else
                        loadPhotoListData()
                }
            }
        })
    }

    private fun loadPhotoListData(tag: String ="\"\"") {
        lifecycleScope.launchWhenStarted {
            viewModel.getPhotoList(tag).collectLatest { response ->
                    binding.progressBar.setInvisible()
                    binding.rvPhotoList.setVisible()
                    binding.searchViewDashboard.setVisible()
                photoAdapter.submitData(response)
            }

            photoAdapter.loadStateFlow.collectLatest { loadStates ->
                if(loadStates.refresh is LoadState.Error)
                    requireContext().showLongToast("Something went wrong!")

            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvPhotoList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = photoAdapter.apply {
                onItemClick = {
                    redirectToImageDetails(it)
                }

                onFavouritePhotoItem = {item, imageView ->
                    onPhotoItemFavourite(item, imageView)
                }
            }.withLoadStateHeaderAndFooter(
                header = ErrorLoadingAdapter{ photoAdapter.retry() },
                footer = ErrorLoadingAdapter{ photoAdapter.retry() }
            )
        }
    }

    private fun onPhotoItemFavourite(photoItem: PhotoItem, imageView: AppCompatImageButton) {
        val flag = photoItem.isFavourite.not()
        photoItem.isFavourite = flag

        if(flag) {
            imageView.setImageResource(R.drawable.ic_favorite_photo)
            viewModel.saveFavourites(photoItem)
        } else {
            imageView.setImageResource(R.drawable.ic_unfavourite_photo)
            viewModel.deleteFavourites(photoItem)
        }
    }

    private fun redirectToImageDetails(urlStr: String) {
        val fragment = requireActivity().findViewById<View>(R.id.mainFragmentContainer)
        val navController = Navigation.findNavController(fragment)
        val directions = HomeFragmentDirections.actionHomeFragmentToImageDetailsFragment(urlStr)
        navController.navigate(directions)
    }

}