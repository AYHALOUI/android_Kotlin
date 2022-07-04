package com.store.affirmations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.store.affirmations.adapter.ItemAdapter
import com.store.affirmations.data.DataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = DataSource().loadAffirmations()
        val recycleView : RecyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        val adapter = ItemAdapter(this,myDataset)
        recycleView.adapter = adapter
        recycleView.setHasFixedSize(true)

    }
}