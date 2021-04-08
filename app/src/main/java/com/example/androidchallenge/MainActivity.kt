package com.example.androidchallenge


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

     /*
     * 1) set as private since you are only accessing in this activity
     * 2) naming conventions on Kotlin for variable is lower camel case
     *  - in this case [list_weather_status] should be listWeatherStatus
     * 3) we usually skip one line if the list is to long
     * */
    private val listWeatherStatus = listOf(
        R.string.sunny,
        R.string.rain,
        R.string.mostly_cloudly,
        R.string.partly_cloudly,
        R.string.thunderStorm,
        R.string.showers,
        R.string.cloudly
    )

    /*
    * same for all this variables: private and lower camel case
    * */
    var status_today: Int = 0 // you could use (shift + F6) shortcut to rename the variable in all the file
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
                /*
                * It could ve simplified since [isChecked] is already a non-nullable boolean.
                * */
                // if(toggle_tomorrow.isChecked == false)
                if(!toggle_tomorrow.isChecked)
                    toggle_today.isChecked = true;
            } else {
                setToday()
                toggle_tomorrow.isChecked = false
            }
        }

             toggle_tomorrow.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked ) {
                // same here
//                if(toggle_today.isChecked == false)
                if(!toggle_today.isChecked)
                    toggle_tomorrow.isChecked = true;
            } else {
                toggle_today.isChecked = false
                setTomorrow()

            }

        }
    }

    /*
    * the privacy comment applies for functions too.
    * if it's only used by this activity, it should be private
    * */
    private fun setWeather(humidity: Int, wind_speed: Int, preciptation: Int, status: Int, max: Int, min: Int, current: Int){

        /*
        * Android let's you use the old C pattern to format strings.
        *
        * instead of using getString to get the percent symbol. you could create a string with the
        * symbol you want and pass the value as argument to [getString]
        *
        * the line:
        * findViewById<TextView>(R.id.text_humidity)?.text = humidity.toString() + getText(R.string.percent)
        * could be replaced by:
        *
        * */
        findViewById<TextView>(R.id.text_humidity)?.text = getString(R.string.n_percent, humidity)
        findViewById<TextView>(R.id.text_wind_speed)?.text = wind_speed.toString() + getText(R.string.kmh)
        findViewById<TextView>(R.id.text_preciptation)?.text = preciptation.toString() + getText(R.string.mm)
        findViewById<TextView>(R.id.text_weather_status)?.text = getString(status)
        findViewById<TextView>(R.id.text_max_temperature)?.text = max.toString() + getText(R.string.degree)
        findViewById<TextView>(R.id.text_min_temperature)?.text = min.toString() + getText(R.string.degree)
        findViewById<TextView>(R.id.text_current_temperature)?.text = current.toString()

        /*
        * Kotlin has a AMAZING feature that let's you simplify code like this.
        *
        * It's the "switch case" from kotlin but it's very helpful when dealing with nullability
        * (and with sealed classes)
        *
        * QUESTION: did the course on the challenge presented the when conditional?
        *
        * */
//        if(status == R.string.sunny){
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_sunny)
//            setLightLayout()
//        }else if(status == R.string.cloudly) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_cloudy)
//            setMediumLayout()
//        }
//        else if(status == R.string.partly_cloudly) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_partlycloudy)
//            setLightLayout()
//        }
//        else if(status == R.string.mostly_cloudly) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_mostlycloudy)
//            setLightLayout()
//        }
//        else if(status == R.string.rain) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_rain)
//            setMediumLayout()
//        }
//        else if(status == R.string.showers) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_showers)
//            setMediumLayout()
//        }
//        else if(status == R.string.thunderStorm) {
//            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_thunderstorm)
//            setDarkLayout()
//        }

        /*
        * Also, notice that you call the same line:
        * [findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_sunny)]
        * in all cases, the only thing different is the image resource you're passing.
        *
        *
        * [when] lets us improve it even more as it returns a value for each case!
        * */
//        when (status) {
//            R.string.sunny -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_sunny)
//                setLightLayout()
//            }
//            R.string.cloudly -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_cloudy)
//                setMediumLayout()
//            }
//            R.string.partly_cloudly -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_partlycloudy)
//                setLightLayout()
//            }
//            R.string.mostly_cloudly -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_mostlycloudy)
//                setLightLayout()
//            }
//            R.string.rain -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_rain)
//                setMediumLayout()
//            }
//            R.string.showers -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_showers)
//                setMediumLayout()
//            }
//            R.string.thunderStorm -> {
//                findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(R.drawable.ic_image_thunderstorm)
//                setDarkLayout()
//            }
//            else -> {} // do nothing if it's any other value
//        }

        val imageRes: Int? = when (status) {
            R.string.sunny -> {
                setLightLayout()
                R.drawable.ic_image_sunny
            }
            R.string.cloudly -> {
                setMediumLayout()
                R.drawable.ic_image_cloudy
            }
            R.string.partly_cloudly -> {
                setLightLayout()
                R.drawable.ic_image_partlycloudy
            }
            R.string.mostly_cloudly -> {
                setLightLayout()
                R.drawable.ic_image_mostlycloudy
            }
            R.string.rain -> {
                setMediumLayout()
                R.drawable.ic_image_rain
            }
            R.string.showers -> {
                setMediumLayout()
                R.drawable.ic_image_showers
            }
            R.string.thunderStorm -> {
                setDarkLayout()
                R.drawable.ic_image_thunderstorm
            }
            else -> null // returns null for any other id
        }

        /*
        * Since the upper [when] it's used to return a value (other than Unit) it must have a default
        * case, in the upper example we are returning null.
        *
        * The problem is that we can't pass a null id for [setImageResource]
        *
        * 1 - (it will not compile)
        * findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(imageRes)
        *
        * */

        /*
        * you can user [let] to avoid nullability problems
        *
        * [let] calls a function (inside the brackets) by passing the "caller" as argument. The [?]
        * operator will ensure that [imageRes] is not nullable
        * */

        imageRes?.let { imageRes ->
            findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(imageRes)
        }
        // same as above but naming the argument in [let]
        // imageRes?.let { imageRes -> findViewById<ImageView>(R.id.img_weather_type)?.setImageResource(imageRes) }

        /*
        * we could avoid returning null (or any other value) if we "close" the status of [status]!
        *
        * status could have been an enum class (see WeatherType class)
        *
        * */
    }

    /*
    * notice that the three methods below have the same behaviour:
    *   - set the background;
    *   - set status bar color, and;
    *   - set navigation bar color.
    *
    * you could create a single method setColors for example.
    *
    * */

    // make it private
    private fun setDarkLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background_dark)
       this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_storm, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_storm, null)
    }

    // make it private
    private fun setLightLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_sunny, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_sunny, null)
    }

    // make it private
    private fun setMediumLayout(){
        findViewById<View>(R.id.main_activity).setBackgroundResource(R.drawable.gradient_background_medium)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, R.color.light_blue_cloudy, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, R.color.dark_blue_cloudy, null)
    }

    /*
    * this method could replace all the three above
    * */
    private fun setColors(@DrawableRes drawableRes: Int, @ColorRes lightColor: Int, @ColorRes darkColor: Int) {
        findViewById<View>(R.id.main_activity).setBackgroundResource(drawableRes)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, lightColor, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, darkColor, null)
    }

    /*
    * Even better than this... if you use the power of WeatherType you could make it in a way that you
    * would only need the weather type to set the colors.
    * */
    private fun setColors(weatherType: WeatherType) {
        findViewById<View>(R.id.main_activity).setBackgroundResource(weatherType.backgrouRes)
        this.window.statusBarColor = ResourcesCompat.getColor(resources, weatherType.lightColor, null)
        this.window.navigationBarColor = ResourcesCompat.getColor(resources, weatherType.darkColor, null)
    }


    // make it private
    private fun getToday(){
        status_today = listWeatherStatus.random()
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

    // make it private
    private fun getTomorrow(){
        status_tomorrow = listWeatherStatus.random()
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

    // make it private
    private fun setToday(){
        setWeather(humidity_today, wind_speed_today, preciptation_today, status_today, max_today, min_today, current_today)
    }

    // make it private
    private fun setTomorrow(){
        setWeather(humidity_tomorrow, wind_speed_tomorrow, preciptation_tomorrow, status_tomorrow, max_tomorrow, min_tomorrow, current_tomorrow)
    }

}