package com.example.marketplace.ui.view.product.product_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.CatalogFragmentBinding
import com.example.marketplace.databinding.FragmentProductInformationBinding

class ProductInformationFragment : Fragment() {

    private var _binding: FragmentProductInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        initToolbar()
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.appBarLayoutView.toolbar,
            findNavController()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductInformationFragment()
    }
}