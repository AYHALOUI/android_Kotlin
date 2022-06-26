package com.store.happybirthday

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var image : ImageView
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        this.image = findViewById(R.id.imageView)
        Log.v(TAG, "Hello, world!")


        rollDice()

        rollButton.setOnClickListener {
            rollDice2()
        }
    }

    private fun rollDice() {
        val dice1 = Dice(6)
        val numberRandom = dice1.rollDice()
        if(numberRandom == 1) image.setImageResource(R.drawable.dice_1)
        else if(numberRandom == 2) image.setImageResource(R.drawable.dice_2)
        else if(numberRandom == 3) image.setImageResource(R.drawable.dice_3)
        else if(numberRandom == 4) image.setImageResource(R.drawable.dice_4)
        else if(numberRandom == 5) image.setImageResource(R.drawable.dice_5)
        else image.setImageResource(R.drawable.dice_6)
    }
    private fun rollDice2(){
        val dice2 = Dice(6)
        //this.image.contentDescription(dice2.rollDice().toString())
        val drawbleResource = when(dice2.rollDice()){
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else-> R.drawable.dice_6
        }
        image.setImageResource(drawbleResource as Int)

    }
}

