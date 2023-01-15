package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.Adapters.MyAdapterRecycleView
import org.w3c.dom.Text
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private lateinit var textViewSpinnerResult: TextView
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewSpinnerResult = findViewById<TextView>(R.id.textViewSpinnerResult)
        /**
         * A recycler view is a listview which dynamic e.g. it can reuse its viewholders to show data from a data source
         * the data source can have many items more than viewholder number
         * viewholder are the holder of the view
         * the view is the way in which the data source is show
         * this data source can come from a content provider, database, rest API, array of strings, xml resources
         *
         * in order to change the data that the viewholder is showing is necessary to have and adapter
         * so that the adapter binds data elements from the data source to the viewholder according to the interactions
         * with the user
         * the RecyclerView class has two inner clases
         *  1.-ViewHolder which is the class how shows the view and changes the data  and also defines listenes so it receives a view
         *  2.- Adapter<ViewHolder>(refDataSource) it's a generic Adapter which receives the datasource reference ( list of adaptees) and
         *  and has thre main methods
         *      1.- onCreateViewHolder is the one which given a view it inflates the view and creates a view holder which has the view
         *          so the layout manager calls this methods when creating viewholder that are going to be reused
         *          it can be for example 5
         *      2.-onBindViewHolder after having created a viewholder this is called as the method to bind the adaptee( datasource and the target) the recycler view
         *      it is called when putting the first viewholders
         *      and also when recycling them for other datasource each data source has a position
         */
        //let's get the data source from an array
        val arrayCountries = resources.getStringArray(R.array.Countries)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        //let's create an instance of the adapter
        val adapter = MyAdapterRecycleView(arrayCountries)

        recyclerView.adapter = adapter


        //Another Dynamic Interface View is
        /**
         * Spinner: show a list of strings into a list of views ordered in row line
         * it can uses an adapter to put data in a set of viewholder
         * Because those are strings and the viewholder is a text view
         * there exist an element which called ArrayAdapter
         * it converts and Array -> into a list of textViews
         * so it can be used as the adapter  of the spinner
         */
        spinner = findViewById(R.id.spinner)

        ArrayAdapter.createFromResource(this, R.array.Countries, android.R.layout.simple_list_item_1).also {
            adapter ->
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                textViewSpinnerResult.text = "$position ${(view as MaterialTextView).text}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                textViewSpinnerResult.text = "Nothing was selected"
            }

        }



    }



}