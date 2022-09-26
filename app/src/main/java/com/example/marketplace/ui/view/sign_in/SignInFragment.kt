package com.example.marketplace.ui.view.sign_in

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.databinding.SignInFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SignInFragment : Fragment(), CoroutineScope {

    private val viewModel: SignInViewModel by viewModel()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!
    private val job = SupervisorJob()

    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SignInFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        initToolbar()
        initViews()
        observeUser()
        return binding.root
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController()
        )
    }

    private fun initViews() {
        binding.btnSignIn.setOnClickListener {
            signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }
        binding.tvForgotPassword.setOnClickListener{
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment())
        }
    }

    private fun signIn(login: String, password: String){
        viewModel.signIn(login, password)
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