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
        liveData.postValue(AppState.Loading(0))
        Thread{
            //liveData.value = Any()
            sleep(2000)
            //liveData.postValue(AppState.Error(IllegalStateException("")))
            val rand = (1..40).random()
            if(rand>25){
                liveData.postValue(AppState.Success("Жарко звездец"))
            }else{
                liveData.postValue(AppState.Error(IllegalStateException("")))
            }

            //sleep(2000)
            //liveData.postValue(AppState.Error())
        }.start()
    }

}