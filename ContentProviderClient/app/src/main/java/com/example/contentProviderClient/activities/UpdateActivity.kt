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

class UpdateActivity : AppCompatActivity() {
    private lateinit var textViewResultUpdate: TextView
    private lateinit var editTextDisplayNameUpdate: EditText
    private lateinit var vm: ViewModelUpdateActivity
    private lateinit var buttonUpdate: Button
    private lateinit var  editTextDisplayNameNew:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)



        editTextDisplayNameUpdate = findViewById<EditText>(R.id.editTextDisplayNameUpdate)
        editTextDisplayNameNew = findViewById<EditText>(R.id.editTextDisplayNameNew)
        buttonUpdate = findViewById<Button>(R.id.buttonUpdate_act_update)
        vm = ViewModelProvider(this).get(ViewModelUpdateActivity::class.java)
        textViewResultUpdate = findViewById<TextView>(R.id.textViewResultUpdate)

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
                    Log.i(InsertActivity.TAG, "The permission ${PERMISSION_WRITE_CONTACTS} was granted")
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                    vm.permissionToWriteContacts = true
                }else{
                    Log.i(InsertActivity.TAG, "The permission ${PERMISSION_WRITE_CONTACTS} was denied")
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun initListeners() {
        buttonUpdate.setOnClickListener {
            Log.i(TAG, "calling buttonUpdate OnClick... vm: ${vm}")
            Log.i(TAG, "getting values from editText...")
            Log.i(TAG, "values gotten $vm")
            setViewModelValues()

            if(vm.attrDisplayUpdateName.isNotEmpty()  && vm.attrNameNew.isNotEmpty()){
                vm.selectionArgs = arrayOf("${vm.attrDisplayUpdateName}")
                Log.i(TAG, "attrDisplayUpdateName correctly filled...")
                Toast.makeText(this, "Proceeding to make the insertion", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Proceeding to make the insertion")
                vm.update(this.contentResolver)
                Toast.makeText(this, "insertion finished", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "insertion finished")

            }else{
                Toast.makeText(this, "Some of the values haven't been settled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setObservers() {
        vm.attrResultUpdate.observe(this, { it -> textViewResultUpdate.text = it  })
    }
    private fun setViewModelValues() {
        Log.i(TAG,"setViewModelValues setting the values ")
        vm.attrDisplayUpdateName = editTextDisplayNameUpdate.text.toString()
        vm.attrNameNew = editTextDisplayNameNew.text.toString()
        Log.i(TAG, "values settled $vm")
    }


    companion object{
        const val TAG = "UpdateActivityLogger"
        const val PERMISSION_REQUEST_CODE = 200
        const val PERMISSION_WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"
    }
}

