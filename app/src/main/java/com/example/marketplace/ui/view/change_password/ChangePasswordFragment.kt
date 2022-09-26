package com.example.marketplace.ui.view.change_password

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.databinding.ChangePasswordFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class ChangePasswordFragment : Fragment(), CoroutineScope {

    private val viewModel: ChangePasswordViewModel by viewModel()
    private var _binding: ChangePasswordFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()
    private val sharedPreferences: SharedPreferences by inject()

    companion object {
        fun newInstance() = ChangePasswordFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        initViews()
        observeChangePassword()
        return binding.root
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
    }

    private fun initViews() {
        binding.btnChangePassword.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword(){
        viewModel.changePassword(
            sharedPreferences.getString("token", "").toString(),
            binding.etOldPassword.text.toString(),
            binding.etNewPassword.text.toString(),
            binding.etRepeatNewPassword.text.toString()
        )
    }

    private fun observeChangePassword() {
        viewModel.changePasswordLiveData().observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigateUp()
                Toast.makeText(
                    requireActivity(),
                    "Password successfully changed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}