package com.training.loadimagesfrominternet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.loadimagesfrominternet.network.MarsApi
import com.training.loadimagesfrominternet.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus{LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel() {

    private var _status  =  MutableLiveData<MarsApiStatus>()
    val status : LiveData<MarsApiStatus> = _status

    private var _photos = MutableLiveData<List<MarsPhoto>>()
    val photos : LiveData<List<MarsPhoto>> = _photos

    init {
        getMarsPhoto()
    }

    fun getMarsPhoto(){
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            }catch (e : Exception){
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }


}