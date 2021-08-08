package eu.example.userinterfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class RelativeLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)

        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton3)

        //toggleButton handled by event onCheckedChangeListener
        toggleButton.setOnCheckedChangeListener {
                buttonView, isChecked ->
            when(isChecked){
                true -> buttonView.text = resources.getText(R.string.toogle_set)
                else -> buttonView.text = resources.getText(R.string.toogle_unset)
            }
        }

        val switchButton = findViewById<Switch>(R.id.switch2)
        switchButton.setOnCheckedChangeListener{
            buttonView, isCheked ->
            if(isCheked)  {
                switchButton.text = "isSelected"
                switchButton.textOn  = "isSelected"

            } else {
                switchButton.text = "isUnSelected"
                switchButton.textOff  = "isUnSelected"
            }

            Log.i(TAG, "switchButton $isCheked" )
        }


    }



    fun CheckBoxHandler(view:View){
        val checkBox = findViewById<CheckBox>(view.id)
        var isChecked:String = if( checkBox.isChecked)  "true" else "false"

        when(view.id){
            R.id.checkBox1 -> {
                //some action
                Log.i("$TAG:CheckBoxHandler ${view.id}", isChecked )
            }
            R.id.checkBox2 -> {
                //someActions
                Log.i("$TAG:CheckBoxHandler ${view.id}", isChecked )
            }
        }
    }

    companion object{
        const val TAG:String = "eu.example.userinterfaces.RelativeLayoutActivity"
    }

}