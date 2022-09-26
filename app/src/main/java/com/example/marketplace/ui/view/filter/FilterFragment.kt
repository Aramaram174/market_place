package com.example.marketplace.ui.view.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.data.db.model.Filter
import com.example.marketplace.data.db.model.MultiselectAdapterItem
import com.example.marketplace.data.db.model.MultiselectAdapterItems
import com.example.marketplace.databinding.FilterFragmentBinding
import com.example.marketplace.ui.view.bottom_navigation.BottomNavigationViewModel
import com.example.marketplace.ui.view.filter.multiselect.MultiselectFilterFragmentArgs
import com.example.marketplace.ui.view.inner_catalog.InnerCatalogFragment
import com.example.marketplace.utils.StringUtil.Companion.combineValueWithCurrency
import com.example.marketplace.utils.test.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class FilterFragment : Fragment() {

    companion object {
        fun newInstance() = FilterFragment()
        const val FRAGMENT_REQUEST_KEY = "filterData"
    }

    private var _binding: FilterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilterViewModel by viewModel()
    private val filterSharedViewModel: FilterSharedViewModel by activityViewModels()
    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private lateinit var filterAdapter: FilterAdapter

    val multiselectAdapterItems = MultiselectAdapterItems()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        binding.btnFilter.setOnClickListener {
            val result = "$22 - 130$"
            setFragmentResult(FRAGMENT_REQUEST_KEY, bundleOf("bundleKey" to result))
        }
    }

    private fun initViews() {
        initToolbar()
        initBottomNavigationView()
        initRecyclerView()
        observeFilters()
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.appBarLayoutView.toolbar,
            findNavController()
        )
        binding.appBarLayoutView.toolbar.setNavigationIcon(R.drawable.ic_close)
        binding.appBarLayoutView.toolbar.setNavigationOnClickListener {
            bottomNavigationViewModel.setBottomNavMode(true)
            findNavController().navigateUp()
        }
        binding.appBarLayoutView.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clear -> {
                    filterSharedViewModel.clearFilters()
                    true
                }
                else -> false
            }
        }
    }

    private fun initBottomNavigationView() = bottomNavigationViewModel.setBottomNavMode(false)

    private fun initRecyclerView() {
        binding.rvFilter.apply {
            itemAnimator = null
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            filterAdapter = FilterAdapter(onItemClick = {
                when (it.id) {
                    1 -> navigateFilterPage(categoryList, "Category")
                    2 -> navigateFilterPage(productTypeList, "Product type")
                    3 -> navigateFilterPage(styleList, "Style")
                    4 -> navigateFilterPage(colorList, "Color")
                    5 -> navigateFilterPage(brandList, "Brand")
                    6 -> navigateFilterPage(sizeList, "Size")
                    7 -> {
                        findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToPriceFragment(1, 1000))
                    }
                }
            })
            filterAdapter.differ.submitList(filterList)
            adapter = filterAdapter
        }
    }

    private fun observeFilters(){
        filterSharedViewModel.color.observe(viewLifecycleOwner, { colors ->
            val tempList = filterList
            tempList.find { it.id == 4 }?.value = colors.joinToString { color -> color.name }
            updateDataList(tempList)
        })

        filterSharedViewModel.brand.observe(viewLifecycleOwner, { brands ->
            val tempList = filterList
            tempList.find { it.id == 4 }?.value = brands.joinToString { brand -> brand.name }
            updateDataList(tempList)
        })

        filterSharedViewModel.size.observe(viewLifecycleOwner, { sizes ->
            val tempList = filterList
            tempList.find { it.id == 4 }?.value = sizes.joinToString { size -> size.name }
            updateDataList(tempList)
        })

        filterSharedViewModel.price.observe(viewLifecycleOwner, { price ->
            val tempList = filterList
            tempList.find { it.id == 7 }?.value = "${
                combineValueWithCurrency(
                    price.first, "USD"
                )
            } - ${combineValueWithCurrency(price.second, "USD")}"
            updateDataList(tempList)
        })
    }

    private fun navigateFilterPage(list: List<MultiselectAdapterItem>, titleToolbar: String){
        multiselectAdapterItems.clear()
        multiselectAdapterItems.addAll(list)
        findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToMultiselectFilterFragment(multiselectAdapterItems, titleToolbar))
    }

    private fun updateDataList(newList: List<Filter>) {
        filterAdapter.differ.submitList(newList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}