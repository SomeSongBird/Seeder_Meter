package com.songbirdnest.seeder_meter

import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

abstract class ReadingFragment: Fragment() {
    abstract fun updateData(dataSource: Readings)
}