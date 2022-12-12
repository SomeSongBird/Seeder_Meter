package com.songbirdnest.seeder_meter

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject


// Readings activity hosts the displays for each of the data with a setting for changing the time range of data used
class ReadingsActivity : AppCompatActivity() {
    // handler to repeatedly pull new data every 10 seconds
    val mInterval:Long = 10000
    var hand :Handler? = null
    var stored_data: Readings? = null
    val fragmentManager = supportFragmentManager

    var readingsView:ReadingFragment?=null
    var optionsMenu:Fragment?=null
    val dummyInput = "[{\"_id\":\"639369aa004d6ba3bda57055\",\"date_time\":\"11-28-2022 12:17:18\",\"soil_temp\":21.31,\"soil_moisture\":0,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369ad004d6ba3bda5705a\",\"date_time\":\"11-28-2022 12:17:22\",\"soil_temp\":21.34,\"soil_moisture\":0.02,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":265,\"sunlight_uv\":0.04},{\"_id\":\"639369b3004d6ba3bda57060\",\"date_time\":\"11-28-2022 12:17:30\",\"soil_temp\":21.35,\"soil_moisture\":0.02,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":261,\"sunlight_uv\":0.03},{\"_id\":\"639369b5004d6ba3bda57064\",\"date_time\":\"11-28-2022 12:17:32\",\"soil_temp\":21.36,\"soil_moisture\":0.02,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369b7004d6ba3bda57066\",\"date_time\":\"11-28-2022 12:17:34\",\"soil_temp\":21.37,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369bd004d6ba3bda5706d\",\"date_time\":\"11-28-2022 12:17:36\",\"soil_temp\":21.39,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369bf004d6ba3bda57070\",\"date_time\":\"12-09-2022 12:00:45\",\"soil_temp\":21.4,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369c1004d6ba3bda57073\",\"date_time\":\"12-09-2022 12:00:47\",\"soil_temp\":21.41,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369c3004d6ba3bda57076\",\"date_time\":\"12-09-2022 12:00:49\",\"soil_temp\":21.4,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369c5004d6ba3bda57079\",\"date_time\":\"12-09-2022 12:00:51\",\"soil_temp\":21.4,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":264,\"sunlight_uv\":0.03},{\"_id\":\"639369c7004d6ba3bda5707c\",\"date_time\":\"12-09-2022 12:00:53\",\"soil_temp\":21.4,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369c9004d6ba3bda57080\",\"date_time\":\"12-09-2022 12:00:55\",\"soil_temp\":21.39,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369cb004d6ba3bda57082\",\"date_time\":\"12-09-2022 12:00:57\",\"soil_temp\":21.39,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369cd004d6ba3bda57086\",\"date_time\":\"12-09-2022 12:00:59\",\"soil_temp\":21.39,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":264,\"sunlight_uv\":0.03},{\"_id\":\"639369d0004d6ba3bda57089\",\"date_time\":\"12-09-2022 12:01:01\",\"soil_temp\":21.39,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369d2004d6ba3bda5708c\",\"date_time\":\"12-09-2022 12:01:04\",\"soil_temp\":21.37,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":264,\"sunlight_uv\":0.03},{\"_id\":\"639369d4004d6ba3bda5708f\",\"date_time\":\"12-09-2022 12:01:06\",\"soil_temp\":21.36,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369d7004d6ba3bda57093\",\"date_time\":\"12-09-2022 12:01:08\",\"soil_temp\":21.35,\"soil_moisture\":0.04,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369d9004d6ba3bda57097\",\"date_time\":\"12-09-2022 12:01:11\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":262,\"sunlight_uv\":0.03},{\"_id\":\"639369dc004d6ba3bda5709a\",\"date_time\":\"12-09-2022 12:01:13\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":264,\"sunlight_uv\":0.03},{\"_id\":\"639369de004d6ba3bda5709d\",\"date_time\":\"12-09-2022 12:01:16\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":264,\"sunlight_uv\":0.03},{\"_id\":\"639369e0004d6ba3bda570a0\",\"date_time\":\"12-09-2022 12:01:18\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a4\",\"date_time\":\"12-09-2022 12:01:20\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a5\",\"date_time\":\"12-09-2022 12:01:22\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a6\",\"date_time\":\"12-09-2022 12:01:24\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a7\",\"date_time\":\"12-09-2022 12:01:26\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a8\",\"date_time\":\"12-09-2022 12:01:28\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570a9\",\"date_time\":\"12-09-2022 12:01:30\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570b0\",\"date_time\":\"12-09-2022 12:01:32\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03},{\"_id\":\"639369e2004d6ba3bda570b1\",\"date_time\":\"12-09-2022 12:01:34\",\"soil_temp\":21.34,\"soil_moisture\":0.03,\"air_temp\":21,\"air_humidity\":27,\"sunligh_visible\":263,\"sunlight_uv\":0.03}]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_readings)

        readingsView = fragmentManager.findFragmentById(R.id.Content) as ReadingFragment
        optionsMenu = fragmentManager.findFragmentById(R.id.menu)

        fragmentManager.beginTransaction()
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .hide(optionsMenu as Fragment)
            .commit()

        //pullMassData(30)

        // !!! for testing the graphs while away from the school only, remove when not testing !!! //
        testMassData(dummyInput)
        // !!! for testing the graphs while away from the school only, remove when not testing !!! //

        Log.i("index","${stored_data!!.currentIndex-1}")
        readingsView?.updateData(this.stored_data!!)

        hand = Handler(Looper.getMainLooper())
        //startRepeatingTask()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRepeatingTask()
        this.stored_data = null
    }

    // functions that run every 10 seconds
    private var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                //val dum = "dum"
                pullUpdateData() //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                hand?.postDelayed(this, mInterval)
            }
        }
    }
    private fun startRepeatingTask() {
        mStatusChecker.run()
    }
    private fun stopRepeatingTask() {
        hand?.removeCallbacks(mStatusChecker)
    }

    fun loadOptionsMenu(view:View?){
        if(optionsMenu!!.isHidden) {
            fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .show(optionsMenu as Fragment)
                .commit()
        }else{
            fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(optionsMenu as Fragment)
                .commit()
        }
    }

    fun changeView(view: View){
        val ft = fragmentManager.beginTransaction()
        if(view.id==R.id.soil_button) {
            ft.replace(R.id.Content, SoilFragment())
        }else if(view.id==R.id.last_air_button){
            ft.replace(R.id.Content, LastAirFragment())
        }else if(view.id==R.id.last10_air_button){
            ft.replace(R.id.Content, LastTenAirFragment())
        }else if(view.id==R.id.last_reading_button){
            ft.replace(R.id.Content, MostRecentFragment())
        }
        ft.commit()
        fragmentManager.executePendingTransactions()

        readingsView = fragmentManager.findFragmentById(R.id.Content) as ReadingFragment

        Log.i("index","${stored_data!!.currentIndex-1}")
        readingsView!!.updateData(this.stored_data!!)
        loadOptionsMenu(null)
    }

    // function to get x amount of data the first time
    private fun pullMassData(){
        this.stored_data = Readings(30)

        val requestMan = RequestManager.getInstance(this)
        val url = "http://172.22.32.140:1880/Chris_readings?q=30"

        val req = StringRequest(Request.Method.GET,url,
            {response->
                //Log.i("check",response)
                val editedResponse = response.substring(1,response.length-1).replace("},{","}~{")

                val responses = editedResponse.split('~')
                //Log.i("check",responses[0])
                for(res in responses){
                    try{
                        val obj = JSONObject(res)
                        this.stored_data!!.addNewItem(obj)
                        Log.i("Network","Mass Pull Success $obj")
                    }catch (e:java.lang.Exception){
                        Log.e("Network","Mass Pull Response Error: $e")
                    }
                }
            },{error->
                Log.e("Network","Mass Pull Communications Error:$error")
            })
        requestMan.addToRequestQueue(req)

        //readingsView?.updateData(this.stored_data!!)
    }
    private fun testMassData(response:String){
        this.stored_data = Readings(30)

        val editedResponse = response.substring(1,response.length-1).replace("},{","}~{")

        val responses = editedResponse.split('~')
        //Log.i("check",responses.size.toString())
        for(res in responses){
            try{
                //Log.i("Network","Mass Pull Success?")
                val obj = JSONObject(res)
                //Log.i("Network","Mass Pull Success?")
                this.stored_data!!.addNewItem(obj)
                //Log.i("index","${stored_data!!.currentIndex-1}")
                Log.i("Network","Mass Pull Success $obj")
            }catch (e:java.lang.Exception){
                Log.e("Network","Mass Pull Response Error: $e")
            }
        }

        //readingsView?.updateData(this.stored_data!!)
    }

    // function to continuously pull new data from the server (once every 10 seconds so last 5 entries)
    private fun pullUpdateData(){
        val requestMan = RequestManager.getInstance(this)
        val url = "http://172.22.32.140:1880/Chris_readings?q=5"

        val req = StringRequest(Request.Method.GET,url,
            {response->

                //Log.i("check",response)
                val editedResponse = response.substring(1,response.length-1).replace("},{","}~{")
                //Log.i("check",editedResponse)

                val responses = editedResponse.split('~')
                //Log.i("check",responses[0])

                for(res in responses) {
                    try {
                        val obj = JSONObject(res)
                        this.stored_data?.addNewItem(obj)
                        //Log.i("Network", "Continuous Pull Success $obj")
                    } catch (e: java.lang.Exception) {
                        Log.e("Network", "continuous Pull Response Error: $e")
                    }
                }
            },{error->
                Log.e("Network","continuous Pull Communications Error: $error")
            })
        requestMan.addToRequestQueue(req)

        readingsView?.updateData(this.stored_data!!)
    }

    fun openAbout(view: View){
        val intent = Intent(this,AboutActivity::class.java)
        startActivity(intent)
    }
}