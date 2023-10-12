package com.example.tictactoe.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.R


//class TicTacToeButton {
//}

class TicTacToeButton : AppCompatButton {


    constructor(context: Context) : super(context) {
        initializeButton()
    }

    // Constructor for use in XML layouts
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initializeButton()
    }

    // Constructor needed for certain XML attributes
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initializeButton()
    }

    private fun initializeButton() {
//        val tf = ResourcesCompat.getFont(context, R.font.comic_neue_bold)
        val tf = resources.getFont(R.font.comic_neue_regular)
        this.typeface = tf
    }

}