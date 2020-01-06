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

    @Throws(ParseException::class)
    fun verifyTimeFormat(time: String) {
        if(TimeValidator.getInstance().validate(time, "HH:mm:ss", Locale.forLanguageTag("de-DE")) == null) {
                throw IllegalArgumentException("Cannot parse time - $time")
        }
    }
}