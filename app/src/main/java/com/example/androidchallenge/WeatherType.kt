package com.example.androidchallenge

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


/*
* This is a example of a enum class that could improve your code base!
*
* Enum classes are really useful when you have closed options of something. In our case, we have close
* options of the weather type! One type for a drawable resource, for instance!
*
* you could add parameters on the enum which you could use to set all the resources id that you want!
* */
enum class WeatherType(
    @StringRes val nameRes: Int,
    @DrawableRes val backgrouRes: Int,
    @ColorRes val lightColor: Int,
    @ColorRes val darkColor: Int
) {
    SUNNY(R.string.sunny, R.drawable.gradient_background, R.color.light_blue_sunny, R.color.dark_blue_sunny),
    RAIN(R.string.rain, R.drawable.gradient_background_medium, R.color.light_blue_cloudy, R.color.dark_blue_cloudy),
    MOSTLY_CLOUDLY(R.string.mostly_cloudly, R.drawable.gradient_background, R.color.light_blue_sunny, R.color.dark_blue_sunny),
    PARTLY_CLOUDLY(R.string.partly_cloudly, R.drawable.gradient_background, R.color.light_blue_sunny, R.color.dark_blue_sunny),
    THUNDERSTORM(R.string.thunderStorm, R.drawable.gradient_background_dark, R.color.light_blue_storm, R.color.dark_blue_storm),
    SHOWERS(R.string.showers, R.drawable.gradient_background_medium, R.color.light_blue_cloudy, R.color.dark_blue_cloudy),
    CLOUDLY(R.string.cloudly, R.drawable.gradient_background_medium, R.color.light_blue_cloudy, R.color.dark_blue_cloudy)
}