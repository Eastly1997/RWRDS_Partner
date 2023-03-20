package com.lakbay.partner

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.lakbay.partner.databinding.ActivityUpdateProductBinding


class UpdateProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initOnClick()

        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
        (binding.categoryList.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

    }

    private fun initOnClick() {
    }
}