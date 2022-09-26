package com.example.marketplace.ui.view.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.AddressesFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class AddressesFragment : Fragment(), CoroutineScope {

    companion object {
        fun newInstance() = AddressesFragment()
    }

    private var _binding: AddressesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddressViewModel by viewModel()
    private val job = SupervisorJob()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddressesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initView()

        launch {
            viewModel.addressesLiveData().observe(viewLifecycleOwner, { addresses ->
//                addressesAdapter.submitList(addresses)
            })
        }
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    private fun initView(){
        binding.btnAddAddress.setOnClickListener {
            findNavController().navigate(AddressesFragmentDirections.actionAddressesFragmentToCreateAddressFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main
}