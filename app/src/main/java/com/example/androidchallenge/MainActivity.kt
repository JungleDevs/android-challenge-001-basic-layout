package com.example.androidchallenge


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.content.res.ResourcesCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val list_weather_status = listOf(R.string.sunny, R.string.rain, R.string.mostly_cloudly, R.string.partly_cloudly,
            R.string.thunderStorm, R.string.showers,R.string.cloudly)
    var status_today: Int = 0
    var humidity_today: Int = 0
    var wind_speed_today = 0
    var preciptation_today = 0
    var max_today = 0
    var min_today = 0
    var current_today = 0

    var status_tomorrow = 0
    var humidity_tomorrow = 0
    var wind_speed_tomorrow = 0
    var preciptation_tomorrow = 0
    var max_tomorrow = 0
    var min_tomorrow= 0
    var current_tomorrow = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide();
        getToday()
        getTomorrow()
        setToday()

        val toggle_tomorrow = findViewById<ToggleButton>(R.id.button_tomorrow);
        val toggle_today = findViewById<ToggleButton>(R.id.button_today)
        toggle_today.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                if(toggle_tomorrow.isChecked == false)
                    toggle_today.isChecked = true;
            } else {
                setToday()
                toggle_tomorrow.isChecked = false
            }
        }

             toggle_tomorrow.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked ) {
                if(toggle_today.isChecked == false)
                    toggle_tomorrow.isChecked = true;
            } else {
                toggle_today.isChecked = false
                setTomorrow()

            }

        }
    }


    fun setWeather(humidity: Int, wind_speed: Int, preciptation: Int, status: Int, max: Int, min: Int, current: Int){

        findViewById<TextView>(R.id.text_humidity)?.text = humidity.toString() + getText(R.string.percent)
        findViewById<TextView>(R.id.text_wind_speed)?.text = wind_speed.toString() + getText(R.string.kmh)
        findViewById<TextView>(R.id.text_preciptation)?.text = preciptation.toString() + getText(R.string.mm)
        findViewById<TextView>(R.id.text_weather_status)?.text = getString(status)
        findViewById<TextView>(R.id.text_max_temperature)?.text = max.toString() + getText(R.string.degree)
        findViewById<TextView>(R.id.text_min_temperature)?.text = min.toString() + getText(R.string.degree)
        findViewById<TextView>(R.id.text_current_temperature)?.text = current.toString()
        if(status == R.string.sunny){
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_sunny)
            setLightLayout()
        }else if(status == R.string.cloudly) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_cloudy)
            setMediumLayout()
        }
        else if(status == R.string.partly_cloudly) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_partlycloudy)
            setLightLayout()
        }
        else if(status == R.string.mostly_cloudly) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_mostlycloudy)
            setLightLayout()
        }
        else if(status == R.string.rain) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_rain)
            setMediumLayout()
        }
        else if(status == R.string.showers) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_showers)
            setMediumLayout()
        }
        else if(status == R.string.thunderStorm) {
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_thunderstorm)
            setDarkLayout()
        }

    }

    fun setDarkLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background_dark)
       this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_storm, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_storm, null)
    }

    fun setLightLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_sunny, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_sunny, null)
    }

    fun setMediumLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background_medium)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_cloudy, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_cloudy, null)
    }


    fun getToday(){
        status_today = list_weather_status.random()
        if(status_today == R.string.sunny) {
            humidity_today = Random.nextInt(0, 80)
            wind_speed_today = Random.nextInt(0, 50)
            preciptation_today = Random.nextInt(0, 5)
            current_today = Random.nextInt(0, 40)
        }
        else if(status_today == R.string.thunderStorm){
            humidity_today = Random.nextInt(70, 100)
            wind_speed_today = Random.nextInt(10, 100)
            preciptation_today = Random.nextInt(20, 100)
            current_today = Random.nextInt(0, 30)
        }
        else if(status_today == R.string.rain || status_today == R.string.showers){
            humidity_today = Random.nextInt(20, 100)
            wind_speed_today = Random.nextInt(0, 30)
            preciptation_today = Random.nextInt(10, 100)
            current_today = Random.nextInt(0, 30)
        }
        else {
            humidity_today = Random.nextInt(0, 100)
            wind_speed_today = Random.nextInt(0, 50)
            preciptation_today = Random.nextInt(0, 50)
            current_today = Random.nextInt(0, 35)
        }

        max_today = Random.nextInt(current_today, current_today + 10)
        min_today = Random.nextInt(current_today - 10,current_today)
    }


    fun getTomorrow(){
        status_tomorrow = list_weather_status.random()
        if(status_tomorrow == R.string.sunny) {
            humidity_tomorrow = Random.nextInt(0, 80)
            wind_speed_tomorrow = Random.nextInt(0, 50)
            preciptation_tomorrow = Random.nextInt(0, 5)
            current_tomorrow = Random.nextInt(0, 40)
        }
        else if(status_tomorrow == R.string.thunderStorm){
            humidity_tomorrow = Random.nextInt(70, 100)
            wind_speed_tomorrow = Random.nextInt(10, 100)
            preciptation_tomorrow = Random.nextInt(20, 100)
            current_tomorrow = Random.nextInt(0, 30)
        }
        else if(status_tomorrow == R.string.rain || status_tomorrow == R.string.showers){
            humidity_tomorrow = Random.nextInt(20, 100)
            wind_speed_tomorrow = Random.nextInt(0, 30)
            preciptation_tomorrow = Random.nextInt(10, 100)
            current_tomorrow = Random.nextInt(0, 30)
        }
        else {
            humidity_tomorrow = Random.nextInt(0, 100)
            wind_speed_tomorrow = Random.nextInt(0, 50)
            preciptation_tomorrow = Random.nextInt(0, 50)
            current_tomorrow = Random.nextInt(0, 35)
        }

        max_tomorrow = Random.nextInt(current_tomorrow, current_tomorrow + 10)
        min_tomorrow = Random.nextInt(current_tomorrow - 10,current_tomorrow)
    }
    
    fun setToday(){
        setWeather(humidity_today, wind_speed_today, preciptation_today, status_today, max_today, min_today, current_today)
    }

    fun setTomorrow(){
        setWeather(humidity_tomorrow, wind_speed_tomorrow, preciptation_tomorrow, status_tomorrow, max_tomorrow, min_tomorrow, current_tomorrow)
    }




}