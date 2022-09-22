package com.training.cupcake.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel(){


    private val _quantity =  MutableLiveData<Int>()
    val quantity : LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor : LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date : LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price : LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
        Log.d("xxx", "resetOrder")
        //show()
    }

    fun resetOrder(){
        _quantity.value = 0
        _flavor.value = ""
        dateOptions?.apply { _date.value = dateOptions[0]}
        //_date.value= dateOptions[0]
        _price.value = 0.0
        show()
    }

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String){
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean{
        return _flavor.value.isNullOrEmpty()
    }


    val dateOptions = getPickupOptions()

    private fun updatePrice(){
        var calculatedPrice = (_quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if(dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    private fun getPickupOptions(): List<String>{
        val option = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4)
        {
            option.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return option
    }


    fun show(){
        dateOptions?.forEach {
            println(it)
        }
    }


}