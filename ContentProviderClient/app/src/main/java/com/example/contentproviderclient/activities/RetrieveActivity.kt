package com.example.contentproviderclient.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.contentproviderclient.R
import com.example.contentproviderclient.viewmodel.ViewModelRetrieveActivity

class RetrieveActivity : AppCompatActivity() {

    private lateinit var listViewWordTable: ListView
    private lateinit var viewTextWordSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var viewModelRetrieveActivity: ViewModelRetrieveActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve)
        viewModelRetrieveActivity = ViewModelProvider(this).get(ViewModelRetrieveActivity::class.java)
        buttonSearch = findViewById<Button>(R.id.buttonSearch)
        viewTextWordSearch = findViewById<EditText>(R.id.viewTextWordSearch)

        listViewWordTable = findViewById<ListView>(R.id.listViewWordTable)





        initListeners()





        //this.contentResolver.query()

    }

    private fun initListeners() {
        buttonSearch.setOnClickListener {
            val searchText = viewTextWordSearch.text.toString()
            if(searchText.equals("")){
                viewModelRetrieveActivity.searchText.value = ""
                viewModelRetrieveActivity.selectionClause = null
                viewModelRetrieveActivity.selectionArgs = emptyArray<String>()
            }else{
                viewModelRetrieveActivity.searchText.value = searchText
                viewModelRetrieveActivity.selectionClause = null
                viewModelRetrieveActivity.selectionArgs = arrayOf(searchText)
            }




            // time to create the cursor or update it
            // do query
            viewModelRetrieveActivity.doQuery(this.contentResolver)

            Log.i(TAG, "Count: ${viewModelRetrieveActivity.cursor?.count}")

            if(viewModelRetrieveActivity.adapter != null ){
                viewModelRetrieveActivity.adapter?.swapCursor(viewModelRetrieveActivity.cursor)
            }else{
                viewModelRetrieveActivity.adapter = SimpleCursorAdapter(
                    this.applicationContext,
                    R.layout.row_word_table,
                    viewModelRetrieveActivity.cursor,
                    viewModelRetrieveActivity.listColumnsAdapter,
                    viewModelRetrieveActivity.listIdItems)
            }
            listViewWordTable.adapter = viewModelRetrieveActivity.adapter
        }
    }
    companion object{
        val TAG = "MainActivityLogger"
    }
}