package com.qomunal.opensource.androidresearch.common.ext

import androidx.fragment.app.FragmentManager
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

    val builder =
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setHour(hour)
            .setMinute(minute)

    title?.let {
        builder.setTitleText(it)
    }

    val picker = builder.build()

    picker.addOnPositiveButtonClickListener {
        val selectedHour = picker.hour
        val selectedMinute = picker.minute

        val time = "$selectedHour:$selectedMinute"

        callback.invoke(time.dateFormater(TIME_FORMAT, TIME_FORMAT), selectedHour, selectedMinute)
    }

    picker.addOnNegativeButtonClickListener {
        picker.dismissAllowingStateLoss()
    }

    picker.show(fragmentManager, "TimePickerBase")

}

