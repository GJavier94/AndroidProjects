package com.example.menusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.example.menusapp.Constants.Tags
import com.example.menusapp.ViewModels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModelMainActivity by viewModels()
    /**
     * This method is called when the activity is starting so that it can show a menu
     * from a xml resource with the main tag as root
     *
     * It is possible to add new items by using add method from the menu Object
     * Menu xml elements are inflated ( create a kotlin object in memory of the Menu class )
     * The items in Menu xml file are MenuItem Objects in kotlin or java
     *
     * Activity has a method menuInflater which has a method inflate -> to inflate a view on the UI
     * Steps
     *  1.- Create xml file in the res/menu folder
     *  2.- override onCreateOptionsMenu so that the menu is inflate when running the ACT
     *  3.- onOptionsItemSelected: -> override a method which is called when an optiones item is clicked
     *
     */

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
            else ->{
                super.onOptionsItemSelected(item)
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