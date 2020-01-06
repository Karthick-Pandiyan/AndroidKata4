package com.kp.berlinclock

import org.apache.commons.validator.routines.TimeValidator
import org.junit.Assert
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

    @Test(expected = Test.None::class)
    fun `Given verifyTimeFormat function returns no invalid format exception when valid time format passed`(){
        verifyTimeFormat("12:00:00")
    }

    @Test
    fun `Given getSingleMinuteRow function not returns "0000" when minute is "59"`(){
        Assert.assertNotEquals("OOOO", getSingleMinuteRow(59))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "0000" when minute is "00"`(){
        Assert.assertEquals("OOOO", getSingleMinuteRow(0))
    }

    @Throws(ParseException::class)
    fun verifyTimeFormat(time: String) {
        if(TimeValidator.getInstance().validate(time, "HH:mm:ss", Locale.forLanguageTag("de-DE")) == null) {
                throw IllegalArgumentException("Cannot parse time - $time")
        }
    }

    private fun getSingleMinuteRow(minutes: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until minutes % 5) {
            result.replace(i, i + 1, "Y")
        }
        return result.toString()
    }
}