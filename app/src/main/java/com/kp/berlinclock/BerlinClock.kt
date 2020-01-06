package com.kp.berlinclock

import org.apache.commons.validator.routines.TimeValidator
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.util.*

class BerlinClock {


    @Throws(ParseException::class)
    fun transformToBerlinTime(time: String): String {
        verifyTimeFormat(time)
        // retrieve hours
        val timeComponents = time.split(":")
        val hours = Integer.parseInt(timeComponents[0])
        val minutes = Integer.parseInt(timeComponents[1])
        val seconds = Integer.parseInt(timeComponents[2])
        return getSecondsLamp(seconds) +
                getFiveHoursRow(hours) +
                getSingleHourRow(hours) +
                getFiveMinutesRow(minutes) +
                getSingleMinuteRow(minutes)
    }

    @Throws(ParseException::class)
    fun verifyTimeFormat(time: String) {
        if(TimeValidator.getInstance().validate(time, "HH:mm:ss", Locale.forLanguageTag("de-DE")) == null) {
            throw IllegalArgumentException("Cannot parse time - $time")
        }
    }

    fun getSingleMinuteRow(minutes: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until minutes % 5) {
            result.replace(i, i + 1, "Y")
        }
        return result.toString()
    }

    fun getFiveMinutesRow(minutes: Int): String {
        val result = StringBuilder("OOOOOOOOOOO")

        for (i in 0 until minutes / 5) {
            if ((i + 1) % 3 == 0) {
                result.replace(i, i + 1, "R")
            } else {
                result.replace(i, i + 1, "Y")
            }
        }
        return result.toString()
    }

    fun getSingleHourRow(hours: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until hours % 5) {
            result.replace(i, i + 1, "R")
        }
        return result.toString()
    }

    fun getFiveHoursRow(hours: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until hours / 5) {
            result.replace(i, i + 1, "R")
        }
        return result.toString()
    }

    fun getSecondsLamp(seconds: Int): String {
        return if (seconds % 2 == 0) {
            "Y"
        } else "O"
    }
}