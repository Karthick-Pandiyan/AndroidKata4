package com.kp.berlinclock

import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException


class BerlinClockTest {

    val berlinClock = BerlinClock()

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when "abcde" passed`(){
        berlinClock.verifyTimeFormat("abcde")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid time format passed`(){
        berlinClock.verifyTimeFormat("21:30")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid hours passed`(){
        berlinClock.verifyTimeFormat("40:30:00")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given verifyTimeFormat function returns invalid format exception when invalid minutes passed`(){
        berlinClock.verifyTimeFormat("12:61:00")
    }

    @Test(expected = Test.None::class)
    fun `Given verifyTimeFormat function returns no invalid format exception when valid time format passed`(){
        berlinClock.verifyTimeFormat("12:00:00")
    }

    @Test
    fun `Given getSingleMinuteRow function not returns "0000" when minute is "59"`(){
        Assert.assertNotEquals("OOOO", berlinClock.getSingleMinuteRow(59))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "0000" when minute is "00"`(){
        Assert.assertEquals("OOOO", berlinClock.getSingleMinuteRow(0))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "YYYY" when minute is "59"`(){
        Assert.assertEquals("YYYY", berlinClock.getSingleMinuteRow(59))
    }

    @Test
    fun `Given getSingleMinuteRow function not returns "YYYY" when minute is "32"`(){
        Assert.assertNotEquals("YYYY", berlinClock.getSingleMinuteRow(32))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "YYOO" when minute is "32"`(){
        Assert.assertEquals("YYOO", berlinClock.getSingleMinuteRow(32))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "YYYY" when minute is "34"`(){
        Assert.assertEquals("YYYY", berlinClock.getSingleMinuteRow(34))
    }

    @Test
    fun `Given getSingleMinuteRow function returns "OOOO" when minute is "35"`(){
        Assert.assertEquals("OOOO", berlinClock.getSingleMinuteRow(35))
    }

    @Test
    fun `Given getFiveMinutesRow function returns unexpected five minutes row when minutes are passed`(){
        Assert.assertNotEquals("YYRYYRYYRYY", berlinClock.getFiveMinutesRow(0))
        Assert.assertNotEquals("YYRYYRYYRYY", berlinClock.getFiveMinutesRow(4))
        Assert.assertNotEquals("OOOOOOOOOOO", berlinClock.getFiveMinutesRow(23))
        Assert.assertNotEquals("YYRYYRYYRYY", berlinClock.getFiveMinutesRow(35))
    }

    @Test
    fun `Given getFiveMinutesRow function returns expected five minutes row when minutes are passed`(){
        Assert.assertEquals("OOOOOOOOOOO", berlinClock.getFiveMinutesRow(0))
        Assert.assertEquals("YYRYYRYYRYY", berlinClock.getFiveMinutesRow(59))
        Assert.assertEquals("OOOOOOOOOOO", berlinClock.getFiveMinutesRow(4))
        Assert.assertEquals("YYRYOOOOOOO", berlinClock.getFiveMinutesRow(23))
        Assert.assertEquals("YYRYYRYOOOO", berlinClock.getFiveMinutesRow(35))
    }

    @Test
    fun `Given getSingleHourRow function returns unexpected Single hour row when hour is passed`(){
        Assert.assertNotEquals("RRRR", berlinClock.getSingleHourRow(0))
        Assert.assertNotEquals("RRRR", berlinClock.getSingleHourRow(23))
        Assert.assertNotEquals("RRRR", berlinClock.getSingleHourRow(2))
        Assert.assertNotEquals("RRRR", berlinClock.getSingleHourRow(8))
    }

    @Test
    fun `Given getSingleHourRow function returns expected Single hour row when hour is passed`(){
        Assert.assertEquals("OOOO", berlinClock.getSingleHourRow(0))
        Assert.assertEquals("RRRO", berlinClock.getSingleHourRow(23))
        Assert.assertEquals("RROO", berlinClock.getSingleHourRow(2))
        Assert.assertEquals("RRRO", berlinClock.getSingleHourRow(8))
        Assert.assertEquals("RRRR", berlinClock.getSingleHourRow(14))
    }

    @Test
    fun `Given getFiveHoursRow function returns unexpected Five hour row when hour is passed`(){
        Assert.assertNotEquals("RRRR", berlinClock.getFiveHoursRow(0))
        Assert.assertNotEquals("RRRR", berlinClock.getFiveHoursRow(2))
        Assert.assertNotEquals("RRRR", berlinClock.getFiveHoursRow(8))
        Assert.assertNotEquals("RRRR", berlinClock.getFiveHoursRow(16))
    }

    @Test
    fun `Given getFiveHoursRow function returns expected Five hours row when hour is passed`(){
        Assert.assertEquals("OOOO", berlinClock.getFiveHoursRow(0))
        Assert.assertEquals("RRRR", berlinClock.getFiveHoursRow(23))
        Assert.assertEquals("OOOO", berlinClock.getFiveHoursRow(2))
        Assert.assertEquals("ROOO", berlinClock.getFiveHoursRow(8))
        Assert.assertEquals("RRRO", berlinClock.getFiveHoursRow(16))
    }

    @Test
    fun `Given getSecondsLamp function returns unexpected seconds lamp when seconds are passed`() {
        Assert.assertNotEquals("O", berlinClock.getSecondsLamp(0))
        Assert.assertNotEquals("Y", berlinClock.getSecondsLamp(59))
    }

    @Test
    fun `Given getSecondsLamp function returns expected seconds lamp when seconds are passed`(){
        Assert.assertEquals("Y", berlinClock.getSecondsLamp(0))
        Assert.assertEquals("O", berlinClock.getSecondsLamp(59))
    }

    @Test
    fun `Given transformToBerlinTime functions should return Berlin clock when 0 hour, 0 Minute, 0 Second passed`(){
        Assert.assertEquals("YOOOOOOOOOOOOOOOOOOOOOOO", berlinClock.transformToBerlinTime("00:00:00"))
    }

    @Test
    fun `Given transformToBerlinTime functions should return Berlin clock when 23 hours, 59 Minutes, 59 Seconds passed`(){
        Assert.assertEquals("ORRRRRRROYYRYYRYYRYYYYYY", berlinClock.transformToBerlinTime("23:59:59"))
    }

    @Test
    fun `Given transformToBerlinTime functions should return Berlin clock when 16 hours, 50 Minutes, 06 Seconds passed`(){
        Assert.assertEquals("YRRROROOOYYRYYRYYRYOOOOO", berlinClock.transformToBerlinTime("16:50:06"))
    }

    @Test
    fun `Given transformToBerlinTime functions should return Berlin clock when 11 hours, 37 Minutes, 01 Second passed`(){
        Assert.assertEquals("ORROOROOOYYRYYRYOOOOYYOO", berlinClock.transformToBerlinTime("11:37:01"))
    }
}