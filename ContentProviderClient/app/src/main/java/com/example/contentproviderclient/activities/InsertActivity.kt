package com.example.contentproviderclient.activities

import android.content.ContentUris
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contentproviderclient.R
import com.example.contentproviderclient.viewmodel.ViewModelInsertActivity

class InsertActivity : AppCompatActivity() {
    private lateinit var textViewResultInsert: TextView
    private lateinit var editTextFrecuency: EditText
    private lateinit var editTextWord: EditText
    private lateinit var editTextLocale: EditText
    private lateinit var editTextAppId: EditText
    private lateinit var vm:ViewModelInsertActivity
    private lateinit var buttonInsert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        editTextAppId = findViewById<EditText>(R.id.editTextAppId)
        editTextLocale = findViewById<EditText>(R.id.editTextLocale)
        editTextWord = findViewById<EditText>(R.id.editTextWord)
        editTextFrecuency = findViewById<EditText>(R.id.editTextFrecuency)
        buttonInsert = findViewById<Button>(R.id.buttonInsert_act_insert)
        vm = ViewModelProvider(this).get(ViewModelInsertActivity::class.java)
        textViewResultInsert = findViewById<TextView>(R.id.textViewResultInsert)

        setViewModelValues()
        //setObservers()
        initListeners()

    }

    private fun initListeners() {
        buttonInsert.setOnClickListener {
            Log.i(TAG, "calling buttonInsert OnClick... vm: ${vm}")
            Log.i(TAG, "getting values from editText...")
            setViewModelValues()
            Log.i(TAG, "values gotten $vm")

            if(vm.attrAppId.value!!.isNotEmpty() &&
                vm.attrFrequency.value!!.isNotEmpty()&&
                vm.attrLocale.value!!.isNotEmpty()&&
                vm.attrWord.value!!.isNotEmpty()){
                Log.i(TAG, "form values correctly filled...")

                Toast.makeText(this, "Proceeding to make the insertion", Toast.LENGTH_SHORT)
                vm.insert(this.contentResolver)

            }else{
                Toast.makeText(this, "Some of the values haven't been settled", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun setObservers() {

        vm.attrAppId.observe( this, Observer<String> {
                t -> editTextAppId.setText(t) }
        )
        vm.attrLocale.observe( this, Observer { t -> editTextLocale.setText(t) })
        vm.attrWord.observe( this, Observer { t -> editTextWord.setText(t) })
        vm.attrFrequency.observe( this, Observer { t -> editTextFrecuency.setText(t) })
        vm.attrResultInsert.observe( this, Observer { t -> textViewResultInsert.text = t })
        vm.uriResult?.observe( this, Observer { t -> Toast.makeText(this, "${t.toString()}", Toast.LENGTH_SHORT) })


    }

    private fun setViewModelValues() {
        Log.i(TAG,"setViewModelValues setting the values ")
        vm.attrAppId.value = editTextAppId.text.toString()
        vm.attrLocale.value = editTextLocale.text.toString()
        vm.attrWord.value = editTextWord.text.toString()
        vm.attrFrequency.value = editTextFrecuency.text.toString()
        vm.attrResultInsert.value = textViewResultInsert.text.toString()
        Log.i(TAG, "values settled $vm")
    }
    companion object{
        val TAG = "InsertActivityLogger"
    }
}