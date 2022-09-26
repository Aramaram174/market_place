package com.example.marketplace.ui.view.my_details

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.data.db.model.User
import com.example.marketplace.databinding.MyDetailsFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MyDetailsFragment : Fragment(), CoroutineScope {

    private val viewModel: MyDetailsViewModel by viewModel()
    private val sharedPreferences: SharedPreferences by inject()
    private var _binding: MyDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()

    companion object {
        fun newInstance() = MyDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyDetailsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initViews()
        getUser()
        observeUser()
        return binding.root
    }

    private fun initViews() {
        initToolbar()
        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().navigateUp() }
        binding.etFirstName.addTextChangedListener {
            viewModel.isChangedProfileData(collectUserData())
        }
        binding.etLastName.addTextChangedListener {
            viewModel.isChangedProfileData(collectUserData())
        }
        binding.etEmail.addTextChangedListener {
            viewModel.isChangedProfileData(collectUserData())
        }

        binding.btnSaveChanges.setOnClickListener {
            updateUser(sharedPreferences.getString("token", "")!!, User().apply {
                binding.etFirstName.text
                binding.etLastName.text
                binding.etEmail.text
            })
        }
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
    }

    private fun getUser(){
        viewModel.getUser()
    }

    private fun updateUser(token: String, user: User){
        viewModel.updateUser(token, user)
    }

    private fun observeUser() {
        viewModel.userLiveData().observe(viewLifecycleOwner, {
            it?.let {
                binding.etFirstName.setText(it.firstName)
                binding.etLastName.setText(it.lastName)
                binding.etEmail.setText(it.email)
            }
        })
    }

    private fun collectUserData(): User = User().apply {
            firstName = binding.etFirstName.text.toString()
            lastName = binding.etLastName.text.toString()
            email = binding.etEmail.text.toString()
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}