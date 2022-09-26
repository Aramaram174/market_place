package com.example.marketplace.ui.view.sign_up

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.databinding.SignUpFragmentBinding
import com.example.marketplace.ui.view.sign_in.SignInFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SignUpFragment : Fragment(), CoroutineScope {

    private val viewModel: SignUpViewModel by viewModel()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private var _binding: SignUpFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        initViews()
        observeUser()
        return binding.root
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(binding.toolbar, findNavController())
    }

    private fun initViews() {
        binding.btnSignUp.setOnClickListener {
            signUp(binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etRepeatPassword.text.toString()
            )
        }
    }

    private fun signUp(firstName: String, lastName: String, email: String, password: String, repeatPassword: String){
        viewModel.signUp(firstName, lastName, email, password, repeatPassword)
    }

    private fun observeUser(){
        viewModel.userLiveData().observe(viewLifecycleOwner, {
            it.token.let { token->
                sharedPreferencesEditor.putString("token", token).apply()
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