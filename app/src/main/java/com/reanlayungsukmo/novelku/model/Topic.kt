package com.reanlayungsukmo.novelku.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    @StringRes val author: Int,
//    val availableCourses: Int,
    @DrawableRes val imageRes: Int,


)