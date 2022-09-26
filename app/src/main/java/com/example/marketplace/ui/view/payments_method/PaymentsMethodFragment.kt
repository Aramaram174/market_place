package com.example.marketplace.ui.view.payments_method

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
import com.example.marketplace.databinding.PaymentsMethodsFragmentBinding
import com.example.marketplace.ui.view.payments_method.adapter.PaymentsMethodAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class PaymentsMethodFragment : Fragment(), CoroutineScope {

    private val accountAppBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(R.id.fakeAccountFragmentContainerView))
    }

    companion object {
        fun newInstance() = PaymentsMethodFragment()
    }

    private var _binding: PaymentsMethodsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PaymentsMethodViewModel by viewModel()
    private lateinit var paymentsMethodsAdapter: PaymentsMethodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaymentsMethodsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initToolbar()
        initRv()
        observeOnPaymentsMethodsUpdate()
        return binding.root
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(),
            accountAppBarConfiguration
        )
    }

    private fun initRv() {
        binding.rvPaymentsMethod.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            paymentsMethodsAdapter = PaymentsMethodAdapter()
            adapter = paymentsMethodsAdapter
        }
    }

    private fun observeOnPaymentsMethodsUpdate(){
        viewModel.paymentsMethodLiveData().observe(viewLifecycleOwner, { paymentsMethods ->
                paymentsMethodsAdapter.submitList(paymentsMethods)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}