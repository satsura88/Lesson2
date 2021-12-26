package ru.geekbrains.lesson2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveData:MutableLiveData<AppState> = MutableLiveData()): ViewModel() {

    fun getLiveDta():LiveData<AppState> {
        return liveData
    }

    fun getWeatherFromServer(){
        Thread{
            //liveData.value = Any()
            liveData.postValue(AppState.Loading(0))
            sleep(2000)
            liveData.postValue(AppState.Success("Холодно звездец"))
            sleep(2000)
            //liveData.postValue(AppState.Error())
        }.start()
    }

}