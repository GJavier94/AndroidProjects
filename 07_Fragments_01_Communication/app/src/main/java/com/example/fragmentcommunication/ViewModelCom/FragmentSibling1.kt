package com.example.fragmentcommunication.ViewModelCom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*
import androidx.lifecycle.Observer

import com.example.fragmentcommunication.R
import com.example.fragmentcommunication.ViewModelCom.Models.Person
import com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity.ViewModelOneActivity
import com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity.ViewModelFragmentSibling1


class FragmentSibling1 : Fragment() {

    private lateinit var textViewSibling1toChild: TextView
    private lateinit var buttonSibling1toSibling2: Button
    private val viewModel: ViewModelOneActivity by activityViewModels()
    private val viewModelFragmentSibling1: ViewModelFragmentSibling1 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(
            Keys.ACTIVITY_TO_SIBLING_1
        ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(TAG, "person: $person")
        }

        setFragmentResultListener(
            Keys.CHILD_TO_SIBLING_1,

            ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(FragmentSibling2.TAG, "person: $person")
        }
        setFragmentResult(
            Keys.SIBLING1_TO_CHILD,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Sam", "Johansson", 2) )
            }
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sibling1, container, false)

            this.childFragmentManager.commit{
                add<FragmentChild>(R.id.fragmentContainerChild,"FragmentChild")
                setPrimaryNavigationFragment( childFragmentManager.findFragmentByTag("FragmentChild"))
            }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewSibling1toChild = view.findViewById<TextView>(R.id.textViewSibling1toChild)

        this.viewModelFragmentSibling1.counterSibling1toChild.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "observer changing value ...$it")
            textViewSibling1toChild.text = it.toString()
        })


        buttonSibling1toSibling2 = view.findViewById<Button>(R.id.buttonSibling1toSibling2)
        buttonSibling1toSibling2.setOnClickListener {
            Log.i(TAG, "button changing value ..")
            viewModel.counterSibling1toSibling2.value = viewModel.counterSibling1toSibling2.value?.plus(1)
        }
    }

    override fun onResume() {
        super.onResume()

        this.setFragmentResult(
            Keys.SIBLING_1_TO_ACTIVITY,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Paco", "Perez", 19) )
            }
        )

        this.setFragmentResult(
            Keys.SIBLING1_TO_SIBLING2,
            Bundle().apply {
                putParcelable(BUNDLE_PERSON, Person("Karla", "mora", 21) )
            }
        )
        setFragmentResultListener(
            Keys.SIBLING2_TO_SIBLING1
        ){
                _, bundle ->
            val person: Person? = bundle.getParcelable(BUNDLE_PERSON)
            Log.i(TAG, "personSIBLING2_TO_SIBLING1: $person")
        }
    }
    companion object{
        const val TAG = "FragmentSibling1Logger"
    }
}