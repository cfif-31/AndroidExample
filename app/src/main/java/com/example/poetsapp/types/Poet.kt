package com.example.poetsapp.types

import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class Poet {
    var firstName:String = ""
    var lastName:String = ""
    var middleName:String = ""
    var birthDate: Date? = null
    var deadDate:Date? = null
    var description:String = ""

    companion object{
        fun FromJson(json: JSONObject):Poet{
            val poet = Poet()
            poet.firstName = json.optString("firstName")
            poet.lastName = json.optString("lastName")
            poet.middleName = json.optString("middleName")
            poet.birthDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(json.optString("birthDate"))
            poet.deadDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(json.optString("deadDate"))
            poet.description = json.optString("description")
            return poet;
        }

         private fun getJsonList():String{
             val connect = URL("https://api.cfif31.ru/Poets").openConnection() as HttpURLConnection
             return connect.inputStream.bufferedReader().readText()
        }

        suspend fun  GetData(): ArrayList<Poet>{
            val str = getJsonList()
            val json = JSONArray(str)
            val poets = ArrayList<Poet>()
            for (i in 0 until json.length()){
                val jobj = json.getJSONObject(i)
                poets.add(FromJson(jobj))
            }
            return poets
        }
    }
}