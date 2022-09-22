package com.training.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.training.myapplication.data.SportsData
import com.training.myapplication.model.Sport

class SportsViewModel: ViewModel() {

    private var _currentSport = MutableLiveData<Sport>()
    val currentSport: LiveData<Sport>
    get() = _currentSport

    private var _sportsData: ArrayList<Sport> = ArrayList()
    val sportsData: ArrayList<Sport>
    get() = _sportsData

    init {
        _sportsData = SportsData.getSportsDate()
        _currentSport.value = _sportsData[0]
    }

    fun updateCurrentSport(sport: Sport){
        _currentSport.value = sport
    }




}