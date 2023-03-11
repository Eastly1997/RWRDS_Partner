package com.lakbay.partner.utils

import android.content.Context

class CommonUtils {

    companion object {
        fun getEnvironment(context: Context): String {
            return if(SharedPrefUtils.getStringData(context, CommonConstants.ENVIRONMENT) == null)
                CommonConstants.ENVIRONMENT_STAGING;
            else
                SharedPrefUtils.getStringData(context, CommonConstants.ENVIRONMENT_PRODUCTION)!!
        }
    }
}