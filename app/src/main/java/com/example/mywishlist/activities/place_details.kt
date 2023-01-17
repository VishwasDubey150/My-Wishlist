package com.example.mywishlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.mywishlist.R

class place_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        );
        supportActionBar?.hide()
    }

    fun backing(view: View) {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun exp_date(view: View) {
        Toast.makeText(this,"expected date",Toast.LENGTH_SHORT).show()
    }
}