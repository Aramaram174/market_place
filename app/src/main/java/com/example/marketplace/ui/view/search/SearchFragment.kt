package com.example.marketplace.ui.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.marketplace.R
import com.example.marketplace.data.db.model.Category
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.SearchFragmentBinding
import com.example.marketplace.ui.view.adapter.ProductAdapter
import com.example.marketplace.ui.view.inner_catalog.InnerCatalogFragmentDirections
import com.example.marketplace.utils.GridSpacingItemDecoration
import com.example.marketplace.utils.adapter.EndlessScrollListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class SearchFragment : Fragment(), CoroutineScope {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(R.id.fakeCatalogFragmentContainerView))
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private val args: SearchFragmentArgs by navArgs()
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private var isLoading: Boolean = false
    private var products = mutableListOf<Product>()
    private val limitForGet = "20"
    private var itemsCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().navigateUp() }

        initToolbar()
        initArgs(args.textSearch)
        setupRecyclerView()
        observeOnSearchUpdate()
        getSearchedProduct(args.textSearch, "", "")
        return binding.root
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(),
            appBarConfiguration
        )
    }

    private fun setupRecyclerView() {
        binding.rvSearch.apply {
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = gridLayoutManager
            addItemDecoration(GridSpacingItemDecoration(2, 50, true))
            itemAnimator = null
            addOnScrollListener(object : EndlessScrollListener(layoutManager as GridLayoutManager){
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    if (adapter?.itemCount!! < itemsCount ) loadData()
                    isLoading = true
                }
            })
            productAdapter = ProductAdapter(onItemClick =  {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(product = it))
            })
            adapter = productAdapter
        }
    }

    private fun getSearchedProduct(textSearch: String, limit: String, offset: String) {
        viewModel.getSearchedProduct(textSearch, limit, offset)
    }

    fun loadData() {
//        binding.llLoader.visibility = View.VISIBLE
        getSearchedProduct(args.textSearch, limitForGet, productAdapter.itemCount.toString())
    }

    private fun updateDataList(newList: List<Product>) {
        val tempList = products.toMutableList()
        tempList.addAll(newList)
        productAdapter.differ.submitList(tempList)
        products = tempList
        isLoading = false
//        binding.llLoader.visibility = View.GONE
    }

    private fun initArgs(searchText: String){
        setToolbarTitle(searchText)
    }

    private fun setToolbarTitle(title: String?){
        binding.toolbar.title = title
    }

    private fun observeOnSearchUpdate(){
        viewModel.productsLiveData()
            .observe(viewLifecycleOwner, { searchedProducts ->
                updateDataList(searchedProducts.results)
                itemsCount = searchedProducts.count
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}