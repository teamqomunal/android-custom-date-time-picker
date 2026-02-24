package com.qomunal.opensource.androidresearch.common.ext

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Created by faisalamircs on 24/02/2026
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


class AllowedDaysValidator(
    private val allowedDays: Set<Int>
) : CalendarConstraints.DateValidator {

    override fun isValid(date: Long): Boolean {
        val cal = Calendar.getInstance()
        cal.timeInMillis = date
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek in allowedDays
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeIntArray(allowedDays.toIntArray())
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<AllowedDaysValidator> {
        override fun createFromParcel(source: Parcel): AllowedDaysValidator {
            val array = source.createIntArray()?.toSet() ?: emptySet()
            return AllowedDaysValidator(array)
        }

        override fun newArray(size: Int): Array<AllowedDaysValidator?> {
            return arrayOfNulls(size)
        }
    }
}

fun FragmentManager.showFilteredDatePicker(
    title: String = "Pilih Tanggal",
    minDate: Long? = null,
    maxDate: Long? = null,
    allowedDays: Set<Int>? = null,
    onSelected: (dateMillis: Long, formattedDate: String) -> Unit
) {

    val constraintsBuilder = CalendarConstraints.Builder()

    minDate?.let { constraintsBuilder.setStart(it) }
    maxDate?.let { constraintsBuilder.setEnd(it) }

    if (allowedDays != null) {
        constraintsBuilder.setValidator(
            AllowedDaysValidator(allowedDays)
        )
    }

    val picker = MaterialDatePicker.Builder.datePicker()
        .setTitleText(title)
        .setCalendarConstraints(constraintsBuilder.build())
        .build()

    picker.addOnPositiveButtonClickListener { selection ->

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formatted = sdf.format(Date(selection))

        onSelected(selection, formatted)
    }

    picker.show(this, "FILTER_DATE_PICKER")
}