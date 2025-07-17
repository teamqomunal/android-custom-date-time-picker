package com.qomunal.opensource.androidresearch.common.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by faisalamircs on 16/07/2025
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


const val ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val DEFAULT_FORMAT = "dd LLLL yyyy"
const val DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss"
const val DATE_TIME_FORMAT_NO_SECOND = "dd-MM-yyyy HH:mm"
const val DATE_TIME_FORMAT_NO_SECOND_TEXT = "dd LLLL yyyy HH:mm"

const val DATE_MONTH = "dd LLL"

const val MATERIAL_DATE_FORMAT = "MMM dd, yyyy"

const val BASE_DATE_FORMAT_DAY = "dd"
const val BASE_DATE_FORMAT_MONTH = "MM"
const val BASE_DATE_FORMAT_YEAR = "yyyy"

const val DATE_TIME_FORMAT_AM_PM = "dd MMMM yyyy, H.mm a"

const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy"
const val SIMPLE_DATE_FORMAT_YEAR_FIRST = "yyyy-MM-dd"
const val SIMPLE_DATE_FORMAT_YEAR_FIRST_NO_SECOND = "yyyy-MM-dd HH:mm"
const val SIMPLE_DATE_FORMAT_REVERSE = "yyyyMMdd"

const val TIME_FORMAT = "HH:mm"
const val TIME_SECOND_FORMAT = "HH:mm:ss"
const val TIME_AM_PM_FORMAT = "H.mm a"

fun getLocaleExt(): Locale {
    return Locale.getDefault()
}

fun String.getMillisExt(format: String = SIMPLE_DATE_FORMAT): Long {
    var date = Date()
    try {
        date = SimpleDateFormat(format, getLocaleExt()).parse(this) ?: Date()
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date.time
}

fun String.dateFormater(
    from: String = SIMPLE_DATE_FORMAT,
    to: String = DATE_TIME_FORMAT,
): String {
    val fromParser = SimpleDateFormat(from, Locale.getDefault())
    val toParser = SimpleDateFormat(to, Locale.getDefault())
    return toParser.format(fromParser.parse(this) as Date)
}