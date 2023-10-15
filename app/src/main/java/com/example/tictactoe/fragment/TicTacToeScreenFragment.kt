package com.example.tictactoe.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.tictactoe.view.EnumCodes.ButtonColor
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentFirstBinding
import com.example.tictactoe.view.TicTacToeButton

class TicTacToeScreenFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var activePlayer = 1 // 1 for X, 2 for O
    private var gameState = Array(3) { IntArray(3) }  // 0 for empty, 1 for X, 2 for O


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var scaleUp: Animation? = null
    private var scaleDown: Animation? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        binding.ticTactToeFrag = this

        scaleUp = AnimationUtils.loadAnimation(context, R.anim.pop_scale_up)
        scaleDown = AnimationUtils.loadAnimation(context, R.anim.pop_scale_down)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun checkWinner(): Boolean {
        // Check rows, columns, and diagonals
        // Return true if there's a winner, else false
        // Check rows
        for (i in 0..2) {
            if (gameState[i][0] == gameState[i][1] &&
                gameState[i][1] == gameState[i][2] &&
                gameState[i][0] != 0
            ) {
                return true
            }
        }
        // Check columns
        for (i in 0..2) {
            if (gameState[0][i] == gameState[1][i] &&
                gameState[1][i] == gameState[2][i] &&
                gameState[0][i] != 0
            ) {
                return true
            }
        }
        // Check diagonals
        if (gameState[0][0] == gameState[1][1] &&
            gameState[1][1] == gameState[2][2] &&
            gameState[0][0] != 0
        ) {
            return true
        }
        if (gameState[0][2] == gameState[1][1] &&
            gameState[1][1] == gameState[2][0] &&
            gameState[0][2] != 0
        ) {
            return true
        }

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
            button.buttonColor = ButtonColor.DEFAULT
        }
    }

    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (gameState[i][j] == 0) {
                    return false
                }
            }
        }
        return true
    }

    //onClick function
    fun cellClicked(view: View) {
        val button = view as TicTacToeButton
        //animation of pop up and down when clicked:
        button.let {

            scaleUp?.let { scaleUpAnim ->
                it.startAnimation(scaleUpAnim)
            }

            // Delay and then scale down
            it.postDelayed({
                scaleDown?.let { scaleDownAnim ->
                    it.startAnimation(scaleDownAnim)
                }
            }, 150)
        }

        val row = button.tag.toString()[0].toString().toInt()
        val col = button.tag.toString()[1].toString().toInt()

        if (gameState[row][col] == 0) {
            gameState[row][col] = activePlayer
            if (activePlayer == 1) {
                button.text = "X"
                button.buttonColor = ButtonColor.YELLOW
            } else {
                button.text = "0"
                button.buttonColor = ButtonColor.RED
            }

            if (checkWinner()) {
                Toast.makeText(context, "Player $activePlayer wins!", Toast.LENGTH_SHORT).show()
                initializeGame()
                return
            }

            if (isBoardFull()) {
                Toast.makeText(context, "It's a draw!", Toast.LENGTH_SHORT).show()
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