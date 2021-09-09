package com.example.menusapp.NotificationUpdateDB

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.menusapp.DialogFragments.AlarmDelete

object Notification {
    const val TAG = "Notification"
    class Delete(
        internal var ready:Boolean = true,
        internal var deleteChore:Boolean = false,
        internal var position:Int = 0){

        init {
            Log.i(TAG, "Creating Delete object to be observed..." )
        }

        internal fun delete(position:Int){
            this.position = position
            this.deleteChore = true
            Log.i("Notification", "Delete:$deleteChore position: $position")
        }

        override fun toString(): String {
            return "Delete(deleteChore=$deleteChore, position=$position)"
        }

    }

    val LiveDataDelete: MutableLiveData<Delete> = MutableLiveData(Delete()).apply { Log.i(TAG,"LiveDataDelete getting created..." ) }
    internal fun delete(position: Int) {
        LiveDataDelete.value.also {
            delete ->
            if(delete?.ready == true){
                delete.position = position
                delete.deleteChore = true
                Log.i(AlarmDelete.TAG, "${delete}")
            }
        }
        //force notification
        LiveDataDelete.value = LiveDataDelete.value
    }
}