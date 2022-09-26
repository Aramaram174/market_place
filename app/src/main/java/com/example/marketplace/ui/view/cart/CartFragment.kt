package com.example.marketplace.ui.view.cart

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.CartFragmentBinding
import com.example.marketplace.databinding.HomeFragmentBinding
import com.example.marketplace.ui.view.adapter.ProductAdapter
import com.example.marketplace.ui.view.adapter.RecyclerItemTouchHelper
import com.example.marketplace.ui.view.bottom_navigation.BottomNavigationViewModel
import com.example.marketplace.ui.view.main.MainViewModel
import com.example.marketplace.utils.adapter.EndlessScrollListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class CartFragment : Fragment(), CoroutineScope,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private val viewModel: CartViewModel by viewModel()
    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private val sharedPreferences: SharedPreferences by inject()
    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private var isLoading: Boolean = false
    private var products = mutableListOf<Product>()
    private val limitForGet = "20"
    private var itemsCount: Int = 0

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
        setupRecyclerView()
        binding.adapter = productAdapter
        observeOnData()
        loadData()
    }

    private fun setupRecyclerView() {
        binding.rvCart.apply {
            val mLayoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
                RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this@CartFragment)
            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvCart)

            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(object :
                EndlessScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    if (adapter?.itemCount!! < itemsCount) loadData()
                    isLoading = true
                }
            })
            productAdapter = ProductAdapter(onItemClick = {
                findNavController().navigate(
                    CartFragmentDirections.actionCartFragmentToProductFragment(
                        it
                    )
                )
            })
            adapter = productAdapter
        }
    }

    fun loadData() {
//        binding.llLoader.visibility = View.VISIBLE
        viewModel.getCart(
            sharedPreferences.getString("token", "").toString(),
            limitForGet,
            productAdapter.itemCount.toString()
        )
    }

    private fun updateDataList(newList: List<Product>) {
        val tempList = products.toMutableList()
        tempList.addAll(newList)
        productAdapter.differ.submitList(tempList)
        products = tempList
        isLoading = false
//        binding.llLoader.visibility = View.GONE

        if (products.isNullOrEmpty()) {
            binding.emptyView.root.visibility = View.VISIBLE
            binding.rvCart.visibility = View.GONE
        } else {
            binding.emptyView.root.visibility = View.GONE
            binding.rvCart.visibility = View.VISIBLE
        }
    }

    fun goToCatalog() {
        bottomNavigationViewModel.setMenuItem("Catalog")
    }

    fun goToCheckout() {
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment())
    }

    private fun observeOnData() {
        launch {
            viewModel.cartLiveData().observe(viewLifecycleOwner, { productResponse ->
                updateDataList(productResponse.results)
                itemsCount = productResponse.count
            })
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        if (viewHolder is ProductAdapter.ViewHolder) {
//            val name: String = products[viewHolder.getAdapterPosition()].name
//            val deletedIndex = viewHolder.adapterPosition
            val deletedItem = products[viewHolder.adapterPosition]
            val list = ArrayList<Product>(products)
            list.remove(deletedItem)
            products = list
            productAdapter.differ.submitList(list)

//            val snackbar = Snackbar.make(requireContext(), binding.root, "$name removed from cart!", Snackbar.LENGTH_LONG)
//            snackbar.setAction("UNDO") {
//                list.add(deletedIndex, deletedItem)
//                productAdapter.differ.submitList(list)
//            }
//            snackbar.setActionTextColor(Color.YELLOW)
//            snackbar.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}