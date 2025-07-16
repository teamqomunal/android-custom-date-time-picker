package com.qomunal.opensource.androidresearch.common.ext

import android.app.DatePickerDialog
import android.content.Context
import com.qomunal.opensource.androidresearch.common.view.CustomTimePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Created by faisalamircs on 02/07/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


fun Context.showDatePickerExt(
    isUsingTimePicker: Boolean = false,
    minDate: String? = null,
    maxDate: String? = null,
    minHour: Int? = null,
    minMinute: Int? = null,
    maxHour: Int? = null,
    maxMinute: Int? = null,
    setup: (date: String, time: String?, day: Int, month: Int, year: Int, hour: Int?, minute: Int?) -> Unit,
) {
    // calender class's instance and get current date , month and year from calender
    val c = Calendar.getInstance()
    val mYear = c.get(Calendar.YEAR) // current year
    val mMonth = c.get(Calendar.MONTH) // current month
    val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

    // date picker dialog
    DatePickerDialog(
        this,
        { _, year, monthOfYear, dayOfMonth -> // set day of month , month and year value in the edit text

            val dateText = "${dayOfMonth}-${monthOfYear + 1}-${year}"

            if (isUsingTimePicker) {

                if (minDate == dateText) {
                    this.showTimePickerExt(dateText, minHour, minMinute, maxHour, maxMinute) { _, mTime, mHour, mMinute ->
                        setup(dateText, mTime, dayOfMonth, monthOfYear, year, mHour, mMinute)
                    }
                } else {
                    this.showTimePickerExt(dateText, null, null, null, null) { _, mTime, mHour, mMinute ->
                        setup(dateText, mTime, dayOfMonth, monthOfYear, year, mHour, mMinute)
                    }
                }
            } else {
                setup(dateText, null, dayOfMonth, monthOfYear, year, null, null)
            }

        }, mYear, mMonth, mDay
    ).apply {
        minDate?.let { date ->
            datePicker.minDate = date.getMillisExt()
        }
        maxDate?.let { date ->
            datePicker.maxDate = date.getMillisExt()
        }
    }.show()

}


fun Context.showTimePickerExt(
    date: String? = null,
    minHour: Int? = null,
    minMinute: Int? = null,
    maxHour: Int? = null,
    maxMinute: Int? = null,
    setup: (date: String, time: String, hour: Int, minute: Int) -> Unit,
) {
    val c = Calendar.getInstance()
    val hour = c[Calendar.HOUR_OF_DAY]
    val minute = c[Calendar.MINUTE]

    CustomTimePicker(
        this,
        { _, selectedHour, selectedMinute ->
            val time = "$selectedHour:$selectedMinute"
            if (date != null) {
                setup(date, time.dateFormater(TIME_FORMAT, TIME_FORMAT), selectedHour, selectedMinute)
            } else {
                setup("", time.dateFormater(TIME_FORMAT, TIME_FORMAT), selectedHour, selectedMinute)
            }
        },
        hour,
        minute,
        true
    ).apply {
        setMin(minHour, minMinute)
        setMax(maxHour, maxMinute)
    }.show()

}


