package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.tictactoe.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TicTacToeScreenFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var activePlayer = 1 // 1 for X, 2 for O
    private var gameState = Array(3) { IntArray(3) }  // 0 for empty, 1 for X, 2 for O


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button00.setOnClickListener { cellClicked(it) }
        binding.button01.setOnClickListener { cellClicked(it) }
        binding.button02.setOnClickListener { cellClicked(it) }
        binding.button10.setOnClickListener { cellClicked(it) }
        binding.button11.setOnClickListener { cellClicked(it) }
        binding.button12.setOnClickListener { cellClicked(it) }
        binding.button20.setOnClickListener { cellClicked(it) }
        binding.button21.setOnClickListener { cellClicked(it) }
        binding.button22.setOnClickListener { cellClicked(it) }
    }

    private fun checkWinner(): Boolean {
        // Check rows, columns, and diagonals
        // Return true if there's a winner, else false
        // (Implement your logic here)

        return false
    }

    private fun initializeGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                gameState[i][j] = 0
            }
        }
        activePlayer = 1
        // Reset all buttons to empty

        val buttons = listOf(
            binding.button00, binding.button01, binding.button02,
            binding.button10, binding.button11, binding.button12,
            binding.button20, binding.button21, binding.button22
        )

        for (button in buttons) {
            button.text = ""
        }
    }

    fun cellClicked(view: View) {
        val button = view as Button
        val row = button.tag.toString()[0].toString().toInt()
        val col = button.tag.toString()[1].toString().toInt()

        if (gameState[row][col] == 0) {
            gameState[row][col] = activePlayer
            button.text = if (activePlayer == 1) "X" else "O"

            if (checkWinner()) {
                Toast.makeText(context, "Player $activePlayer wins!", Toast.LENGTH_SHORT).show()
                initializeGame()
                return
            }

            activePlayer = 3 - activePlayer  // switch player
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}