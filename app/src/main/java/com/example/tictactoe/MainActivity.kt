package com.example.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            // Initial transaction to add the fragment
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, TicTacToeScreenFragment())
                .commit()
        }
    }

}