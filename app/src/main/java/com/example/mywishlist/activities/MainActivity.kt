package com.example.mywishlist.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywishlist.Adapters.MyWishlistAdapter
import com.example.mywishlist.R
import com.example.mywishlist.database.DatabaseHandler
import com.example.mywishlist.model.MyWishlistModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    fun add(view: View) {
        val intent= Intent(this, addlist::class.java)
        startActivityForResult(intent,ADD_req_code)
        getHappyPlacesListFromLocalDB()
        // END
    }

    private fun setupRecyclerView(WishList: ArrayList<MyWishlistModel>) {
        var rv=findViewById<RecyclerView>(R.id.rv)

        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        val placesAdapter = MyWishlistAdapter(this, WishList)
        rv.adapter = placesAdapter
    }


    private fun getHappyPlacesListFromLocalDB() {
        var rv=findViewById<RecyclerView>(R.id.rv)
        var empty=findViewById<TextView>(R.id.empty)

        val dbHandler = DatabaseHandler(this)

        val getHappyPlacesList = dbHandler.getWishlistsList()

        if (getHappyPlacesList.size > 0) {
            rv.visibility=View.VISIBLE
            empty.visibility=View.GONE
            setupRecyclerView(getHappyPlacesList)
        }
        else
        {
            rv.visibility=View.GONE
            empty.visibility=View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_req_code)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                getHappyPlacesListFromLocalDB()
            }
        }
    }
    companion object
    {
        var ADD_req_code=1;
    }

    fun descrip(view: View) {
        var intent=Intent(this,place_details::class.java)
        startActivity(intent)
    }
}