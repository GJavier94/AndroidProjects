package com.example.storageapp.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.storageapp.AppSpecificStorageFragment
import com.example.storageapp.SharedStorageFragment

class PageAdapter(activity: FragmentActivity,var NUM_PAGES: Int) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AppSpecificStorageFragment()
            else -> SharedStorageFragment() //position 1 but else is required by when clause

        }
    }

}
