package com.songbirdnest.seeder_meter

import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LastTenAirFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LastTenAirFragment : ReadingFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_ten_air, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LastTenAirFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LastTenAirFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun updateData(dataSource: Readings) {
        val table = (requireActivity() as ReadingsActivity).findViewById<TableLayout>(R.id.temp_table)
        table.removeAllViews()
        var i = 1
        while(i<=10){
            Log.i("Last10","${dataSource.currentIndex-i}")
            val date = dataSource.dates[dataSource.currentIndex-i]
            var date_text = TextView(this.context)
            date_text.text = "$date : "
            Log.i("Last10","${date_text.text}")
            val temp = dataSource.airTemp[dataSource.currentIndex-i]
            var temp_text = TextView(this.context)
            temp_text.text = "$temp C"
            Log.i("Last10","${temp_text.text}")
            var tr = TableRow(this.context)
            tr.addView(date_text)
            tr.addView(temp_text)
            table.addView(tr)
            i++
        }
        Log.i("Last10","function complete")
    }
}