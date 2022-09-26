package com.example.marketplace.ui.view.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R
import com.example.marketplace.databinding.MainFragmentBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController

class MainFragment : Fragment(), CoroutineScope {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navControllerMain: NavController

    private val mainAppBarConfiguration by lazy {
        AppBarConfiguration(navControllerMain.graph)
    }

//    private val cardAppBarConfiguration by lazy {
//        AppBarConfiguration(
//            topLevelDestinationIds = setOf(
//            )
//        )
//    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            Toast.makeText(requireActivity(), "Aram back", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        navControllerMain = findNavController(this)

//        binding.toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.card -> {
//                    findNavController(this).navigate(R.id.action_to_cardFragment)
////                    binding.drawerLayout.openDrawer(binding.cardNavigationView)
//                    true
//                }
//                else -> false
//            }
//        }

//        NavigationUI.setupWithNavController(
//            binding.cardNavigationView,
//            navControllerMain
//        )
//        binding.toolbar.setupWithNavController(
//            navControllerMain,
//            mainAppBarConfiguration
//        )
//        NavigationUI.setupWithNavController(
//            binding.cardToolbar,
//            navControllerCard,
//            cardAppBarConfiguration
//        )

//        binding.toolbar.setNavigationOnClickListener {
//            if (navControllerMain.currentDestination?.id == R.id.cardFragment) {
//                binding.toolbar.visibility = View.GONE
//            } else {
////                Navigation.findNavController(requireActivity(), R.id.mainFragmentContainerView)
////                    .navigateUp()
////                navControllerMain.navigateUp()
////                NavigationUI.navigateUp(navControllerMain, mainAppBarConfiguration)
//            }
//        }

//        binding.cardToolbar.setNavigationOnClickListener {
////            if (navController.currentDestination?.id == R.id.destinationOfInterest) {
////                // Custom behavior here
////            } else {
//            Navigation.findNavController(requireActivity(), R.id.cardFragmentContainerView)
//                .navigateUp()
//            navControllerCard.navigateUp()
//            NavigationUI.navigateUp(navControllerCard, cardAppBarConfiguration)
////            }
//        }

//        binding.cardLayout.findViewById<ImageButton>(R.id.close_card).setOnClickListener {
//            binding.drawerLayout.closeDrawer(binding.cardNavigationView)
//        }

//        launch {
//            viewModel.getAllProductsFromCardCount().observe(viewLifecycleOwner, { count ->
//                if (count != 0) {
//                    val badgeDrawable = BadgeDrawable.create(binding.toolbar.context)
//                    badgeDrawable.number = count
//                    badgeDrawable.backgroundColor = Color.RED
//                    badgeDrawable.maxCharacterCount = 999
//                    BadgeUtils.attachBadgeDrawable(
//                        badgeDrawable,
//                        binding.toolbar,
//                        R.id.card
//                    )
//                }
//            })
//        }

//        navControllerMain.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.productFragment) {
//                binding.toolbar.visibility = View.GONE
////                bottomNavigationView.visibility = View.GONE
//            } else {
//                binding.toolbar.visibility = View.VISIBLE
////                bottomNavigationView.visibility = View.VISIBLE
//            }
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}