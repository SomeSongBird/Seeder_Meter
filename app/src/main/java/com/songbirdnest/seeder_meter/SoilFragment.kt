package com.songbirdnest.seeder_meter


import android.graphics.Color
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import javax.sql.CommonDataSource


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SoilFragment : ReadingFragment() {

    var lineChart: LineChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soil, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SunlightFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SoilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun updateData(dataSource: Readings){
        lineChart = view?.findViewById(R.id.chart)
        if(lineChart==null){
            Log.e("fragmentCreate","No linechart")
        }
        if(dataSource == null) Log.e("","datasource Not initialized")
        var entries = List(dataSource!!.max_size){i->Entry(i*1f,dataSource.soilMoisture[i])}
        var entries2 = List(dataSource!!.max_size){i->Entry(i*1f,dataSource.soilTemp[i])}
        val lineData = LineDataSet(entries,"Soil Moisture")
        lineData.setAxisDependency(YAxis.AxisDependency.LEFT)
        lineData.color = Color.BLUE
        val lineData2 = LineDataSet(entries2,"Soil Moisture")
        lineData2.setAxisDependency(YAxis.AxisDependency.RIGHT)
        lineData2.color = Color.GREEN
        val data = ArrayList<ILineDataSet>()
        data.add(lineData)
        data.add(lineData2)

        lineChart!!.data = LineData(data)
        lineChart!!.invalidate()

    }
}