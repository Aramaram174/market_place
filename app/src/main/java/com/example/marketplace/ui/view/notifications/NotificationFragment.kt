package com.example.marketplace.ui.view.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.NotificationFragmentBinding

class NotificationFragment : Fragment() {

    private val accountAppBarConfiguration by lazy {
        AppBarConfiguration(topLevelDestinationIds = setOf(R.id.fakeAccountFragmentContainerView))
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }

    private lateinit var binding: NotificationFragmentBinding
    private lateinit var viewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationFragmentBinding.inflate(inflater, container, false)
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
    }
}