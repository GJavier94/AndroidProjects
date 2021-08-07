package eu.example.userinterfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton

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

    }

}