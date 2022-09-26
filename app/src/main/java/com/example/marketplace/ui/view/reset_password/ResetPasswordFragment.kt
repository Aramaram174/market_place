package com.example.marketplace.ui.view.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.databinding.ResetPasswordFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class ResetPasswordFragment : Fragment(), CoroutineScope {

    private val viewModel: ResetPasswordViewModel by viewModel()
    private var _binding: ResetPasswordFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResetPasswordFragmentBinding.inflate(layoutInflater, container, false)
        initToolbar()
        initViews()
        observeData()
        return binding.root
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
    }

    private fun initViews() {
        binding.btnForgotPassword.setOnClickListener {
            viewModel.forgotPassword(binding.etEmail.text.toString())
        }
        binding.btnSendPin.setOnClickListener {
            viewModel.checkResetKey(binding.etEmailPin.text.toString())
        }
    }

    private fun observeData(){
        viewModel.forgotPasswordLiveData().observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireActivity(), "Pin successfully send your email", Toast.LENGTH_SHORT).show()
                binding.etEmailPin.visibility = View.VISIBLE
                binding.btnSendPin.visibility = View.VISIBLE
            }
        })
        viewModel.pinLiveData().observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigateUp()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}