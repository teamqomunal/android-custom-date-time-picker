package com.qomunal.opensource.androidresearch.common.ext

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

/**
 * Created by faisalamircs on 17/07/2025
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */

fun showMaterialTimePickerExt(
    fragmentManager: FragmentManager,
    title: String? = null,
    callback: (time: String, hour: Int, minute: Int) -> Unit,
) {

    val c = Calendar.getInstance()
    val hour = c[Calendar.HOUR_OF_DAY]
    val minute = c[Calendar.MINUTE]

    val timeBuilder =
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setHour(hour)
            .setMinute(minute)

    title?.let {
        timeBuilder.setTitleText(it)
    }

    val timePicker = timeBuilder.build()

    timePicker.addOnPositiveButtonClickListener {
        val selectedHour = timePicker.hour
        val selectedMinute = timePicker.minute

        val time = "$selectedHour:$selectedMinute"

        callback.invoke(time.dateFormater(TIME_FORMAT, TIME_FORMAT), selectedHour, selectedMinute)
    }

    timePicker.addOnNegativeButtonClickListener {
        timePicker.dismissAllowingStateLoss()
    }

    timePicker.show(fragmentManager, "MaterialTimePicker")

}

fun showMaterialDatePickerExt(
    fragmentManager: FragmentManager,
    title: String? = null,
    isUsingTimePicker: Boolean = false,
    callback: (date: String, time: String?, day: Int, month: Int, year: Int, hour: Int?, minute: Int?) -> Unit,
) {
    // normal date picker
    val dateBuilder = MaterialDatePicker.Builder
        .datePicker()
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)

    title?.let {
        dateBuilder.setTitleText(it)
    }

    val datePicker: MaterialDatePicker<Long> = dateBuilder.build()

    datePicker.addOnPositiveButtonClickListener {

        val date = datePicker.headerText.dateFormater(MATERIAL_DATE_FORMAT, SIMPLE_DATE_FORMAT)
        val day = datePicker.headerText.dateFormater(MATERIAL_DATE_FORMAT, BASE_DATE_FORMAT_DAY)
        val month = datePicker.headerText.dateFormater(MATERIAL_DATE_FORMAT, BASE_DATE_FORMAT_MONTH)
        val year = datePicker.headerText.dateFormater(MATERIAL_DATE_FORMAT, BASE_DATE_FORMAT_YEAR)

        if (isUsingTimePicker) {

            showMaterialTimePickerExt(fragmentManager) { time, hour, minute ->
                callback.invoke(
                    date,
                    time,
                    day.toInt(),
                    month.toInt(),
                    year.toInt(),
                    hour,
                    minute
                )
            }

        } else {
            callback.invoke(
                date,
                null,
                day.toInt(),
                month.toInt(),
                year.toInt(),
                null,
                null
            )
        }


    }

    datePicker.addOnNegativeButtonClickListener {
        datePicker.dismissAllowingStateLoss()
    }

    datePicker.show(fragmentManager, "MaterialDatePicker")

}