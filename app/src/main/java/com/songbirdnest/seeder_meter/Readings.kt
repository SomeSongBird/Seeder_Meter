package com.songbirdnest.seeder_meter

import android.util.Log
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.max


//readings is a data structure to store data from the database in stacks and make storage and passing data around easy
class Readings(size:Int) {
    //var most_recent = "01-01-2000 01:01:01"
    var dates = Array<String>(size){_->""}
    var soilTemp = FloatArray(size)
    var soilMoisture = FloatArray(size)
    var airTemp = FloatArray(size)
    var airHumid = FloatArray(size)
    var sunlightVisible = FloatArray(size)
    var sunlightUV = FloatArray(size)
    val max_size = size
    var currentIndex = 0

    fun addNewItem(jsonData:JSONObject){
/*
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        val stored_dt = LocalDate.parse(most_recent, formatter);
        val incoming_dt = LocalDate.parse(jsonData.get("date_time").toString(),formatter)
        if(incoming_dt.isAfter(stored_dt)){
            most_recent = jsonData.get("date_time").toString()*/
            if(currentIndex<max_size){
                add(jsonData)
                this.currentIndex+=1
                //Log.i("index_readings","$currentIndex")
            }else{
                drop()
                add(jsonData)
                //Log.i("index_max?","$max_size + $currentIndex")
            }
        //}
    }

    private fun drop(){
        var index = 0
        while(index< max_size-1){
            dates[index] = dates[index+1]
            soilTemp[index] = soilTemp[index+1]
            soilMoisture[index] = soilMoisture[index+1]
            airTemp[index] = airTemp[index+1]
            airHumid[index] = airHumid[index+1]
            sunlightVisible[index] = sunlightVisible[index+1]
            sunlightUV[index] = sunlightUV[index+1]
            index++
        }
    }
    private fun add(value:JSONObject){
        dates[currentIndex] = value.get("date_time") as String
        soilTemp[currentIndex] = value.getDouble("soil_temp").toFloat()
        soilMoisture[currentIndex] = value.getDouble("soil_moisture").toFloat()
        airTemp[currentIndex] = value.getDouble("air_temp").toFloat()
        airHumid[currentIndex] = value.getDouble("air_humidity").toFloat()
        sunlightVisible[currentIndex] = value.getInt("sunligh_visible").toFloat()
        sunlightUV[currentIndex] = value.getDouble("sunlight_uv").toFloat()
    }
}