package com.store.happybirthday

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun generate_number(){
        val dice = Dice(6)
        assertTrue("the number of the riceRoller between 1 and 6", dice.rollDice() in 1..6)
    }
}