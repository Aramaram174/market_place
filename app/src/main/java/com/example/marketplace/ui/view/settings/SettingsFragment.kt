package com.example.marketplace.ui.view.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.R
import com.example.marketplace.data.db.model.AccountItem
import com.example.marketplace.databinding.SettingsFragmentBinding
import com.example.marketplace.ui.view.account.adapter.AccountAdapter
import com.example.marketplace.utils.LocaleManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var binding: SettingsFragmentBinding
    private val sharedPreferences: SharedPreferences by inject()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()
    private val viewModel: SettingsViewModel by viewModel()
    private lateinit var accountAdapter: AccountAdapter
    private val appBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.fakeAccountFragmentContainerView))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initViews()
        return binding.root
    }

    private fun initViews() {
        initToolbar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountList: ArrayList<AccountItem> = arrayListOf(
            AccountItem(1, "Change password", ""),
        )

        accountAdapter = AccountAdapter(onItemClick = { position ->
            run {
                @IdRes
                var action: Int = -1
                when (position) {
                    0 -> action = R.id.action_settingsFragment_to_changePasswordFragment
                    else -> action = R.id.action_settingsFragment_to_changePasswordFragment
                }
                findNavController().navigate(action)
            }
        }, accountList = accountList)

        binding.settingsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = accountAdapter
        }

        binding.armenian.setOnClickListener {
            setNewLocale(requireActivity(), "hy")
        }
        binding.english.setOnClickListener {
            setNewLocale(requireActivity(), "en")
        }
    }

    private fun initToolbar() {
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(),
            appBarConfiguration
        )
    }

    private fun setNewLocale(
        mContext: FragmentActivity,
        language: String
    ) {
        LocaleManager.setNewLocale(mContext, language)
        val intent = mContext.intent
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}