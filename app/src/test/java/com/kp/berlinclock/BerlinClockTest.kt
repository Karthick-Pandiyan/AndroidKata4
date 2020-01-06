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

    @Test
    fun `Given getSingleMinuteRow function returns "YYYY" when minute is "59"`(){
        Assert.assertEquals("YYYY", getSingleMinuteRow(59))
    }

    @Test
    fun `Given getSingleMinuteRow function not returns "YYYY" when minute is "32"`(){
        Assert.assertNotEquals("YYYY", getSingleMinuteRow(32))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "YYOO" when minute is "32"`(){
        Assert.assertEquals("YYOO", getSingleMinuteRow(32))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "YYYY" when minute is "34"`(){
        Assert.assertEquals("YYYY", getSingleMinuteRow(34))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "OOOO" when minute is "35"`(){
        Assert.assertEquals("OOOO", getSingleMinuteRow(35))
    }

    @Test
    fun `Given getFiveMinutesRow function returns unexpected five minutes row when minutes are passed`(){
        Assert.assertNotEquals("YYRYYRYYRYY", getFiveMinutesRow(0))
        Assert.assertNotEquals("YYRYYRYYRYY", getFiveMinutesRow(4))
        Assert.assertNotEquals("OOOOOOOOOOO", getFiveMinutesRow(23))
        Assert.assertNotEquals("YYRYYRYYRYY", getFiveMinutesRow(35))
    }

    @Test
    fun `Given getFiveMinutesRow function returns expected five minutes row when minutes are passed`(){
        Assert.assertEquals("OOOOOOOOOOO", getFiveMinutesRow(0))
        Assert.assertEquals("YYRYYRYYRYY", getFiveMinutesRow(59))
        Assert.assertEquals("OOOOOOOOOOO", getFiveMinutesRow(4))
        Assert.assertEquals("YYRYOOOOOOO", getFiveMinutesRow(23))
        Assert.assertEquals("YYRYYRYOOOO", getFiveMinutesRow(35))
    }

    @Test
    fun `Given getSingleHourRow function returns unexpected Single hour row when hour is passed`(){
        Assert.assertNotEquals("RRRR", getSingleHourRow(0))
        Assert.assertNotEquals("RRRR", getSingleHourRow(23))
        Assert.assertNotEquals("RRRR", getSingleHourRow(2))
        Assert.assertNotEquals("RRRR", getSingleHourRow(8))
    }

    @Test
    fun `Given getSingleHourRow function returns expected Single hour row when hour is passed`(){
        Assert.assertEquals("OOOO", getSingleHourRow(0))
        Assert.assertEquals("RRRO", getSingleHourRow(23))
        Assert.assertEquals("RROO", getSingleHourRow(2))
        Assert.assertEquals("RRRO", getSingleHourRow(8))
        Assert.assertEquals("RRRR", getSingleHourRow(14))
    }

    @Test
    fun `Given getFiveHoursRow function returns unexpected Five hour row when hour is passed`(){
        Assert.assertNotEquals("RRRR", getFiveHoursRow(0))
        Assert.assertNotEquals("RRRR", getFiveHoursRow(2))
        Assert.assertNotEquals("RRRR", getFiveHoursRow(8))
        Assert.assertNotEquals("RRRR", getFiveHoursRow(16))
    }

    @Test
    fun `Given getFiveHoursRow function returns expected Five hours row when hour is passed`(){
        Assert.assertEquals("OOOO", getFiveHoursRow(0))
        Assert.assertEquals("RRRR", getFiveHoursRow(23))
        Assert.assertEquals("OOOO", getFiveHoursRow(2))
        Assert.assertEquals("ROOO", getFiveHoursRow(8))
        Assert.assertEquals("RRRO", getFiveHoursRow(16))
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

    private fun getFiveMinutesRow(minutes: Int): String {
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

    private fun getSingleHourRow(hours: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until hours % 5) {
            result.replace(i, i + 1, "R")
        }
        return result.toString()
    }

    private fun getFiveHoursRow(hours: Int): String {
        val result = StringBuilder("OOOO")

        for (i in 0 until hours / 5) {
            result.replace(i, i + 1, "R")
        }
        return result.toString()
    }

    private fun getSecondsLamp(seconds: Int): String {
        return if (seconds % 2 == 0) {
            "Y"
        } else "O"
    }
}