package com.example.leclevietnam.demoeverything.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun convertDateToString(
            date: Date,
            format: String,
            locale: Locale = Locale.getDefault()): String {
        val simpleDateFormat = SimpleDateFormat(format, locale)

        return simpleDateFormat.format(date)
    }

    fun convertStringToDate(
            string: String,
            format: String,
            locale: Locale = Locale.getDefault()): Date {

        if (string.isEmpty())
            return Date()

        val simpleDateFormat = SimpleDateFormat(format, locale)

        return simpleDateFormat.parse(string)
    }
}