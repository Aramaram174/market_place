package com.example.marketplace.ui.view.fake_fragments.fake_cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marketplace.R

class FakeCartFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FakeCartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fake_cart, container, false)
    }
}