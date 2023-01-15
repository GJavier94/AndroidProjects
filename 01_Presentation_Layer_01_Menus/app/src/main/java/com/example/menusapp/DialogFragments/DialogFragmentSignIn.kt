package com.example.menusapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.menusapp.R
import java.lang.ClassCastException

class DialogFragmentSignIn:DialogFragment() {
    /**
     * Purpose:
     * owner of the DialogFragment handle the events from the DialogFragment
     * 1.- create an interface with the listeners
     * 2.- create a variable which a reference of the listenerHandler 
     * 3.- initialiaze it on OnAttach  THE ACTIVITY IS THE INTERFACE
     * make the activity implements the interface
     *      now the activity needs to implements the methods that are called 
     *      by the listener of the <feeling>Button on clicks..
     */
    interface onClickFeelingButtonSignInListener {
        fun onDialogPositiveClick(dialog:DialogFragmentSignIn)
        fun onDialogNegativeClick(dialog:DialogFragmentSignIn)
        fun onDialogNeutralClick(dialog:DialogFragmentSignIn)
    }

    internal lateinit var loginEditTextPassword: EditText
    internal lateinit var loginEditTextEmail: EditText
    internal lateinit var interfaceListenerHandler:onClickFeelingButtonSignInListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            interfaceListenerHandler = context as onClickFeelingButtonSignInListener
        }catch (e:ClassCastException){
            throw ClassCastException( "${context.toString()} must implement onClickFeelingButtonSignInListener")
        }

    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        /**
         * In order to inflate a custom Dialog it is necessary a layout to atached it to the
         * alertDialog
         * via the layoutInflater object which inflates the layout into the alertDialog
         */
        val inflater = requireActivity().layoutInflater
        val viewAlertDialog = inflater.inflate(R.layout.dialog_sign_in,null )

        loginEditTextEmail = viewAlertDialog.findViewById(R.id.login_editTextEmail)
        loginEditTextPassword = viewAlertDialog.findViewById(R.id.login_editTextPassword)




        builder.apply {
            setTitle(R.string.sign_in_dialog_title)
            setView(viewAlertDialog)
            setPositiveButton(R.string.accept_text,
                DialogInterface.OnClickListener { dialog, which ->
                    interfaceListenerHandler.onDialogPositiveClick(this@DialogFragmentSignIn)
                })
            setNegativeButton(R.string.cancel_text,
                DialogInterface.OnClickListener { dialog, which ->
                    interfaceListenerHandler.onDialogNegativeClick(this@DialogFragmentSignIn)
                })

        }


        return builder.create()
    }
    companion object{
        const val TAG = "DialogFragmentSignIn"
    }
}
