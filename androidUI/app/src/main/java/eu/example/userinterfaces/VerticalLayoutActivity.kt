package eu.example.userinterfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class VerticalLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_layout)
        Log.i("${TAG} ${Thread.currentThread().stackTrace.toList().get(2).toString().split(".").get(4).split("(").get(0)
        }", " ID_Thread: ${Thread.currentThread().id}")


    }
    companion object{
        const val TAG:String = "eu.example.userinterfaces.VerticalLayoutActivity"
    }
}