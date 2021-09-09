package com.example.menusapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.menusapp.R

class ColorsDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        return builder.run{
            setTitle(R.string.menu_color_dialog_title)
            setItems(R.array.menu_colors,
                DialogInterface.OnClickListener { dialog, which ->
                    when(which){
                        0 ->{
                            Toast.makeText(this.context, "You selected ${resources.getString(R.string.color_red)}", Toast.LENGTH_SHORT).show()
                        }
                        1 ->{
                            Toast.makeText(this.context, "You selected ${resources.getString(R.string.color_blue)}", Toast.LENGTH_SHORT).show()
                        }
                        2->{
                            Toast.makeText(this.context, "You selected ${resources.getString(R.string.color_orange)}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            create()
        }


    }
    companion object{
        const val TAG = "ColorsDialogFragment"
    }
}
