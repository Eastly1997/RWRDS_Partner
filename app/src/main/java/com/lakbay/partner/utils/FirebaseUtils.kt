package com.lakbay.partner.utils

import android.content.Context
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.lakbay.partner.viewmodel.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseUtils {
    companion object {
        const val TAG = "FirebaseUtils"
        private const val STAGING_PREFIX = "stg_"
        private const val PRODUCTION_PREFIX = "prd_"
        private const val PRODUCT = "product"

        fun saveProduct(context: Context, product: Product, callback: (DocumentReference?) -> Unit)  {
            Firebase.firestore.collection((if(CommonUtils.getEnvironment(context) == CommonConstants.ENVIRONMENT_PRODUCTION)
                PRODUCTION_PREFIX else STAGING_PREFIX ) + PRODUCT)
                .add(product)
                .addOnSuccessListener {
                    callback(it)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }
    }
}