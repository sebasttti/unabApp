package com.sebastianjoya.unabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityExampleBinding


class ExampleActivity : AppCompatActivity() {

    lateinit var binding: ActivityExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_example)

        title = "ACTIVIDAD DE EJEMPLO"

    }
}