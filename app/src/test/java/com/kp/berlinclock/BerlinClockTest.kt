package com.kp.berlinclock

import org.apache.commons.validator.routines.TimeValidator
import org.junit.Test
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.util.*

class BerlinClockTest {


    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when "abcde" passed`(){
        verifyTimeFormat("abcde")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid time format passed`(){
        verifyTimeFormat("21:30")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid hours passed`(){
        verifyTimeFormat("40:30:00")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid minutes passed`(){
        verifyTimeFormat("12:61:00")
    }

    @Throws(ParseException::class)
    fun verifyTimeFormat(time: String) {
        if(TimeValidator.getInstance().validate(time, "HH:mm:ss", Locale.forLanguageTag("de-DE")) == null) {
                throw IllegalArgumentException("Cannot parse time - $time")
        }
    }
}