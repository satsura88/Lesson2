package ru.geekbrains.lesson2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.lesson2.R
import ru.geekbrains.lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv
    }
}