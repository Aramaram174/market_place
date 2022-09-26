package com.example.marketplace.ui.view.account

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.data.db.model.AccountItem
import com.example.marketplace.databinding.AccountFragmentBinding
import com.example.marketplace.databinding.MultiselectFilterFragmentBinding
import com.example.marketplace.ui.view.account.adapter.AccountAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {

    companion object {
        fun newInstance() = AccountFragment()
    }

    private var _binding: AccountFragmentBinding? = null
    private val binding get() = _binding!!
    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private val viewModel: AccountViewModel by viewModel()
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AccountFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hasPayment = true
        val accountList: ArrayList<AccountItem>
        if (hasPayment){
            accountList = arrayListOf(
                AccountItem(0, "My Details", ""),
                AccountItem(1, "Orders", ""),
                AccountItem(2, "Addresses", ""),
                AccountItem(3, "Notifications", ""),
                AccountItem(4, "Payments", ""),
                AccountItem(5, if (sharedPreferences.getString("token", "").equals("")) "SignIn" else "LogOut", ""),
            )
        }else{
            accountList = arrayListOf(
                AccountItem(1, "Addresses", ""),
                AccountItem(2, "Notifications", ""),
                AccountItem(4, "SignIn", ""))
        }

        accountAdapter = AccountAdapter(onItemClick = { position ->
            run {
                @IdRes
                val action: Int
                when(position){
                    0 -> action = R.id.action_accountFragment_to_myDetailsFragment
                    1 -> action = R.id.action_accountFragment_to_ordersFragment
                    2 -> action = R.id.action_accountFragment_to_addressesFragment
                    3 -> action = R.id.action_accountFragment_to_notificationsFragment
                    4 -> action = R.id.action_accountFragment_to_paymentsMethodsFragment
                    5 -> {
                        val token = sharedPreferences.getString("token", "")
                        if (token == "") action = R.id.action_accountFragment_to_signInFragment else {
//                            viewModel.logOut()
                            action = R.id.action_accountFragment_to_signInFragment
                            sharedPreferencesEditor.putString("token", "").commit()
                        }
                    }
                    else -> action = R.id.action_accountFragment_to_ordersFragment
                }
                findNavController().navigate(action)
            }
        }, accountList = accountList)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = accountAdapter
        }
    }

    private fun initViews(){
        initToolbar()
    }

    private fun initToolbar() {
        binding.appBarLayoutView.toolbar.inflateMenu(R.menu.menu_price_fragment)
        binding.appBarLayoutView.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    findNavController().navigate(R.id.action_accountFragment_to_settingsFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}