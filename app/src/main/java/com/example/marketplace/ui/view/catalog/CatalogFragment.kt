package com.example.marketplace.ui.view.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.data.db.model.Category
import com.example.marketplace.databinding.CatalogFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext
import android.text.TextUtils
import com.example.marketplace.R

class CatalogFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = CatalogFragment()
    }

    private var _binding: CatalogFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModel()
    private val args: CatalogFragmentArgs by navArgs()
    private lateinit var catalogAdapter: CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatalogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        initCatalogRv()
        if (args.category != null) {
            initToolbar()
            initArguments(args.category)
        }else {
            binding.appBarLayout.visibility = View.INVISIBLE
            initSearchView(binding.svSearchCatalog)
            observeOnCatalogUpdate()
        }
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    private fun initCatalogRv() {
        binding.rvCatalog.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            catalogAdapter = CatalogAdapter(onItemClick = {
                if (it.subCategories.isNullOrEmpty()){
                    findNavController().navigate(CatalogFragmentDirections.actionCatalogFragmentToInnerCatalogFragment(it))
                }else {
                    findNavController().navigate(CatalogFragmentDirections.actionCatalogFragmentToCatalogFragment(it))
                }
            })
            adapter = catalogAdapter
        }
    }

    private fun initArguments(category: Category?){
        setToolbarTitle(category?.name)
        catalogAdapter.submitList(category?.subCategories)
    }

    private fun setToolbarTitle(title: String?){
        binding.toolbar.title = title
    }

    private fun observeOnCatalogUpdate(){
        viewModel.catalogLiveData()
            .observe(viewLifecycleOwner, { catalogList ->
                //Todo must delete
                catalogList.forEach { it.imageURL = "https://images.unsplash.com/photo-1621616875450-79f024a8c42c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxOTU3MDZ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjM2OTk4MjI&ixlib=rb-1.2.1&q=80&w=1080" }
                catalogAdapter.submitList(catalogList)
            })
    }

    private fun initSearchView(searchView: SearchView) {
//        searchView.queryHint = getString(R.string.search_go)
        searchView.onActionViewExpanded()
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (TextUtils.isEmpty(query)) return false
                navigateToSearchFragment(query)
//                SoftInputUtil.hideIMM(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun navigateToSearchFragment(query: String){
        findNavController().navigate(CatalogFragmentDirections.actionCatalogFragmentToSearchFragment(query))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}