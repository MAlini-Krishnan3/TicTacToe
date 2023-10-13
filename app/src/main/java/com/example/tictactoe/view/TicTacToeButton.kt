package com.example.tictactoe.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.tictactoe.R

class TicTacToeButton : AppCompatButton {
    enum class ButtonColor {
        RED, YELLOW, DEFAULT
    }

    var buttonColor: ButtonColor = ButtonColor.DEFAULT
        set(value) {
            field = value
            updateBackgroundColor()
        }

    constructor(context: Context) : super(context) {
        initializeButton(null)
    }

    // Constructor for use in XML layouts
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeButton(attrs)
    }

    // Constructor needed for certain XML attributes
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeButton(attrs)
    }

    private fun initializeButton(attrs: AttributeSet?) {
//        val tf = ResourcesCompat.getFont(context, R.font.comic_neue_bold)
        val tf = resources.getFont(R.font.comic_neue_regular)
        this.background = resources.getDrawable(R.drawable.button_3d)
        this.typeface = tf
        this.setTextColor(resources.getColor(R.color.white))
        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(
                it,
                R.styleable.TicTacToeButton,
                0, 0
            )
            try {
                val backgroundColorOption =
                    typedArray.getInt(R.styleable.TicTacToeButton_buttonColor, 0)
                when (backgroundColorOption) {
                    1 -> setBackgroundColor(ContextCompat.getColor(context, R.color.yellow)) //for X
                    2 -> setBackgroundColor(ContextCompat.getColor(context, R.color.red)) // for 0
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun updateBackgroundColor() {
        when (buttonColor) {
            ButtonColor.RED -> setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.red
                )
            ) //for 0
            ButtonColor.YELLOW -> setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.yellow
                )
            ) // for X
            ButtonColor.DEFAULT -> this.background = resources.getDrawable(R.drawable.button_3d)
        }
    }

}