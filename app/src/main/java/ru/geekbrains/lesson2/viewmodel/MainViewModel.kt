package ru.geekbrains.lesson2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lesson2.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData:MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
): ViewModel() {

    fun getLiveDta():LiveData<AppState> {
        return liveData
    }

    fun getWeatherFromServer(){
        liveData.postValue(AppState.Loading(0))
        Thread{
            //liveData.value = Any()
            //liveData.postValue(AppState.Error(IllegalStateException("")))
            //синхронн и асихронн
            sleep(2000)
            val rand = (1..40).random()
            if(rand>25){
                liveData.postValue(AppState.Success(repositoryImpl.getWeatherFromServer()))
            }else{
                liveData.postValue(AppState.Error(IllegalStateException("")))
            }

            //sleep(2000)
            //liveData.postValue(AppState.Error())
        }.start()
    }

    fun getWeather(){
        //место для некоего переключателя
        getWeatherFromServer()
    }

}