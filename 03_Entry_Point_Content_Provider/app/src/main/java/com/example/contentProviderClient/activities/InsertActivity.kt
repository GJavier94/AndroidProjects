package com.example.contentProviderClient.activities

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.contentProviderClient.R
import com.example.contentProviderClient.viewmodel.ViewModelInsertActivity

class InsertActivity : AppCompatActivity() {
    private lateinit var textViewResultInsert: TextView
    private lateinit var editTextDisplayName: EditText
    private lateinit var vm:ViewModelInsertActivity
    private lateinit var buttonInsert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        editTextDisplayName = findViewById<EditText>(R.id.editTextDisplayName)

        buttonInsert = findViewById<Button>(R.id.buttonInsert_act_insert)
        vm = ViewModelProvider(this).get(ViewModelInsertActivity::class.java)
        textViewResultInsert = findViewById<TextView>(R.id.textViewResultInsert)

        setObservers()
        initListeners()

        Log.i(RetrieveActivity.TAG, "Checking permissions required")
        if( !vm.permissionToWriteContacts || this.applicationContext.checkSelfPermission(
                PERMISSION_WRITE_CONTACTS
            ) == PackageManager.PERMISSION_DENIED ){
            Log.i(RetrieveActivity.TAG, "One Permission is required")
            Log.i(RetrieveActivity.TAG, "Requiring the permission ${PERMISSION_WRITE_CONTACTS}")
            // Require the permission one more time
            requestPermissions(arrayOf(PERMISSION_WRITE_CONTACTS),
                PERMISSION_REQUEST_CODE
            )
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG, "The permission $PERMISSION_WRITE_CONTACTS was granted")
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                    vm.permissionToWriteContacts = true
                }else{
                    Log.i(TAG, "The permission $PERMISSION_WRITE_CONTACTS was denied")
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun initListeners() {
        buttonInsert.setOnClickListener {
            Log.i(TAG, "calling buttonInsert OnClick... vm: ${vm}")
            Log.i(TAG, "getting values from editText...")
            Log.i(TAG, "values gotten $vm")
            setViewModelValues()

            if(vm.attrDisplayName.isNotEmpty() ){
                Log.i(TAG, "attrDisplayName correctly filled...")
                Toast.makeText(this, "Proceeding to make the insertion", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Proceeding to make the insertion")
                vm.insert(this.contentResolver)
                Toast.makeText(this, "insertion finished", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "ainsertion finished")

            }else{
                Toast.makeText(this, "Some of the values haven't been settled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setObservers() {
        vm.attrResultInsert.observe(this, { it -> textViewResultInsert.text = it  })
    }

    private fun setViewModelValues() {
        Log.i(TAG,"setViewModelValues setting the values ")
        vm.attrDisplayName = editTextDisplayName.text.toString()
        Log.i(TAG, "values settled $vm")
    }
    companion object{
        const val TAG = "InsertActivityLogger"
        const val PERMISSION_REQUEST_CODE = 200
        const val PERMISSION_WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"
    }
}