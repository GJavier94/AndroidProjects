package com.example.storageapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FragmentDialogThemes() :DialogFragment() {

    lateinit var onClickOptionThemeInterface:OnClickOptionThemeInterface
    interface OnClickOptionThemeInterface{
        fun onClickThemeOption(option:Int)
    }

    override fun onAttach(context: Context) {
        onClickOptionThemeInterface = this.activity as OnClickOptionThemeInterface
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        builder.apply{
            setTitle( resources.getString(R.string.title_dialog_choose_theme))

            setItems(R.array.themeColorsKeys, DialogInterface.OnClickListener {
                    dialog, which ->
                this@FragmentDialogThemes.onClickOptionThemeInterface.onClickThemeOption(which)
            })
        }

        return builder.create()
    }
    companion object{
        const val TAG = "FragmentDialogThemes"
    }
}
