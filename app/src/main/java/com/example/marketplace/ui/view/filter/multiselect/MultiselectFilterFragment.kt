package com.example.marketplace.ui.view.filter.multiselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.ui.view.bottom_navigation.BottomNavigationViewModel
import com.example.marketplace.ui.view.filter.FilterSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.marketplace.data.db.model.MultiselectAdapterItems
import com.example.marketplace.databinding.MultiselectFilterFragmentBinding

class MultiselectFilterFragment : Fragment() {

    companion object {
        fun newInstance() = MultiselectFilterFragment()
    }

    private var _binding: MultiselectFilterFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MultiselectFilterViewModel by viewModel()
    private val filterSharedViewModel: FilterSharedViewModel by activityViewModels()
    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private val args: MultiselectFilterFragmentArgs by navArgs()
    private lateinit var multiselectFilterAdapter: MultiselectFilterAdapter
    private lateinit var multiselectAdapterItems: MultiselectAdapterItems
    private var titleToolbar: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MultiselectFilterFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this@MultiselectFilterFragment
        binding.viewModel = viewModel
        initViews()
        return binding.root
    }

    private fun initArgs(){
        args.let {
            multiselectAdapterItems = it.multiselectAdapterItems
            titleToolbar = it.titleToolbar
        }
    }

    private fun initViews() {
        initToolbar()
        initBottomNavigationView()
        initRecyclerView()
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.appBarLayoutView.toolbar,
            findNavController()
        )
        binding.appBarLayoutView.toolbar.title = titleToolbar
        binding.appBarLayoutView.toolbar.inflateMenu(R.menu.menu_price_fragment)
        binding.appBarLayoutView.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clear -> {
                    multiselectFilterAdapter.resetSelected()
                    true
                }
                else -> false
            }
        }
    }

    private fun initBottomNavigationView() = bottomNavigationViewModel.setBottomNavMode(false)

    private fun initRecyclerView() {
        binding.rvMultiselect.apply {
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            multiselectFilterAdapter = MultiselectFilterAdapter()
            multiselectFilterAdapter.differ.submitList(multiselectAdapterItems)
            adapter = multiselectFilterAdapter
        }
    }

    private fun setToolbarTitle(toolbarTitle: String){
        binding.appBarLayoutView.toolbar.title = toolbarTitle
    }

    fun applyChanges() {
        filterSharedViewModel.setColor(multiselectFilterAdapter.getSelected())
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}