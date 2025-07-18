package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.common.ext.showDatePickerExt
import com.qomunal.opensource.androidresearch.common.ext.showMaterialDateRangePickerExt
import com.qomunal.opensource.androidresearch.common.ext.showMaterialDateTimePickerExt
import com.qomunal.opensource.androidresearch.common.ext.showMaterialTimePickerExt
import com.qomunal.opensource.androidresearch.common.ext.showTimePickerExt
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : BaseActivity<ActivityMainBinding>() {

    // calender class's instance and get current date , month and year from calender
    private val c = Calendar.getInstance()
    private val mYear = c.get(Calendar.YEAR) // current year
    private val mMonth = c.get(Calendar.MONTH) // current month
    private val mDay = c.get(Calendar.DAY_OF_MONTH) // current day

    private val hour = c.get(Calendar.HOUR_OF_DAY)
    private val minute = c.get(Calendar.MINUTE)

    private val dateToday = "$mDay-${mMonth + 1}-$mYear"
    private val timeToday = "$hour:$minute"

    private var date1 = dateToday

    private var minHour = 0
    private var minMinute = 0

    private val viewModel: MainViewModel by viewModels()

    private val router: MainRouter by lazy {
        MainRouter(this)
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        binding.apply {
            btnDatePicker.setOnClickListener {
                showDatePickerExt { date, time, day, month, year, hour, minute ->
                    btnDatePicker.text = date
                }
            }

            btnTimePicker.setOnClickListener {
                showTimePickerExt { date, time, hour, minute ->
                    btnTimePicker.text = time
                }
            }

            btnDateTimePicker.setOnClickListener {
                showDatePickerExt(
                    minDate = date1,
                    isUsingTimePicker = true,
                ) { date, time, day, month, year, hour, minute ->
                    btnDateTimePicker.text = "$date $time"
                }
            }

            btnMaterialDatePicker.setOnClickListener {
                showMaterialDateTimePickerExt(
                    supportFragmentManager
                ) { date, time, day, month, year, hour, minute ->
                    btnMaterialDatePicker.text = date
                }
            }

            btnMaterialTimePicker.setOnClickListener {
                showMaterialTimePickerExt(
                    supportFragmentManager
                ) { time, hour, minute ->
                    btnMaterialTimePicker.text = time
                }
            }

            btnMaterialDateTimePicker.setOnClickListener {
                showMaterialDateTimePickerExt(
                    fragmentManager = supportFragmentManager,
                    isUsingTimePicker = true
                ) { date, time, day, month, year, hour, minute ->
                    btnMaterialDateTimePicker.text = "$date $time"
                }
            }

            btnMaterialDateRangePicker.setOnClickListener {
                showMaterialDateRangePickerExt(
                    fragmentManager = supportFragmentManager
                ) { date ->
                    btnMaterialDateRangePicker.text = date
                }
            }
        }
    }

    override fun initObserver() {
        viewModel.apply {

        }
    }

}