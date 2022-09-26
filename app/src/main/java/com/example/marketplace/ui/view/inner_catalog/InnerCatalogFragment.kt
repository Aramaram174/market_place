package com.example.marketplace.ui.view.inner_catalog

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.data.db.model.Category
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.InnerCatalogFragmentBinding
import com.example.marketplace.ui.view.adapter.ProductAdapter
import com.example.marketplace.ui.view.sort.SortAdapter
import com.example.marketplace.ui.view.sort.SortType
import com.example.marketplace.utils.GridSpacingItemDecoration
import com.example.marketplace.utils.adapter.EndlessScrollListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.ui.view.filter.FilterFragment
import com.example.marketplace.ui.view.filter.FilterFragment.Companion.FRAGMENT_REQUEST_KEY


class InnerCatalogFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = InnerCatalogFragment()
    }

    private val viewModel: InnerCatalogViewModel by viewModel()
    private val args: InnerCatalogFragmentArgs by navArgs()
    private var _binding: InnerCatalogFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private var isLoading: Boolean = false
    private var products = mutableListOf<Product>()
    private val limitForGet = "20"
    private var itemsCount: Int = 0

    lateinit var adapter: SortAdapter
    lateinit var listView: ListView
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    private var result : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(FRAGMENT_REQUEST_KEY) { requestKey, bundle ->
            if(requestKey == FRAGMENT_REQUEST_KEY) result = bundle.getString("bundleKey")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InnerCatalogFragmentBinding.inflate(inflater, container, false)

        initToolbar()
        setupRecyclerView()
        observeOnInnerCatalogUpdate()
        getProductByCategory(args.category, "", "")
        return binding.root
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
        setToolbarTitle(args.category.name)

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.filter -> findNavController().navigate(InnerCatalogFragmentDirections.actionInnerCatalogFragmentToFilterFragment())
                R.id.sort -> { showDialog()}
            }
            true
        }
    }

    private fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.sort_view)

        val listSortType = listOf(SortType.ALPHABET, SortType.PRICE_MIN, SortType.PRICE_MAX, SortType.SALE, SortType.UPDATE, SortType.WHATS_NEW)
        adapter = SortAdapter(listSortType, onItemClick = {
            dialog.dismiss()
            getSortedProductsByCategory(args.category, it, limitForGet, productAdapter.itemCount.toString())
        })
        val recyclerView = dialog.findViewById(R.id.rv_sort) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        dialog.show()
    }

    private fun setToolbarTitle(title: String?) {
        binding.toolbar.title = title
    }

    private fun setupRecyclerView() {
        binding.innerCatalogRecyclerView.apply {
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
            productAdapter = ProductAdapter(onItemClick = {
                findNavController().navigate(
                    InnerCatalogFragmentDirections.actionInnerCatalogFragmentToProductFragment(
                        product = it
                    )
                )
            })
            adapter = productAdapter
            loadData()
        }
    }

    private fun observeOnInnerCatalogUpdate() {
        viewModel.productsLiveData().observe(viewLifecycleOwner, { productResponse ->
            updateDataList(productResponse.results)
            itemsCount = productResponse.count
        })
    }

    private fun getProductByCategory(category: Category, limit: String, offset: String) {
        viewModel.getProductsByCategory(category.id.toString(), limit, offset)
    }

    private fun getSortedProductsByCategory(category: Category, sortType: SortType, limit: String, offset: String) {
        viewModel.getSortedFilteredProductsByCategory(category.id.toString(), sortType, limit, offset)
    }

    fun loadData() {
        binding.llLoader.visibility = View.VISIBLE
        val sortType = SortType.SALE //Todo change
        when(sortType){
            SortType.RATING -> getProductByCategory(args.category, limitForGet, productAdapter.itemCount.toString())
            SortType.PRICE_MAX -> getSortedProductsByCategory(args.category, sortType, limitForGet, productAdapter.itemCount.toString())
        }
    }

    private fun updateDataList(newList: List<Product>) {
        val tempList = products.toMutableList()
        tempList.addAll(newList)
        productAdapter.differ.submitList(tempList)
        products = tempList
        isLoading = false
        binding.llLoader.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}