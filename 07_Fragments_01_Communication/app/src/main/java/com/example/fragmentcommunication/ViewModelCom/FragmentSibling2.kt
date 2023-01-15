package com.example.fragmentcommunication.ViewModelCom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import com.example.fragmentcommunication.R
import com.example.fragmentcommunication.ViewModelCom.Models.Person
import com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity.ViewModelOneActivity


class FragmentSibling2 : Fragment() {

    private lateinit var textViewSibling1toSibling2: TextView
    private lateinit var buttonSibling2toActivity: Button
    private val viewModel: ViewModelOneActivity by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(
            Keys.ACTIVITY_TO_SIBLING_2
        ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(TAG, "person: $person")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sibling2, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSibling1toSibling2 = view.findViewById<TextView>(R.id.textViewSibling1toSibling2)

        this.viewModel.counterSibling1toSibling2.observe(viewLifecycleOwner, Observer {
            Log.i(FragmentSibling1.TAG, "observer changing value ...$it")
            textViewSibling1toSibling2.text = it.toString()
        })


        buttonSibling2toActivity = view.findViewById<Button>(R.id.buttonSibling2toActivity)
        buttonSibling2toActivity.setOnClickListener {
            Log.i(FragmentSibling1.TAG, "button changing value ..")
            viewModel.counterSibling2toActivity.value = viewModel.counterSibling2toActivity.value?.plus(1)
        }
    }

    override fun onResume() {
        super.onResume()

        this.setFragmentResult(
            Keys.SIBLING_2_TO_ACTIVITY,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Eugenia", "Castillo", 19) )
            }
        )
        this.setFragmentResult(
            Keys.SIBLING2_TO_SIBLING1,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Daniel", "Bernardo", 2) )
            }
        )
        setFragmentResultListener(
            Keys.SIBLING1_TO_SIBLING2
        ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(FragmentSibling1.TAG, "SIBLING1_TO_SIBLING2: $person")
        }
    }
    companion object{
        const val TAG = "FragmentSibling2Logger"
    }

}