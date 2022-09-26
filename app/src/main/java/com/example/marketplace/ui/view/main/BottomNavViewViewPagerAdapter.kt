package com.example.marketplace.ui.view.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marketplace.ui.view.fake_fragments.fake_account.FakeAccountFragment
import com.example.marketplace.ui.view.fake_fragments.fake_cart.FakeCartFragment
import com.example.marketplace.ui.view.fake_fragments.fake_catalog.FakeCatalogFragment
import com.example.marketplace.ui.view.fake_fragments.fake_favorite.FakeFavoriteFragment
import com.example.marketplace.ui.view.fake_fragments.fake_home.FakeHomeFragment

class BottomNavViewViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var fragmentList: List<Fragment> = mutableListOf()

    init {
        fragmentList = listOf(
            FakeHomeFragment.newInstance(),
            FakeCatalogFragment.newInstance(),
            FakeCartFragment.newInstance(),
            FakeFavoriteFragment.newInstance(),
            FakeAccountFragment.newInstance()
        )
    }

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return getFragmentByPosition(position)
    }

    private fun getFragmentByPosition(position: Int): Fragment {
        return fragmentList[position]
    }
}