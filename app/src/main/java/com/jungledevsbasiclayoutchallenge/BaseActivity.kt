package com.jungledevsbasiclayoutchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
