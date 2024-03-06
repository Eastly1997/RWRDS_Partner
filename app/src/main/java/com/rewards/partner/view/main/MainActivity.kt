package com.rewards.partner.view.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rewards.partner.R
import com.rewards.partner.databinding.ActivityMainBinding
import com.rewards.partner.databinding.NavHeaderMainBinding
import com.rewards.partner.view.BaseActivity
import com.rewards.partner.view.LoginActivity


class  MainActivity : BaseActivity() {

    companion object {
        private val TAG = this::class.java.simpleName
    }
    private lateinit var binding: ActivityMainBinding
    private var itemFragment = ItemFragment()
    private var receiptFragment = ReceiptFragment()
    private var salesFragment = SalesFragment()
    private var settingsFragment = SettingsFragment()
    private var shiftFragment = ShiftFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupAppBar()
        setupNavigation()
    }


    private fun setupAppBar() {
        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
    }
    private fun setupNavigation() {
        val headerBinding: NavHeaderMainBinding = NavHeaderMainBinding.bind(binding.navigationView.getHeaderView(0))
        headerBinding.name.text = Firebase.auth.currentUser?.displayName ?: getString(R.string.dummy_owner)
        headerBinding.shop.text = getString(R.string.dummy_shop)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(false)
            binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(false)

            when(menuItem.itemId) {
                R.id.nav_sales -> {
                    binding.topAppBar.title = "Ticket 0"
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(true)
                    switchFragment(salesFragment)
                }
                R.id.nav_receipt -> {
                    binding.topAppBar.title = getString(R.string.receipt_title)
                    switchFragment(receiptFragment)
                }
                R.id.nav_shift -> {
                    binding.topAppBar.title = getString(R.string.shift_title)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(true)
                    switchFragment(shiftFragment)
                }
                R.id.nav_item -> {
                    binding.topAppBar.title = getString(R.string.item_title)
                    switchFragment(itemFragment)
                }
                R.id.nav_settings -> {
                    binding.topAppBar.title = getString(R.string.setting_title)
                    switchFragment(settingsFragment)
                }
                R.id.nav_logout -> {  Firebase.auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
            binding.drawerLayout.close()
            true
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, fragment)
        transaction.commitAllowingStateLoss()
    }
}