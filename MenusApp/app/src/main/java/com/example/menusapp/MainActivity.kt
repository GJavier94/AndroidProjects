package com.example.menusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.Observer
import com.example.menusapp.Constants.Tags
import com.example.menusapp.DialogFragments.ColorsDialogFragment
import com.example.menusapp.NotificationUpdateDB.Notification
import com.example.menusapp.ViewModels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {

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

            else ->{
                super.onOptionsItemSelected(item) // this returns false so that other fragments methods implementations can  handle the itemselected
            }
        }
    }

    private fun setOnlyVisible(FragmentTag: String) {
        /**
         * The logic we have is that all the fragments are running because the tasks they have are already to small
         * so we just use show() and hide() method to show one fragment at a time
         */

        this.supportFragmentManager.fragments.forEach { action ->
            this.supportFragmentManager.commit {
                hide(action)
            }
        }
        this.supportFragmentManager.commit {
            if(supportFragmentManager.findFragmentByTag(FragmentTag) != null){
                show(supportFragmentManager.findFragmentByTag(FragmentTag)!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Create and commit the fragmentChild of this activity
         */

        this.supportFragmentManager.commitNow{
            add<FragmentAlarm>(R.id.fragmentContainer_optionAlarm, Tags.FRAGMENT_ALARM)
            add<FragmentTimeHour>(R.id.fragmentContainer_optionTimeHour, Tags.FRAGMENT_TIME_HOUR)
        }
        setOnlyVisible(Tags.FRAGMENT_ALARM)


    }
    companion object{
        const val TAG = "MainActivityLogger"

    }
}