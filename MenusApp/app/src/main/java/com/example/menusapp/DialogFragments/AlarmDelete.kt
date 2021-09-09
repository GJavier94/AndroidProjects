package com.example.menusapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.menusapp.R

class AlarmDelete: DialogFragment() {

    /**
     * Dialogs: they are small window that prompts to the user so that the user
     * cant make a decision to proceed with an operation they are shown over a fragment or activity
     * examples: erasing data...
     *
     * Dialog class is the implementation in android of this modals
     *
     * There are some template classes:
     * AlertDialog, DatePickerDialog, TimePickerDialog
     *
     * A dialog can be added into a UI but it will not have it's own lifecycle son
     * for changes in configuration or back press button it won't know how to behave
     *
     * ------------>    DialogFragment is the fragment class to hold Dialogs
     * It will attached  the Dialog to its FragmentManager and will have a lifeCycle
     * THIS is the DialogFragment Class which is holding AlertDialog
     *
     */

    //In this method the dialog is created
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //AlertDialog class implements the Builder design pattern
        // in order to create a Dialog first we wil need to make an instance of its builder and set the values through the methods
        // then invoke .Build() to have an AlertDialog Instance
        // onCreateDialog returns this instance
        // When Instantiating a DialogFragment somewhere else  this method will be called and
        // by calling .show() ( like in toast) the DialogFragment( with the alertDialog inside) will be shown


        /***
         * The AlertDialog's attributes are:
         *
         * title
         * message = contentArea
         * set<feeeling>Button -> to set onclicklisteners...
         */
        val builder = AlertDialog.Builder(this.activity)
        builder.apply {
            setTitle(R.string.delete_dialog_title)
            setMessage(activity?.resources?.getString(R.string.dialog_delete_message))
            setPositiveButton(R.string.accept_text, DialogInterface.OnClickListener {
                    dialog, which ->
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->{
                        Toast.makeText(this@AlarmDelete.context, "The alarm will be deleted", Toast.LENGTH_SHORT   ).show()
                    }
                }
            })
            setNegativeButton(R.string.cancel_text, DialogInterface.OnClickListener {
                    dialog, which ->
                when(which){
                    DialogInterface.BUTTON_NEGATIVE ->{
                        Toast.makeText(this@AlarmDelete.context, "nothing will be deleted", Toast.LENGTH_SHORT   ).show()
                    }
                }
            })
        }

        return builder.create()
    }

    /**Positive
     * You should use this to accept and continue with the action (the "OK" action).
     * Negative
     * You should use this to cancel the action.
     * Neutral
     * You should use this when the user may not want to proceed with the action, but doesn't necessarily want to cancel.
     * It appears between the positive and negative buttons. For example, the action might be "Remind me later."
     */


}
