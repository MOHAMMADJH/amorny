package com.mapapp.amorny;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_producer.view.*

class MainActivity : AppCompatActivity() {


    val db = FirebaseDatabase.getInstance()
    var mRef:DatabaseReference? = null
    var mCartList:ArrayList<Producer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRef = db.getReference("Cart")
        mCartList = ArrayList()

        Add_to_cart.setOnClickListener{
            showAddCart()

        }
    }

    override fun onStart() {
        super.onStart()
        mRef?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                for(n in p0.children){
                    var cart = n.getValue(Producer::class.java)
                    mCartList?.add(cart!!)
                }
                var cartAdapter:CartAdapter = CartAdapter(applicationContext, mCartList!!)
                shopping_basket.adapter = cartAdapter
            }

        })
    }
    fun showAddCart(){
        val alertBuilder  = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_producer, null)
        alertBuilder.setView(view)

        val alertDialog = alertBuilder.create()
        alertBuilder.show()

        view.addButton.setOnClickListener {
            val producer =  view.productText.text.toString()
            val weight =  view.weightText.text.toString()
            val unit =  view.unit.text.toString()

            if(producer.isNotEmpty() && weight.isNotEmpty() && unit.isNotEmpty()){
                var id = mRef!!.push().key
                var myProducer = Producer(id ,producer, weight ,unit)
                mRef!!.child(id.toString()).setValue(myProducer)
                view.productText.setText("")
                view.weightText.setText("")
                view.unit.setText("")

                alertDialog.dismiss()
            }else{
                Toast.makeText(this, "الحقول فارغة" , Toast.LENGTH_LONG).show()
            }
        }
    }
}
