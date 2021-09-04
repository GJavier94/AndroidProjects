package com.example.fragmentcommunication.ViewModelCom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.fragmentcommunication.R
import com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity.ViewModelOneActivity

import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.example.fragmentcommunication.ViewModelCom.Models.Person

const val BUNDLE_PERSON:String = "PERSON_OBJECT_KEY"


class OneActivity : AppCompatActivity() {
    private lateinit var textViewSibling2toActivity: TextView
    private val viewModel: ViewModelOneActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        textViewSibling2toActivity = findViewById<TextView>(R.id.textViewSibling2toActivity)


        /**
         * FragmentManager Class implement FragmentResultOwner
         * FragmentResultOwner: has many methods  two important ones are
         *      setFragmentResult( bundlekey:String, bundle:Bundle) -> sends a result so the sender used it
         *      setFragmentResultListener(bundlekey:String, bundle:Bundle) -> receives the result
         *
         * A fragment has two FragmentManager
         *  parent-> which is the FM from the parent so you can comunicate with your siblings
         *  childFragmentManager-> so you save all the fragments Children you own
         *
         *  Fragments have methods and have the property Fragment manager
         *  so it hides the calling in the FM by making a member method call the method of the FM
         * e.g
         * within an Act
         * setFragmentResult() <<---- this one does the call
         * Activities need to call the fragment
         */

        this.supportFragmentManager.setFragmentResult(
            Keys.ACTIVITY_TO_SIBLING_1,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Javier", "Armenta", 18) )
            }
        )

        this.supportFragmentManager.setFragmentResultListener(Keys.SIBLING_1_TO_ACTIVITY,this){
            _, bundle ->
            val person:Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(TAG, "personSIBLING_1_TO_ACTIVITY: $person")
        }
        this.supportFragmentManager.setFragmentResultListener(Keys.SIBLING_2_TO_ACTIVITY,this){
                _, bundle ->
            val person:Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(TAG, "personSIBLING_2_TO_ACTIVITY: $person")
        }

        this.supportFragmentManager.setFragmentResult(
            Keys.ACTIVITY_TO_SIBLING_2,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Nicolas", "Armenta", 19) )
            }
        )


        this.supportFragmentManager.commit{
            add<FragmentSibling1>(R.id.fragmentContainerSibling1, "FragmentSibling1")
            add<FragmentSibling2>(R.id.fragmentContainerSibling2, "FragmentSibling2")
            setPrimaryNavigationFragment( supportFragmentManager.findFragmentByTag("FragmentSibling1"))
        }
        this.viewModel.counterSibling2toActivity.observe(this, Observer {
            Log.i(TAG, "observer changing value ...$it")
            textViewSibling2toActivity.text = it.toString()
        })

    }
    companion object{
        const val TAG = "OneActivityLogger"
    }

}