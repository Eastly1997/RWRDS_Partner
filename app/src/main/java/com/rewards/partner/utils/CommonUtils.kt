package com.rewards.partner.utils

import android.content.Context

class CommonUtils {

    companion object {
        fun getEnvironment(context: Context): String {
            return if(SharedPrefUtils.getStringData(context, CommonConstants.ENVIRONMENT) == null)
                CommonConstants.ENVIRONMENT_STAGING;
            else
                SharedPrefUtils.getStringData(context, CommonConstants.ENVIRONMENT_PRODUCTION)!!
        }
        fun getAdUnitID(context: Context): String {
            return if(getEnvironment(context) != CommonConstants.ENVIRONMENT_STAGING)
                "ca-app-pub-3940256099942544/1033173712"
            else "ca-app-pub-5106113422678211/6963208998"
        }
    }
}