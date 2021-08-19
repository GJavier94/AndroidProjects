package com.example.mainactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    /*
    Activity life cycle
    An activity is the only app component entry point which has user interface
    The UI Thread is in charge  to handle the entry points by calling their lifecycle methods
    The seven stages that an activity can have are
    OnCreate -> here the components of the UI , Views etc are loaded-> it's in charger to use the setContentView method to load the layout on the window
    OnStart -> The activity is visible, and some other initialization are made here
    OnResume -> The user has It has the core code of the activity
    OnPause -> it's not recommended to store data here, it only serves as a method to update UI
    OnStop -> Here We can save the data and the state of the act to be retrieve later
    OnRestart -> called by stop() state so that can retrieve dat and state of the act
    OnDestroy -> Execute at the end works as a releaser of resources etc.
    * */

    /*
    onCreate:
    if the act get's destroyed and some date save is  neeeded to show a new version of the act
    it is possible doing that by using the:
    Bundle: savedInstanceState -> It stores the info
    it will need two methods
    the one that is executed when destroying the app onSaveInstanceState
    The one that is executed when restoring the app onRestoreInstanceState
     */

    private lateinit var textViewcontactInfo: TextView
    private lateinit var buttonChooseContact: Button
    private lateinit var buttonActionSend: Button
    private lateinit var textView: TextView
    private var countingTimes:Int = 0;





    /**
     * onCreate()  It has the starting logic when it is first created the act
     * it  get's all the references of the views declares the listeners and implements them
     * it has the setcontenview method to bind the layout to the activity
     * here the activity is not visible and not focused
     */

    /**While only working with the activity life cycle methods
     *There will be some components which will be needing to synchronized with the life methods
     * so that they will necessary  being initialized and released within the methods
     * this arises two problems:
     *      1)the need to invoke all the methods (example obj.start() in onStart() method) within a life cycle method
     *      producing a lot of code and also difficult to sustain the project
     *      2)If the initialization process for example is huge maybe onStart() couldn't have finished while the onStop()
     *      method it's been executed and producing memory leaks and inconsistencies
     *
     *  The solution is to create lifeCycle Aware component by
     *  1.- Creating a class which will be the observer of the life cycle act or fragment
     *  so that it handles the events dispatch  by the act  by using anotation, this is reached by implementing the
     *  interface LifecycleObserver
     *      1.1 put the annotations and provide implementation methods to initialice or release a component
     *
     *  2.- the component you'll be observing to needs to implements LifeCycleOwner interface,
     *      in the case of ACT or Fragments they're already implementing it
     *
     *      this Interface only has one method  getLifeCycle() which returns and "Lifecycle" object
     *
     * Lifecycle class: Is the class that holds the info about the life cycle states of the component
     * which are only 5
     * initialized, created,started, resumed, destroyed
     *
     * INITIALIZED
     * CREATED -> From initialized to  created with ON_CREATE
     *            From started to created with ON_STOP
     * STARTED -> From created to started with ON_START
     *            From resumed to started with ON_PAUSE
     * RESUMED -> from started to resume with ON_RESUME
     * DESTROYED -> from created to destroyed with ON_DESTROY
     *
     * EVENTS -> onCreate(), onStart(), onResume(), onPause(),onStop(), onDestroy()
     * */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observer = MyObserver()
        this.lifecycle.addObserver(observer)


        textViewcontactInfo = findViewById<TextView>(R.id.TextViewcontactInfo)
        val buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.setOnClickListener{
            val intent = Intent(this, ChildActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                type = "plain/text"
            }
            startActivity(intent)
        }
        Log.i(TAG, "onCreate" )

        textView = findViewById<TextView>(R.id.textView)


        /**
         * We can interact with other app functionalites by calling them by using and intent
         * We create the intent, there we need to specify the action to be taken
         * and the extra parameters like  data to be transmitted of the type (key value)
         * Intent class already offers some constant variables which acts as keys
         *
         * If our own app doesn't provides any component that can handle the intent request
         * the system will look for candidates which can handle it
         */

        buttonActionSend = findViewById<Button>(R.id.buttonActionSend)
        buttonActionSend.setOnClickListener{
            val intent = Intent( Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_EMAIL,arrayOf("javargmar@gmail.com"))

                putExtra(Intent.EXTRA_CC, "")
                putExtra(Intent.EXTRA_BCC, "")
                putExtra(Intent.EXTRA_SUBJECT,
                    "Playlist Details")
                setType("text/html")
            }
            this.startActivity(intent)

        }
        /**
         * it is also possible to get a response from the request by calling the method
         * startActivityForResult(int requestCode, Intent)
         *      requestCode: it serves as an identifier for the as this activity can make many calls to other activities
         *
         * in order to receive the response the activity has to provide and implementation for
         *
         * this method is called anytime another activity returns a response to this act
         * onActivityResult(int requestCode, int resultCode, Intent)
         *      requestCode: the identifier code of the call
         *      resultCode: the constant value returned from the secondActivity
         *      Intent: the  intent object which stores the data returned
         */

        buttonChooseContact = findViewById<Button>(R.id.buttonChooseContact)
        buttonChooseContact.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            this.startActivityForResult(intent, REQUEST_CONTACT)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CONTACT -> {
                if(resultCode == RESULT_OK && data != null){
                    Log.i(TAG,"The data is : ${data?.data}" )
                    /**
                     * The intent  data has an uri content://com.android.contacts/data/2584 this is the uri to acces the data from that contact
                     */

                    //the resource is stored in that uri, so it offers a content provider to acces the data
                    //so we will need the API contentResolver
                    //we will use the method query() from the contentResolver API and send the Uri there
                    val uri = data.data
                    if(uri != null ){

                        val cursor = this.contentResolver.query(uri ,null, null, null,null )
                        cursor?.moveToFirst()
                        val nameIndex:Int = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME) ?: 0
                        val phoneIndex:Int = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) ?: 0
                        val name:String?  = cursor?.getString(nameIndex)
                        val phone:String? = cursor?.getString(phoneIndex)
                        textViewcontactInfo.text = "$name $phone"

                    }

                }else{
                    Toast.makeText(this, "Something bad happens with the request", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }




    /**
     * onStart() called from onCreate or onRestart()
     * It is in charge to reinitialized the resources freed  from onStop method
     * in this state the app is finally visible but not in focus
     *
     */
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart" )
    }
    /**
     * onResume():
     * The activity is visible, is focused and is ready to interact with the user
     * its incoming transitions can come from onPause() or onStart()
     * so is responsible for initializing the resources freed on onPause()
     * It has the core code, or core functionality
     * it ends or goes to onPause() state when the user uses other activity
     **/
    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }
    /**
     * This method is executed when the onResume() method ends
     * Here the activity can be visible or invisible but the act have lost focus
     * moltimode window: visible but not in focus
     * only one window: partially visible not focus
     * -----> when a dialog comes into the foreground
     * lifecycle aware components have to  release resourcer or decrease the work
     * the execution is too short so saving data or long tasks shouldn't be done here
     */

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause")
    }

    /**
     * onStop() comes from onPause()
     * Not visible
     * no focus
     * Here  the act is no longer visible and might mean two things the
     * act is going to be destroyed by finish()
     * it's been replace by another act
     * Because it's not visible
     *  free resources
     *  adjust function -> put them in pause
     *  work diminished ->
     *      if it on resume GPS location was very refined , then changed it to more gross
     *  Save data to a BD or some persistent source
     *
     */
    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"onRestart")
    }
    /**
     * onDestroy(): called from onStop()
     * it means two things
     * 1-> the act is getting destroyed
     * 2-> there's a change in the constraints of the app like -> orientation
     *
     * OnDestroy needs to release all the missing components and clean everything
     *
     *
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    /**
     * In order to save the state of the app
     * we can combine the bundle instance state and the viewmodel architecture
     *
     * There's a method isFinished() whicha says if:
     *              true: Act is getting finished and destroyed
     *              false: Act is going to be recreated due to some constraint app changes ( orientation)
     * ViewModel -> serves for large amount of data
     *          if isFinished() is true  implies ViewModel needs to call Oncleared() to clean data
     *          if isFinished() is false implies the act is going to be recreated and ViewModel has to do nothing cause already has the data
     */

    /**
     * Saving and restoring UI states
     * When the act is destroyed by system constraints:
     * -> changing configuration (orientation)
     * -> changing to multimode window
     * -> switching apps, after some time get killed by system
     * The instance state of the activity is necessary to be saved and retrevied
     * by :
     * using ViewModel -> for large amount of data
     * InstanceState:Bundle -> object which holds small amounts of data like strings and so on
     *
     * Then retrieving and restoring bundle by using
     *  onSaveInstanceState
     *  onRestoreInstanceState
     */


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("TEXT_VIEW_KEY",(++countingTimes) )
        Log.i(TAG, "onSaveInstanceState.... ${outState.getInt("TEXT_VIEW_KEY")}" )
    }
    /*
    *It seems this method is only called when the act is destroyed an recreated fast!!
    */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState...." )

        val num =  savedInstanceState.getInt("TEXT_VIEW_KEY")
        Log.i(TAG, "onRestoreInstanceState....$num" )
        textView.text = num.toString()
        countingTimes = num

    }

    /**
     * Ejection from Memory:
     * Each app is running in a different process
     * process1 --- > app1
     * process2 --- > app2
     *
     * If the System needs more memory it will kill an app. accordin to
     *
     *  likelihood            state process    act state
     *  most                    empty           destroy
     *  more                    background without focus  stop
     *                          background without focus  pause
     * less                     foreground      resume, start, create
     */
    companion object{
        val TAG = "MainActivityLogger"
        val REQUEST_CONTACT = 0
    }


    /**
     * The Lifecycle of an activity goes as follows
     *                                  -----------------
     *                                  |               |
     *                                  |               |
     *                                  v               |
     * onCreate()----> onStart()---->onResume()---->onPause()---->onStop()---->onDestroy()
     *                     ^                                        |
     *                     |                                        |
     *                     |---------onRestart()<-------------------|
     */

}