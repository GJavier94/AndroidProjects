package eu.example.userinterfaces

import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.UiThread
import kotlin.random.Random

class ConstraintLayoutActivity : AppCompatActivity() {


    lateinit var buttonStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)

        buttonStart = findViewById<Button>(R.id.button8)
        buttonStart.apply {
            text = "Start"
        }

            buttonStart.setOnClickListener {
            object: AsyncTask<String,String,String>(){
                override fun doInBackground(vararg params: String?): String {
                    val  progressBar = findViewById<ProgressBar>(R.id.progressBar)
                    var cont:Double = 0.0
                    val IdThread = Random.nextInt(10)
                    while(cont <= 100 ){
                        Thread.sleep(1)
                        progressBar.progress = cont.toInt()
                        Log.i("${RelativeLayoutActivity.TAG}:startProgressBar $IdThread" , cont.toString() )
                        cont += 1.0
                    }
                    return "true"
                }

            }.execute()

        }

    }
}