package com.kp.berlinclock

import org.apache.commons.validator.routines.TimeValidator
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.util.*

class BerlinClockTest {

    @Throws(ParseException::class)
    fun verifyTimeFormat(time: String) {
        if(TimeValidator.getInstance().validate(time, "HH:mm:ss", Locale.forLanguageTag("de-DE")) == null) {
                throw IllegalArgumentException("Cannot parse time - $time")
        }
    }
}