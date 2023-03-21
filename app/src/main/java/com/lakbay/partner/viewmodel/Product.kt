package com.lakbay.partner.viewmodel

class Product() {

    companion object {
        const val FIELD_UID = "uid"
        const val FIELD_RESTAURANT_UID = "restaurant_uid"
        const val FIELD_TYPE = "type"
        const val FIELD_PRICE = "price"
        const val FIELD_COST = "cost"
    }

    var uid:String = ""
    var restaurant_uid:String = ""
    var category:String = ""
    // 0 = Each
    // 1 = Weight
    var type:Int = 0;
    var price: Double = 0.00
    var cost: Double = 0.00

}