package com.rewards.partner

import android.os.Bundle
import com.lakbay.partner.databinding.ActivityMainBinding


class  MainActivity : BaseActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
    }
}