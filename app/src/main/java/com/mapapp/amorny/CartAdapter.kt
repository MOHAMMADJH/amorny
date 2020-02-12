package com.mapapp.amorny

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.cart_layout.view.*

class CartAdapter(context: Context, cartList:ArrayList<Producer>) : ArrayAdapter<Producer>(context , 0 ,cartList) {



    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val view = LayoutInflater.from(context).inflate(R.layout.cart_layout,parent, false)
        val producers: Producer = this.getItem(position)!!

            view.textProducer.text = producers.producer
            view.textWeight.text = producers.weight
            view.textMeasuringUnit.text = producers.measuringUnit
        return view

    }


}