package eu.example.userinterfaces


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.UiThread
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class ConstraintLayoutActivity : AppCompatActivity() {


    lateinit var buttonStart: FloatingActionButton
    lateinit var progressBar: ProgressBar
    lateinit var switch:Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        Log.i("$TAG ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")

        setContentView(R.layout.activity_constraint_layout)

        buttonStart = findViewById<FloatingActionButton>(R.id.button8)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)


        val crt: ConstraintLayoutActivity = this

            buttonStart.setOnClickListener {
            object: AsyncTask<String,String,String>(){
                override fun doInBackground(vararg params: String?): String {



                    val  progressBar = findViewById<ProgressBar>(R.id.progressBar)
                    var cont:Int = 0
                    val IdThread = Random.nextInt(10)
                    crt.runOnUiThread{
                        crt.progressBar.progressTintList = ColorStateList.valueOf(Color.YELLOW)
                    }

                    while(cont < 100 ){
                        Thread.sleep(1)
                        crt.runOnUiThread {
                            findViewById<TextView>(R.id.textProgress).text = cont.toString()
                        }
                        progressBar.progress = cont.toInt()

                        Log.i("${RelativeLayoutActivity.TAG}:startProgressBar $IdThread" , cont.toString() )
                        cont += 1
                    }
                    crt.runOnUiThread{
                        crt.progressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)
                    }
                    return "true"
                }

            }.execute()


        }
        switch = findViewById<Switch>(R.id.buttonHide)
        switch.setOnClickListener {
            when(!switch.isChecked){
                 true -> {
                    findViewById<Button>(R.id.button7).visibility = View.GONE
                    findViewById<Button>(R.id.button6).visibility = View.GONE
                }
                 false-> {
                    findViewById<Button>(R.id.button7).visibility = View.VISIBLE
                    findViewById<Button>(R.id.button6).visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("$TAG ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")

    }

    override fun onResume() {
        super.onResume()
        Log.i("$TAG ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")

    }
    companion object{
        const val TAG = "eu.example.userinterfaces.ConstraintLayoutActivity"
    }
}