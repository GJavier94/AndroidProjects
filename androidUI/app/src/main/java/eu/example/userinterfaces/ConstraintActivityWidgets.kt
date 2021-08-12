package eu.example.userinterfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView

class ConstraintActivityWidgets : AppCompatActivity() {
    lateinit var seekBar: SeekBar
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_widgets)


        seekBar = findViewById<SeekBar>(R.id.seekbar1)

        textView = findViewById<TextView>(R.id.act_const_widg_textView)

        seekBar.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    //This method is triggered when a user or some component change the seekbar progress value
                    //that's why there's a  boolean telling if it was the user or not
                    if(fromUser){
                        textView.text = seekBar?.progress.toString()
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    Log.i("seekBar:onStartTrackingTouch", "Someone started dragging")
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    Log.i("seekBar:onStartTrackingTouch", "Someones stopped dragging")
                }

            }
        )

    }



}