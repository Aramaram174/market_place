package com.example.marketplace.ui.view.fake_fragments.fake_account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marketplace.R

class FakeAccountFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FakeAccountFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fake_account, container, false)
    }
}