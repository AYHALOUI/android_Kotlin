package com.store.happybirthday

class Dice(val sides : Int) {

    fun rollDice(): Int {
        return (1..sides).random()
    }
}