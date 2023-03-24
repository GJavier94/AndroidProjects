package eu.example.userinterfaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("${TAG} ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")



        val spinner: Spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(this, R.array.activities_layouts, R.layout.text_view_custom).apply {
            spinner.adapter = this
        }


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
                _ ->
            val nameActivity = getActivitySelected(spinner.selectedItemPosition)

            val intent = Intent(this@MainActivity,Class.forName("eu.example.userinterfaces.$nameActivity"))

            startActivity(intent)
        }

        val btnDismiss = findViewById<Button>(R.id.btn_dismis)

        btnDismiss.setOnClickListener {
            when (spinner.visibility){
                View.VISIBLE -> spinner.visibility = View.INVISIBLE
                View.INVISIBLE -> spinner.visibility = View.VISIBLE

            }
        }
    }

    private fun getActivitySelected(selectedItemPosition: Int): String {
        val position = selectedItemPosition
        val array:Array<String> = resources.getStringArray( R.array.activities_layouts)
        val nameActivity:String = array[position]
        Log.d(TAG,"""$position ${array[position]}""" )
        return nameActivity
    }

    companion object{
        const val TAG = "eu.example.userinterfaces.MainActivity"
    }


}