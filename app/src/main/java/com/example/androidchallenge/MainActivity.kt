package com.example.androidchallenge


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val text_humidity: TextView = findViewById(R.id.text_humidity)
        val text_wind_speed: TextView = findViewById(R.id.text_wind_speed)
        val text_preciptation: TextView = findViewById(R.id.text_preciptation)
        val text_weather_status: TextView = findViewById(R.id.text_weather_status)
        val text_max_temperature: TextView = findViewById(R.id.text_max_temperature)
        val text_min_temperature: TextView = findViewById(R.id.text_min_temperature)
        val text_current_temperature: TextView = findViewById(R.id.text_current_temperature)
        var icon_weather_status: ImageView = findViewById(R.id.img_weather_type)

        text_humidity.text = "60%"
        text_wind_speed.text = "5 km/h"
        text_preciptation.text = "0 mm"
        text_weather_status.text = "Partly Cloudy"
        text_max_temperature.text = "34º"
        text_min_temperature.text = "18º"
        text_current_temperature.text = "25"
        icon_weather_status.setImageResource(R.drawable.ic_image_partlycloudy)


    }


}