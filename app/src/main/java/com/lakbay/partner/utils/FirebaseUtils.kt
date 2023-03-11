package com.lakbay.partner.utils

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseUtils {
    companion object {
        const val TAG = "FirebaseUtils"
        private const val STAGING_PREFIX = "stg_"
        private const val PRODUCTION_PREFIX = "prd_"

        private const val USERS = "PARTNER_USER";

        fun getRestaurantRef(context: Context): CollectionReference {
            return Firebase.firestore.collection(
                (if(CommonUtils.getEnvironment(context) == CommonConstants.ENVIRONMENT_PRODUCTION)
                    PRODUCTION_PREFIX else STAGING_PREFIX ) + USERS)
        }
    }
}