package com.songbirdnest.seeder_meter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.annotation.RequiresPermission.Read

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MostRecentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MostRecentFragment : ReadingFragment() {
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
        return inflater.inflate(R.layout.fragment_most_recent, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MostRecentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MostRecentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun updateData(dataSource: Readings) {
        val activ :ReadingsActivity
        try{
            activ = requireActivity() as ReadingsActivity
        }catch (e:java.lang.Exception){
            return
        }

        activ.findViewById<TextView>(R.id.mr_date).text = dataSource.dates[dataSource.currentIndex-1]
        activ.findViewById<TextView>(R.id.mr_soilT).text = dataSource.soilTemp[dataSource.currentIndex-1].toString()
        activ.findViewById<TextView>(R.id.mr_soilM).text = dataSource.soilMoisture[dataSource.currentIndex-1].toString()
        activ.findViewById<TextView>(R.id.mr_airT).text = dataSource.airTemp[dataSource.currentIndex-1].toString()
        activ.findViewById<TextView>(R.id.mr_airH).text = dataSource.airHumid[dataSource.currentIndex-1].toString()
        activ.findViewById<TextView>(R.id.mr_sunV).text = dataSource.sunlightVisible[dataSource.currentIndex-1].toString()
        activ.findViewById<TextView>(R.id.mr_sunUV).text = dataSource.sunlightUV[dataSource.currentIndex-1].toString()

    }
}