package eu.example.userinterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.view.get
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("${TAG} ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")



        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.activities_layouts,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            _ ->
            val position = spinner.selectedItemPosition
            val array:Array<String> = resources.getStringArray( R.array.activities_layouts)
            val nameActivity:String = array[position]
            Log.d(TAG,"""$position ${array[position]}""" )

            val intent = Intent(applicationContext,Class.forName("eu.example.userinterfaces.$nameActivity"))

            startActivity(intent)
        }

    }


    companion object{
        const val TAG = "eu.example.userinterfaces.MainActivity"
    }
}