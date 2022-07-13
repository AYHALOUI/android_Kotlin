package com.store.wordapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.store.wordapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private var isLinearlayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        recyclerView = binding.recycleView
        chooseLayout()
    }


    private fun chooseLayout(){
        if (isLinearlayout)
        {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this,4)
        }
        recyclerView.adapter = LatterAdapter(this)

    }

    private fun setIcon(menuItem: MenuItem){
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearlayout){
                ContextCompat.getDrawable(this,R.drawable.ic_linear_layout)
            }else
                ContextCompat.getDrawable(this,R.drawable.ic_grid_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu,menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        if (layoutButton != null) {
            setIcon(layoutButton)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearlayout =! isLinearlayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }





}