package eu.example.userinterfaces

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

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
            if(buttonStart.isEnabled){
                buttonStart.isEnabled = false
                val value:String = object: AsyncTask<String,String,String>(){
                    override fun doInBackground(vararg params: String?): String {
                        val  progressBar = findViewById<ProgressBar>(R.id.progressBar)
                        var cont:Double = 0.0
                        while(cont <= 100 ){
                            Thread.sleep(1)
                            progressBar.progress = cont.toInt()
                            Log.i("${RelativeLayoutActivity.TAG}:startProgressBar" , cont.toString() )
                            cont += 0.10
                        }
                        return "true"
                    }

                }.execute().get().also {
                    value ->
                    when(value){
                        "true" -> buttonStart.isEnabled = true
                    }
                }

            }

        }
    }
}