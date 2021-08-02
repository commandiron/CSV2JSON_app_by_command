package com.demirli.a8csvtojsonexample

import android.annotation.SuppressLint
import android.database.CharArrayBuffer
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.RegExp
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.util.function.BinaryOperator
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    private var text: String? = null

    private lateinit var valueList: ArrayList<List<String>>

    private lateinit var jsonArray: JSONArray

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueList = arrayListOf()
        jsonArray = JSONArray()

        convert_btn.setOnClickListener {
            text = csv_tv.text.toString()

            val firstLine = text?.split("\n")?.first()
            val firstLineWithOutComma = firstLine?.replace("\"","")
            val keys = firstLineWithOutComma?.split(",")

            val values = text?.split("\n")
            for(i in 1 until values!!.size){
                var b = values[i].split(",")
                valueList.add(b)
            }

            for(i in valueList){
                val result = JSONObject(keys!!.zip(i).toMap())
                jsonArray.put(result)
            }

            json_tv.text = jsonArray.toString()
        }
    }

}
