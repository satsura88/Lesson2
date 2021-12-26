package ru.geekbrains.lesson2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.geekbrains.lesson2.R
import ru.geekbrains.lesson2.databinding.ActivityMainBinding
import ru.geekbrains.lesson2.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
    get(){
        return _binding!!
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