package com.example.databindingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.databindingapp.databinding.ActivityMainBinding
import com.example.databindingapp.viewmodels.ViewModelMainActivity


class MainActivity : AppCompatActivity() {

    val viewModelMainActivity: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelMainActivity.mainActivity = this
        /**
         * now it's time to bind the data by using the autogenerated binding class
         * It inherits from ViewDataBinding
         * By using DataBindingUtil() we can set the view and get the binding class
         * then set the attributes
         */
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.viewModelMainActivity = viewModelMainActivity

        val ageEditText = findViewById<EditText>(R.id.ageEditText)

        val mapNamePass: MutableMap<String, String> = mutableMapOf()
        mapNamePass["Javier"] = "Armenta"
        binding.mapNamePass = mapNamePass
        /**
         * For fragments or other views it needs the owned view or the inflate method from
         * DataBindingUtil.inflate()
         */

        /**
         * In order to bind the ViewModel with the layout it is necessary to user the Binding class
         * because Binding Class generates tons of findViewById => it has the views in code
         *
         * => these views need to => observe data ViewModel attrs data source
         * ==> the observers needs  a LifeCycleOwner to just notify when active
         */
        binding.lifecycleOwner = this

        /**
         * we can still create anonymous observers so that we can still be printing logs
         * when data changes
         */
        viewModelMainActivity.rememberUser.observe(this, Observer {
            rememberUser ->
            Log.i(TAG, "rememberUser value change to $rememberUser")
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        viewModelMainActivity.mainActivity = null
        viewModelMainActivity.binding = null
    }
    companion object{
        const val TAG = "MainActivityL"
    }

}




