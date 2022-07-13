package com.store.wordapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.store.wordapp.databinding.ActivityDetailBinding



class DetailActivity : AppCompatActivity() {

    lateinit var recycleVDatailActivtiy : RecyclerView
    private var isLinearlayout = true
    lateinit var adapter : WordAdapter


    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val latterId = intent?.extras?.get(LETTER).toString()

        adapter  = WordAdapter(latterId,this)
        recycleVDatailActivtiy = binding.recycleVDatailActivtiy
        chooseLayout()

        binding.recycleVDatailActivtiy.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix)+" "+latterId



    }

    private fun chooseLayout(){
        if (isLinearlayout){
            recycleVDatailActivtiy.layoutManager = LinearLayoutManager(this)
        }else
        {
            recycleVDatailActivtiy.layoutManager = GridLayoutManager(this,4)
        }
        recycleVDatailActivtiy.adapter = adapter

    }

    private fun setIcon(menuItem : MenuItem){
        if (menuItem == null)
        return
        menuItem.icon =
            if (isLinearlayout){
                ContextCompat.getDrawable(this,R.drawable.ic_linear_layout)
            }else{
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater
            .inflate(R.menu.layout_menu,menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        if (layoutButton != null) setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout ->{
                isLinearlayout =! isLinearlayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}