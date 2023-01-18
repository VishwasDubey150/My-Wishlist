package com.example.mywishlist.Adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mywishlist.R
import com.example.mywishlist.model.MyWishlistModel

class MyWishlistAdapter(

    private val context: Context,
    private var list: ArrayList<MyWishlistModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]


        if(holder is MyViewHolder)
        {
            holder.itemView.findViewById<TextView>(R.id.place).text=model.title
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListner{
        fun onClick(position: Int,model: MyWishlistModel)
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
