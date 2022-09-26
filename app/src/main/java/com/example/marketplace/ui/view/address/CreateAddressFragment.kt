package com.example.marketplace.ui.view.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.data.db.model.Address
import com.example.marketplace.databinding.CreateAddressFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class CreateAddressFragment : Fragment(), CoroutineScope {

    private val viewModel: AddressViewModel by viewModel()
    private var _binding: CreateAddressFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()

    companion object {
        fun newInstance() = CreateAddressFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateAddressFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    private fun initToolbar(){
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    private fun initViews(){
        binding.btnSaveAddress.setOnClickListener {
//            saveAddress(Address())
        }
    }

    private fun saveAddress(address: Address){
        viewModel.addAddress(address)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}