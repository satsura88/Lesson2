package ru.geekbrains.lesson2.view

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lesson2.R
import ru.geekbrains.lesson2.databinding.ActivityMainBinding
import ru.geekbrains.lesson2.databinding.FragmentMainBinding
import ru.geekbrains.lesson2.viewmodel.AppState
import ru.geekbrains.lesson2.viewmodel.MainViewModel


class MainFragment : Fragment() {

    var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
    get(){
        return _binding!!
    }

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //главная строка урока!!!
        viewModel.getLiveDta().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        viewModel.getWeather()
    }

    private fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView, "Ошибка(",Snackbar.LENGTH_LONG).setAction("Ребут"){
                    viewModel.getWeatherFromServer()
                }.show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.cityName.text = appState.weatherData.city.name
                binding.cityCoordinates.text = "${appState.weatherData.city.lat} ${appState.weatherData.city.lon}"
                binding.temperatureValue.text = "${appState.weatherData.temperature}"
                binding.feelsLikeValue.text = "${appState.weatherData.feelsLike}"
                Snackbar.make(binding.mainView, "Успех",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}