package com.lakbay.partner

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.lakbay.partner.databinding.ActivityUpdateProductBinding
import com.lakbay.partner.utils.FirebaseUtils
import com.lakbay.partner.utils.SharedPrefUtils
import com.lakbay.partner.viewmodel.Product
import com.lakbay.partner.viewmodel.Restaurant


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

        val items = arrayOf("No Category", "Create Category")
        (binding.categoryList.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

    }

    private fun initOnClick() {
        val product: Product = Product()
        product.restaurant_uid = SharedPrefUtils.getStringData(
            this@UpdateProductActivity,
            Restaurant.FIELD_UID
        )
        binding.save.setOnClickListener {
            FirebaseUtils.saveProduct(this@UpdateProductActivity, product) {
                if (it == null) {
                    Snackbar.make(binding.root, "Error, Please try again later.", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    finish()
                }
            }
        }
    }
}