package com.example.marketplace.ui.view.fake_fragments.fake_catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.marketplace.R

class FakeCatalogFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FakeCatalogFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val callback: OnBackPressedCallback =
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    findNavController().navigateUp()
//                }
//            }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            NavHostFragment.findNavController(this@FakeCatalogFragment).navigateUp()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fake_catalog, container, false)
    }
}