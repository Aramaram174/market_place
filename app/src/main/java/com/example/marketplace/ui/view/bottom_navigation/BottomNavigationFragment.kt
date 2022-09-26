package com.example.marketplace.ui.view.bottom_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.marketplace.databinding.BottomNavigationFragmentBinding
import com.example.marketplace.ui.view.main.BottomNavViewViewPagerAdapter
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext
import androidx.fragment.app.activityViewModels
import com.example.marketplace.data.db.model.BottomNavigationView
import com.example.marketplace.ui.view.main.MainViewModel
import com.example.marketplace.utils.config.ConfigManager
import com.example.marketplace.utils.getDrawableResourceByName
import com.example.marketplace.utils.getStringResourceByName

class BottomNavigationFragment : Fragment(), CoroutineScope {

    private lateinit var navController: NavController
    private lateinit var binding: BottomNavigationFragmentBinding
    private val viewModel: BottomNavigationViewModel by activityViewModels()

    private lateinit var viewPagerAdapter: BottomNavViewViewPagerAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    private val mOnNavigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { menuItem ->
            binding.mainViewPager.setCurrentItem(menuItem.itemId, false)
            return@OnItemSelectedListener true
        }

    companion object {
        fun newInstance() = BottomNavigationFragment()
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomNavigationFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        return binding.root
    }

    private fun initViews(){
        observeBottomNavMode()
        observeMenuItem()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = ConfigManager.getBottomNavigationView()
        val menu: Menu = binding.bottomNavigationView.menu
        binding.bottomNavigationView.labelVisibilityMode = bottomNavigationView.itemType

        for (bottomMenuItem in bottomNavigationView.bottomMenuItems) {
            menu.add(
                Menu.NONE,
                bottomMenuItem.itemId,
                Menu.NONE,
                getStringResourceByName(requireContext(), bottomMenuItem.titleResId)
            ).icon =
                getDrawableResourceByName(requireContext(), bottomMenuItem.iconResId)
        }

        navController = Navigation.findNavController(view)
        binding.mainViewPager.isUserInputEnabled = false
        viewPagerAdapter = BottomNavViewViewPagerAdapter(this)
        binding.mainViewPager.offscreenPageLimit = viewPagerAdapter.itemCount
        binding.mainViewPager.adapter = viewPagerAdapter

        binding.bottomNavigationView.setOnItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
    }

    private fun setBottomNavigationItem(item: String){
        val itemId = when(item) {
            "Catalog" -> 1
            "Favorite" -> 3
            else -> 0
        }
        binding.mainViewPager.setCurrentItem(itemId, false)
        binding.bottomNavigationView.menu.getItem(itemId).isChecked = true
    }

    private fun observeBottomNavMode() {
        viewModel.bottomNavVisible.observe(viewLifecycleOwner){ bottomNavVisible ->
            if (bottomNavVisible) binding.bottomNavigationView.visibility =
                View.VISIBLE else binding.bottomNavigationView.visibility = View.GONE
        }
    }

    private fun observeMenuItem() {
        viewModel.menuItem.observe(viewLifecycleOwner, {
            setBottomNavigationItem(it)
        })
    }
}