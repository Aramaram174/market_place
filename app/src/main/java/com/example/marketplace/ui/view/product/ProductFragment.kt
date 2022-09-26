package com.example.marketplace.ui.view.product

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.data.db.model.Product
import com.example.marketplace.databinding.CartFragmentBinding
import com.example.marketplace.databinding.ProductFragmentBinding
import com.example.marketplace.ui.view.cart.CartViewModel
import com.example.marketplace.ui.view.favorite.FavoriteViewModel
import com.google.android.material.checkbox.MaterialCheckBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class ProductFragment : Fragment(), CoroutineScope {

    private val productViewModel: ProductViewModel by viewModel()
    private val cartViewModel: CartViewModel by viewModel()
    private val favoriteViewModel: FavoriteViewModel by activityViewModels()
    private val sharedPreferences: SharedPreferences by inject()
    private val args: ProductFragmentArgs by navArgs()
    private var _binding: ProductFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProductFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this@ProductFragment
        binding.product = args.product
        initViews()
        return binding.root
    }

    private fun initViews(){
        initToolbar()
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    fun onCheckedChanged(checkBox: CompoundButton, isChecked: Boolean, product: Product){
        if (!checkBox.isPressed) return
        if (isChecked) {
            addProductToFavorites(product.apply {isFavorite = true})
        } else {
            deleteProductFromFavorites(product.id)
        }
    }

    fun addProductToCart(id: Int){
        launch {
            cartViewModel.addProductToCart(sharedPreferences.getString("token", "").toString(), id)
        }
    }

    private fun addProductToFavorites(product: Product){
        launch {
            favoriteViewModel.addProductToFavorites(sharedPreferences.getString("token", "").toString(), product)
        }
    }

    private fun deleteProductFromFavorites(id: Int){
        launch {
            favoriteViewModel.deleteProductFromFavorites(sharedPreferences.getString("token", "").toString(), id)
        }
    }

    fun navigateToProductInformation(){
        findNavController().navigate(ProductFragmentDirections.actionProductFragmentToProductInformationFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext =  Dispatchers.Main
}