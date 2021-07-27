package com.mvvmhilt.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.mvvmhilt.databinding.SplashLayoutBinding
import com.mvvmhilt.ui.base.BaseActivity
import com.mvvmhilt.ui.component.login.LoginActivity
import com.mvvmhilt.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by navdeepera04
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity(){

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
