package eu.example.userinterfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class ConstraintWidgetsActivity : AppCompatActivity() {
    lateinit var seekBar: SeekBar
    lateinit var textView: TextView
    lateinit var ratingBar: RatingBar
    lateinit var imageView: ImageView
    lateinit var progressBar:ProgressBar
    lateinit var refActivity: ConstraintWidgetsActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_widgets)
        seekBar = findViewById<SeekBar>(R.id.seekbar1)

        /*
        Implementing some behaviour on image profile
        Let's say the image is loaded by some third party service (REST, http library like picasso,...)
        if the resource is not loaded then it will show profile picture from local resources

        * */
        refActivity = this

        imageView = findViewById<ImageView>(R.id.imageView)
        progressBar = findViewById<ProgressBar>(R.id.progressBar2)
        loadImage()

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

        /*Rating bar is  a subtype of progressbar and seekbar
        but it is measured by stars
        main attributes in xml
        numStars: number of starts showed
        stepSize: measure unit to jump from one measure to another
        isIndicator: Boolean (false by defect)
        rating: the current rating ( can be changed by the user if indicator allows it)
        * */


        ratingBar = findViewById<RatingBar>(R.id.ratingBar2)
        ratingBar.setOnRatingBarChangeListener {
                ratingBar, rating, fromUser ->
            val valueText:String = when(rating.toInt()){
                1 -> "Bad"
                2 -> "Less than average"
                3 -> "Just Average"
                4 -> "Good"
                else -> "Perfect"
            }
            Log.i("ratingBar", valueText)
            Toast.makeText(this, valueText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadImage() {
        /*
        * here it should go the code from the rest request
        * let's assume it was  unsuccessful
        * */
        object:Thread(){
            override fun run() {
                super.run()
                Thread.sleep(2000) // simulating the time request
                refActivity.runOnUiThread {
                    progressBar.visibility = View.INVISIBLE
                    imageView.visibility = View.VISIBLE
                }

            }
        }.start()

    }
}
