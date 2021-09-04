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
import androidx.lifecycle.Observer

class OneActivity : AppCompatActivity() {
    private lateinit var textViewSibling2toActivity: TextView
    private val viewModel: ViewModelOneActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        textViewSibling2toActivity = findViewById<TextView>(R.id.textViewSibling2toActivity)
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