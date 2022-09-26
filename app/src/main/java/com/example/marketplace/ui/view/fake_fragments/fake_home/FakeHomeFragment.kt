package com.example.marketplace.ui.view.fake_fragments.fake_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marketplace.R

class FakeHomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FakeHomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fake_home, container, false)
    }
}