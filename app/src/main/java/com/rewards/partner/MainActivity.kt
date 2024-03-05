package com.rewards.partner

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rewards.partner.databinding.ActivityMainBinding


class  MainActivity : BaseActivity() {

    companion object {
        private val TAG = this::class.java.simpleName
    }
    private lateinit var binding: ActivityMainBinding

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

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when(menuItem.itemId) {
                R.id.nav_sales -> {
                    binding.topAppBar.title = "Ticket 0"
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(true)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(false)
                }
                R.id.nav_receipt -> {
                    binding.topAppBar.title = ""
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(false)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(false)
                }
                R.id.nav_shift -> {
                    binding.topAppBar.title = ""
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(false)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(true)
                }
                R.id.nav_item -> {
                    binding.topAppBar.title = ""
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(false)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(false)
                }
                R.id.nav_settings -> {
                    binding.topAppBar.title = ""
                    binding.topAppBar.menu.findItem(R.id.toolbar_customer).setVisible(false)
                    binding.topAppBar.menu.findItem(R.id.toolbar_shift).setVisible(false)
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
}