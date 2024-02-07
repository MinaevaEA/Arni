package com.arni.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.Dp
import java.time.LocalTime
import java.time.Month

object CalendarUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getStandaloneMonthName(month: Month): String = when (month) {
        Month.JANUARY -> "Январь"
        Month.FEBRUARY -> "Февраль"
        Month.MARCH -> "Март"
        Month.APRIL -> "Апрель"
        Month.MAY -> "Май"
        Month.JUNE -> "Июнь"
        Month.JULY -> "Июль"
        Month.AUGUST -> "Август"
        Month.SEPTEMBER -> "Сентябрь"
        Month.OCTOBER -> "Октябрь"
        Month.NOVEMBER -> "Ноябрь"
        Month.DECEMBER -> "Декабрь"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthToInt(month: String): Int = when (month) {
        "Январь" -> Month.JANUARY.value
        "Февраль" -> Month.FEBRUARY.value
        "Март" -> Month.MARCH.value
        "Апрель" -> Month.APRIL.value
        "Май" -> Month.MAY.value
        "Июнь" -> Month.JUNE.value
        "Июль" -> Month.JULY.value
        "Август" -> Month.AUGUST.value
        "Сентябрь" -> Month.SEPTEMBER.value
        "Октябрь" -> Month.OCTOBER.value
        "Ноябрь" -> Month.NOVEMBER.value
        "Декабрь" -> Month.DECEMBER.value
        else -> throw IllegalArgumentException()
    }

    fun getHoursSuffix(hours: Long): String {
        return when {
            hours % 10 == 1L && hours % 100 != 11L -> "час"
            hours % 10 in 2..4 && (hours % 100 < 10 || hours % 100 >= 20) -> "часа"
            else -> "часов"
        }
    }

    /**
     * Метод для определения правильного падежа для слова "минута"
     */
    fun getMinutesSuffix(minutes: Long): String {
        return when {
            minutes % 10 == 1L && minutes % 100 != 11L -> "минута"
            minutes % 10 in 2..4 && (minutes % 100 < 10 || minutes % 100 >= 20) -> "минуты"
            else -> "минут"
        }
    }

    fun findCurrentTimeIndicatorOffset(cellHeight: Dp): Dp {
        val now = LocalTime.now()
        val startHour = now.hour.toFloat()
        val startMinute = now.minute.toFloat()

        val start = startHour + startMinute / 60

        return cellHeight.times(start)
    }
}

