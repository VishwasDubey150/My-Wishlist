package com.example.mywishlist.model

import android.icu.text.CaseMap.Title
import java.util.Date

class MyWishlistModel(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val date: String,
    val location: String,
    val latitude: String,
    val longitude: Double
)