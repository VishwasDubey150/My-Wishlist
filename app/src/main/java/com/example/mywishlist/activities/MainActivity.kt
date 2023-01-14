package com.example.mywishlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.mywishlist.R
import com.example.mywishlist.database.DatabaseHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    fun add(view: View) {
        val intent= Intent(this, addlist::class.java)
        startActivity(intent)
        getHappyPlacesListFromLocalDB()
        // END
    }

    private fun getHappyPlacesListFromLocalDB() {

        val dbHandler = DatabaseHandler(this)

        val getHappyPlacesList = dbHandler.getWishlistsList()

        if (getHappyPlacesList.size > 0) {
            for (i in getHappyPlacesList) {
                Log.e("Title", i.title)
                Log.e("Description", i.description)
                Log.e("date", i.date)
            }
        }
    }
    // END
}