package com.lakbay.partner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lakbay.partner.databinding.ActivityManageItemBinding
import com.lakbay.partner.databinding.ActivityManageProductBinding

class ManageProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initOnClick()
    }

    private fun initOnClick() {
        binding.addProduct = View.OnClickListener {
            startActivity(Intent(this, UpdateProductActivity::class.java))
        }
    }
}