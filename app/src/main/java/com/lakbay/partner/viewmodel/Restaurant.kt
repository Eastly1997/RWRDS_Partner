package com.lakbay.partner.viewmodel

class Restaurant() {
    companion object {
        const val FIELD_UID = "uid"
    }
    var uid:String = ""
    var loc_lat: Double = 0.0
    var loc_lng: Double = 0.0
    var name: String = ""
    var rating: Double = 0.0
    var img: String = ""
}