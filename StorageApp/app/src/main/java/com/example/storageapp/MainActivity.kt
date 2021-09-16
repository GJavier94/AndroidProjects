package com.example.storageapp

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.example.storageapp.Adapters.PageAdapter
import com.example.storageapp.Constants.Constants
import com.example.storageapp.ViewModels.ViewModelActivity

class MainActivity : AppCompatActivity(), FragmentDialogThemes.OnClickOptionThemeInterface {

    val viewModel: ViewModelActivity by viewModels()

    private lateinit var viewPager: ViewPager2


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val view = this.menuInflater.inflate(R.menu.main_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_change_color_theme -> {
                val fragmentDialogThemes = FragmentDialogThemes()
                fragmentDialogThemes.show(this.supportFragmentManager, FragmentDialogThemes.TAG)
            }
        }
        return true
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)

        /**
         * Adding SharedPreferences to the app
         */

        val defaultSharedPreferences = this.applicationContext.getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,
             Context.MODE_PRIVATE)

        val colorTheme = defaultSharedPreferences.getInt(Constants.KEY_OPTION_THEME,Color.MAGENTA)
        viewPager.setBackgroundColor(colorTheme)

        viewPager.adapter = PageAdapter(this, NUM_PAGES)
        checkPermissionRequested()



    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPermissionRequested() {
        if(!viewModel.arePermissionRequested()){
            val arrayPermissionsRequired = viewModel.PERMISSIONS_STORAGE.filter {
                    perPair: Pair<String, Int> ->
                perPair.second != PackageManager.PERMISSION_GRANTED
            }.map { it.first }.toTypedArray() // this method helps to convert a list into an arrayList
            for(i in 0..viewModel.PERMISSIONS_STORAGE.size-1){
                if(viewModel.PERMISSIONS_STORAGE[i].second != PackageManager.PERMISSION_GRANTED){
                    viewModel.PERMISSIONS_STORAGE[i] = viewModel.PERMISSIONS_STORAGE[i].copy(second = viewModel.PERMISSION_REQUESTED) }
                viewModel.permissionsRequested = true
            }
            if(arrayPermissionsRequired != null ){
                this.requestPermissions(arrayPermissionsRequired, viewModel.REQUEST_CODE)
            }
        }else{
            Log.i(TAG, "do not require any request permission")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var indexPermissions:Int = 0
        if(requestCode == viewModel.REQUEST_CODE){
            for (i in 0..viewModel.PERMISSIONS_STORAGE.size-1){
                if(viewModel.PERMISSIONS_STORAGE[i].second == viewModel.PERMISSION_REQUESTED){
                    viewModel.PERMISSIONS_STORAGE[i] = viewModel.PERMISSIONS_STORAGE[i].copy(second = grantResults[indexPermissions])
                    indexPermissions++
                }
            }
        }
    }

    override fun onClickThemeOption(option: Int) {
        val sharedPreferences = this.getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,
            Context.MODE_PRIVATE)

        val color = this.resources.getIntArray(R.array.themeColorsValues)[option]
        Log.i(TAG, "color: ${Integer.toHexString(color) }}")
        viewPager.setBackgroundColor(color)
        with(sharedPreferences.edit()){
            putInt(Constants.KEY_OPTION_THEME,color)
            commit()
        }

    }

    companion object{
        const val TAG = "MainActivityL"
        const val NUM_PAGES:Int = 2
    }

}