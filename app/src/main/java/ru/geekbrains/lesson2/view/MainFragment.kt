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
        viewModel.getLiveDta().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        viewModel.getWeatherFromServer()
    }

    fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> Toast.makeText(requireContext(), appState.error.message, Toast.LENGTH_SHORT).show()
            is AppState.Loading -> Toast.makeText(requireContext(), "${appState.progress}", Toast.LENGTH_SHORT).show()
            is AppState.Success -> Toast.makeText(requireContext(), appState.weatherData, Toast.LENGTH_SHORT).show()
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