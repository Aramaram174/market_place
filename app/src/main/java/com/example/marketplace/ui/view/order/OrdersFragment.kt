package com.example.marketplace.ui.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.databinding.OrdersFragmentBinding
import com.example.marketplace.ui.view.order.adapter.OrdersAdapter
import com.example.marketplace.ui.view.order.adapter.OrdersOnClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class OrdersFragment : Fragment(), CoroutineScope {

    private val accountAppBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(R.id.fakeAccountFragmentContainerView))
    }

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private val viewModel: OrdersViewModel by viewModel()
    private var _binding: OrdersFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var ordersAdapter: OrdersAdapter
    private val job = SupervisorJob()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OrdersFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(),
            accountAppBarConfiguration
        )

        ordersAdapter = OrdersAdapter(object : OrdersOnClickListener {
            override fun onItemClick(id: Int) = print("A")
            override fun onFavoriteClick(id: Int) = print("A")
            override fun onCartClick(id: Int) = print("A")
        })

        binding.ordersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ordersAdapter
        }

        launch {
            viewModel.ordersLiveData().observe(viewLifecycleOwner, { orders ->
                    ordersAdapter.submitList(orders)
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
}