package com.example.marketplace.ui.view.filter.price

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.PriceFragmentBinding
import com.example.marketplace.ui.view.bottom_navigation.BottomNavigationViewModel
import com.example.marketplace.ui.view.filter.FilterSharedViewModel
import com.example.marketplace.utils.StringUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PriceFragment : Fragment() {

    companion object {
        fun newInstance() = PriceFragment()
    }

    private var _binding: PriceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PriceViewModel by viewModel()
    private val filterSharedViewModel: FilterSharedViewModel by activityViewModels()
    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private val args: PriceFragmentArgs by navArgs()
    private var mMinPrice = 0
    private var mMaxPrice = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PriceFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this@PriceFragment
        binding.viewModel = viewModel
        binding.stringUtil = StringUtil()
        initViews()
        return binding.root
    }

    private fun initViews() {
        initToolbar()
        initBottomNavigationView()
        initArgs()
        initRangeSlider()
    }

    private fun initArgs(){
        args.let {
            mMinPrice = args.minPrice
            mMaxPrice = args.maxPrice
        }
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.appBarLayoutView.toolbar,
            findNavController()
        )
        binding.appBarLayoutView.toolbar.inflateMenu(R.menu.menu_price_fragment)
        binding.appBarLayoutView.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clear -> {
                    resetRangeSlider()
                    true
                }
                else -> false
            }
        }
    }

    private fun initBottomNavigationView() {
        bottomNavigationViewModel.setBottomNavMode(false)
    }

    private fun initRangeSlider() {
        val minPrice = filterSharedViewModel.price.value?.first ?: mMinPrice
        val maxPrice = filterSharedViewModel.price.value?.second ?: mMaxPrice

        viewModel.setMinPrice(minPrice)
        viewModel.setMaxPrice(maxPrice)

        binding.rsPrice.setValues(minPrice.toFloat(), maxPrice.toFloat())

        binding.rsPrice.addOnChangeListener { slider, _, _ ->
            viewModel.setMinPrice(slider.values[0].toInt())
            viewModel.setMaxPrice(slider.values[1].toInt())
        }
    }

    private fun resetRangeSlider() {
        viewModel.reset(mMinPrice, mMaxPrice)
        binding.rsPrice.setValues(mMinPrice.toFloat(), mMaxPrice.toFloat())
    }

    fun applyChanges() {
        filterSharedViewModel.setPrice(viewModel.minPrice.value!!.toInt(), viewModel.maxPrice.value!!.toInt())
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}