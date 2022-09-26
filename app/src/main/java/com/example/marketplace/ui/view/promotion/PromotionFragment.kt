package com.example.marketplace.ui.view.promotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marketplace.R

class PromotionFragment : Fragment() {

    companion object {
        fun newInstance() = PromotionFragment()
    }

    private lateinit var viewModel: PromotionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.promotion_fragment, container, false)
    }
}