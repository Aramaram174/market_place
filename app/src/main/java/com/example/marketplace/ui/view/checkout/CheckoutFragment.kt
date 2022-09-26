package com.example.marketplace.ui.view.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.CheckoutFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class CheckoutFragment : Fragment(), CoroutineScope {

    private val viewModel: CheckoutViewModel by viewModel()
    private var _binding: CheckoutFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()
    private val appBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(R.id.fakeCartFragmentContainerView))
    }

    companion object {
        fun newInstance() = CheckoutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CheckoutFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initToolbar()
        return binding.root
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}