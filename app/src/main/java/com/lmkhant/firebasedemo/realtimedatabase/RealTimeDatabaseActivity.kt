package com.lmkhant.firebasedemo.realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.lmkhant.firebasedemo.databinding.ActivityRealTimeDatabaseBinding

class RealTimeDatabaseActivity : AppCompatActivity() {
    val database = Firebase.database
    val dbRef = database.getReference("names")

    companion object{
        private const val TAG = "RealTimeDatabaseActivity"
    }
    private lateinit var binding: ActivityRealTimeDatabaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRealTimeDatabaseBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            dbRef.child("name").setValue(binding.etName.text.toString())
        }

        dbRef.child("name").addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                binding.textView.text = value
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
    }
}