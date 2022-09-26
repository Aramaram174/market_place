package com.example.marketplace.ui.view.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.data.db.model.HomeData
import com.example.marketplace.databinding.HomeFragmentBinding
import com.example.marketplace.ui.view.catalog.CatalogFragmentDirections
import com.example.marketplace.ui.view.home.adapter.HomeMultipleContentTypeAdapter
import com.example.marketplace.utils.hideSoftInput
import com.example.marketplace.utils.test.homeList
import com.example.marketplace.utils.test.imageCircleImageSlider
import com.example.marketplace.utils.test.imageImageSliderItem
import com.example.marketplace.utils.test.imageUrlList2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class HomeFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeMultipleContentTypeAdapter: HomeMultipleContentTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        return binding.root
    }

    private fun initViews() {
        initSearchView()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.homeRecyclerView.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            homeMultipleContentTypeAdapter = HomeMultipleContentTypeAdapter()
            homeMultipleContentTypeAdapter.setData(homeList)
            adapter = homeMultipleContentTypeAdapter
        }
    }

    private fun initSearchView() {
        binding.search.queryHint = getString(R.string.search)
        binding.search.onActionViewExpanded()
        binding.search.isSubmitButtonEnabled = true
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (TextUtils.isEmpty(query)) return false
                binding.search.clearFocus()
                navigateToSearchFragment(query)
                hideSoftInput(requireContext())
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun navigateToSearchFragment(query: String){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment(query))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}