package com.example.tictactoe.activity

import android.content.Intent
import android.os.Bundle

import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity

import com.example.tictactoe.databinding.ActivitySplashBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashDuration : Long = 3 // 3 seconds
        val scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler.schedule({
            runOnUiThread {
                val anim = TranslateAnimation(
                    0f,
                    0f,
                    0f,
                    binding.splashLogo.height.toFloat()
                )
                anim.interpolator = AccelerateInterpolator()
                anim.duration = 1000 // Animation duration: 1 second
                anim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        goToNextActivity()
                    }
                    override fun onAnimationStart(animation: Animation) {}
                })
                binding.splashLogo.startAnimation(anim)
            }
        }, splashDuration, TimeUnit.SECONDS)
    }

    private fun goToNextActivity() {
        startActivity(Intent(this, TicTacToeActivity::class.java))
        finish()
    }
}
