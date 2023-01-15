package com.example.menusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.example.menusapp.Constants.Tags
import com.example.menusapp.DialogFragments.ColorsDialogFragment
import com.example.menusapp.DialogFragments.DialogFragmentSignIn
import com.example.menusapp.DialogFragments.MixedShowFragment
import com.example.menusapp.NotificationUpdateDB.Notification
import com.example.menusapp.ViewModels.ViewModelMainActivity

class MainActivity : AppCompatActivity(), DialogFragmentSignIn.onClickFeelingButtonSignInListener {

    private var orientationScreen:Boolean = false
    private val viewModel: ViewModelMainActivity by viewModels()
    private val notifications = Notification.LiveDataDelete.value


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = this.menuInflater
        menu?.apply {
            add("Item added programatically")
        }
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*
        MenuItem is a class object which has all the attributes of menu item
        including the ID in this way we can now which option item was clicked!
         */

        /**
         * In your implementation of onOptionsItemSelected(), use a switch statement on the itemId of the menu item.
         * If the selected item is yours, handle the touch appropriately and return true to indicate that the click
         * event has been handled.
         * If the selected item is not yours, call the super implementation.
         * By default, the super implementation returns false to allow menu processing to continue.
         */
        return when(item.itemId){
            R.id.option_alarm ->{
                setOnlyVisible(Tags.FRAGMENT_ALARM)
                true
            }
            R.id.option_time_hour ->{
                setOnlyVisible(Tags.FRAGMENT_TIME_HOUR)
                true
            }
            R.id.option_timer -> {
                val dialogFragmentSignIn = DialogFragmentSignIn()

                dialogFragmentSignIn.show(this.supportFragmentManager, DialogFragmentSignIn.TAG)
                //here we only show the dialog to login the interface callbacks are at the bottom there we  show the fragment in case credentials are ok
                true
            }
            R.id.option_settings ->{
                true
            }
            R.id.option_changeMenuColor -> {
                val colorsDialogFragment = ColorsDialogFragment()
                colorsDialogFragment.show(this.supportFragmentManager,ColorsDialogFragment.TAG )
                true
            }
            R.id.option_mixedFragment ->{

                Log.i(TAG, "$orientationScreen")
                /**
                 * this dialogfragment can shown as a dialog full screen fragment
                 * or an embbed fragment
                 * difference:
                 *
                 * we use in this case the app resources Framework
                 * to decide which way based on the value of a boolean within the xml bools
                 * show()
                 * or as
                 * action()
                **/


                when(orientationScreen){
                    // it's portrait  -> fragment embedded -> transaction
                    true ->{
                        setOnlyVisible()
                        this.supportFragmentManager.commit {
                            add<MixedShowFragment>(R.id.fragmentContainer_optionMixedFragment, MixedShowFragment.TAG_FRAGMENT)
                            addToBackStack(null)
                        }

                    }
                    // it's landscape  -> fragment in Dialog ( modal way ) ->  show
                    false ->{
                        val mixedShowFragment = MixedShowFragment()
                        mixedShowFragment.show(this.supportFragmentManager, MixedShowFragment.TAG_FDIALOG)
                    }
                }
                true
            }

            else ->{
                super.onOptionsItemSelected(item) // this returns false so that other fragments methods implementations can  handle the itemselected
            }
        }
    }

    private fun setOnlyVisible(fragmentTag: String = "NONE") {
        /**
         * The logic we have is that all the fragments are running because the tasks they have are already to small
         * so we just use show() and hide() method to show one fragment at a time
         */

        this.supportFragmentManager.fragments.forEach { action ->
            this.supportFragmentManager.commit {
                hide(action)
            }
        }
        if(fragmentTag.compareTo("NONE") != 0){
            this.supportFragmentManager.commit {
                if(supportFragmentManager.findFragmentByTag(fragmentTag) != null){
                    show(supportFragmentManager.findFragmentByTag(fragmentTag)!!)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orientationScreen = resources.getBoolean(R.bool.portrait)



        /**
         * Create and commit the fragmentChild of this activity
         */

        this.supportFragmentManager.commitNow{
            add<FragmentAlarm>(R.id.fragmentContainer_optionAlarm, Tags.FRAGMENT_ALARM)
            add<FragmentTimeHour>(R.id.fragmentContainer_optionTimeHour, Tags.FRAGMENT_TIME_HOUR)
            add<FragmentTimer>(R.id.fragmentContainer_optionTimer, Tags.FRAGMENT_TIMER)
        }
        setOnlyVisible(Tags.FRAGMENT_ALARM)


    }
    override fun onDialogPositiveClick(dialog: DialogFragmentSignIn) {
        val email = dialog.loginEditTextEmail.text.toString()
        val password = dialog.loginEditTextPassword.text.toString()

        var correctEmail = true
        var correctPass = true

        if(email.compareTo("javarmgar@gmail.com") != 0 ){
            Toast.makeText(this, "wrong email", Toast.LENGTH_LONG)
            correctEmail = false
        }
        if(password.compareTo("123") != 0 ){
            Toast.makeText(this, "wrong password", Toast.LENGTH_LONG)
            correctPass = false
        }

        if(correctEmail && correctPass){
            setOnlyVisible(Tags.FRAGMENT_TIMER)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragmentSignIn) {
        Toast.makeText(this, "The login was cancelled", Toast.LENGTH_SHORT)
    }

    override fun onDialogNeutralClick(dialog: DialogFragmentSignIn) {
        Log.i(TAG, "This does nothing...")
    }

    companion object{
        const val TAG = "MainActivityLogger"

    }
}