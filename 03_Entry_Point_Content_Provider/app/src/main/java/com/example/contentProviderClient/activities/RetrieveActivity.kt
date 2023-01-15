package com.example.contentProviderClient.activities

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contentProviderClient.R
import com.example.contentProviderClient.viewmodel.ViewModelRetrieveActivity


/**
 * In order to interact with a content provider is necessary to request a permision on runtime
 * so that the user grant it
 */
class RetrieveActivity : AppCompatActivity() {

    private lateinit var listViewSmsTable: ListView
    private lateinit var viewTextSmsSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var vm: ViewModelRetrieveActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve)
        vm = ViewModelProvider(this).get(ViewModelRetrieveActivity::class.java)
        buttonSearch = findViewById<Button>(R.id.buttonSearch)
        viewTextSmsSearch = findViewById<EditText>(R.id.viewTextSmsSearch)

        listViewSmsTable = findViewById<ListView>(R.id.listViewSmsTable)

        Log.i(TAG, "Checking permissions required")
        if( !vm.permissionToReadContentProvider || this.applicationContext.checkSelfPermission(PERMISSION_READ_SMS)== PackageManager.PERMISSION_DENIED ){
            Log.i(TAG, "One Permission is required")
            Log.i(TAG, "Requiring the permission $PERMISSION_READ_SMS")
            // Require the permission one more time
            requestPermissions(arrayOf(PERMISSION_READ_SMS), PERMISSION_REQUEST_CODE)
        }

        initListeners()
        setObservers()

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
                    Log.i(TAG, "The permission $PERMISSION_READ_SMS was granted")
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                    vm.permissionToReadContentProvider = true
                }else{
                    Log.i(TAG, "The permission $PERMISSION_READ_SMS was denied")
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        listViewSmsTable.adapter = vm.adapter

    }
    private fun initListeners() {
        buttonSearch.setOnClickListener {
            Log.i(TAG,"Calling onClick buttonSearch...")
            val searchText = viewTextSmsSearch.text.toString()
            Log.i(TAG,"Getting the searchText: $searchText")
            if(searchText.compareTo("") == 0){
                Log.i(TAG,"searchText was null")
                vm.searchId.value = ""
                vm.selectionClause = null
                vm.selectionArgs = emptyArray<String>()
            }else{
                Log.i(TAG,"searchText had value")
                vm.searchId.value = searchText
                vm.selectionClause = "${ Telephony.Sms.Inbox._ID} = ?"
                vm.selectionArgs = arrayOf(searchText)
            }




            Log.i(TAG, "Executing the query")
            // time to create the cursor or update it
            // do query
            vm.doQuery(this.contentResolver)
            Log.i(TAG, "Query executed")
            Log.i(TAG, "Num records Count: ${vm.cursor?.count}")

            Log.i(TAG, "Setting the adapter")
            if(vm.adapter != null ){
                Log.i(TAG, "Activity comes from ondestroy by constraint Changes... just changing cursor")
                vm.adapter?.swapCursor(vm.cursor)
            }else{
                Log.i(TAG, "First time adapter created ")
                vm.adapter = SimpleCursorAdapter(
                    this.applicationContext,
                    R.layout.row_word_table,
                    vm.cursor,
                    vm.listColumnsAdapter,
                    vm.listIdItems)
                Log.i(TAG, "Adapter created ")
            }
            Log.i(TAG, "Setting the adapter into the lisView")
            listViewSmsTable.adapter = vm.adapter
            Log.i(TAG, "Adapter set")
        }
    }
    private fun setObservers() {
        vm.searchId.observe(this, Observer { t -> viewTextSmsSearch.setText(t)  })



    }
    companion object{
        val TAG = "MainActivityLogger"
        const val PERMISSION_REQUEST_CODE = 200
        const val PERMISSION_READ_SMS = "android.permission.READ_SMS"
    }
}