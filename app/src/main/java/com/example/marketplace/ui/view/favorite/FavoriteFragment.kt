package com.example.marketplace.ui.view.favorite

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.FavoriteFragmentBinding
import com.example.marketplace.ui.view.adapter.ProductAdapter
import com.example.marketplace.ui.view.adapter.RecyclerItemTouchHelper
import com.example.marketplace.ui.view.bottom_navigation.BottomNavigationViewModel
import com.example.marketplace.ui.view.favorite.adapter.SwipeToDeleteCallback
import com.example.marketplace.utils.adapter.EndlessScrollListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class FavoriteFragment : Fragment(), CoroutineScope, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by activityViewModels()
    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var productAdapter: ProductAdapter
    private var isLoading: Boolean = false
    private var products = mutableListOf<Product>()
    private val limitForGet = "20"
    private var itemsCount: Int = 0
    private val job = SupervisorJob()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this@FavoriteFragment
        setupRecyclerView()
        loadData()
        observeOnData()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.favoriteRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = linearLayoutManager
            itemAnimator = null
            addOnScrollListener(object : EndlessScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    if (adapter?.itemCount!! < itemsCount) loadData()
                    isLoading = true
                }
            })
            productAdapter = ProductAdapter(onItemClick = {
//                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(product = it))
//                findNavController().navigate(R.id.action_cardFragment_to_productFragment)
                findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToProductFragment(it))
            })
            adapter = productAdapter
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireActivity()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteProductFromFavorites(sharedPreferences.getString("token", "").toString(), productAdapter.getIdByPosition(viewHolder.adapterPosition))
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.favoriteRecyclerView)
    }

    fun loadData() {
//        binding.llLoader.visibility = View.VISIBLE
        launch {
            viewModel.getFavorite(
                sharedPreferences.getString("token", "").toString(),
                limitForGet,
                productAdapter.itemCount.toString())
        }
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
            binding.favoriteRecyclerView.visibility = View.GONE
        } else {
            binding.emptyView.root.visibility = View.GONE
            binding.favoriteRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun observeOnData() {
        launch {
            viewModel.favoriteLiveData.observe(viewLifecycleOwner, { productResponse ->
                updateDataList(productResponse.results)
                itemsCount = productResponse.count
            })
        }
    }

    fun goToCatalog(){
        bottomNavigationViewModel.setMenuItem("Catalog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

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
}