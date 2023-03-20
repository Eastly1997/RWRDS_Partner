package com.lakbay.partner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lakbay.partner.databinding.ActivityManageItemBinding

class ManageItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initOnClick()
    }

    private fun initOnClick() {
        binding.addItem = View.OnClickListener {
            startActivity(Intent(this, ManageProductActivity::class.java))
        }
    }
}