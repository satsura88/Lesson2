package ru.geekbrains.lesson2.model

data class Weather(val city:City= getDefaultCity(), val temperature:Int=20, val feelsLike:Int=22)

data class City(val name:String, val lon:Double, val lat:Double)

fun getDefaultCity() = City("Минск", 27.34, 53.54)
