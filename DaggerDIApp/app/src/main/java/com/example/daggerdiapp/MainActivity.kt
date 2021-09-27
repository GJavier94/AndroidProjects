package com.example.daggerdiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.daggerdiapp.viewmodels.LoginViewModel
import com.example.daggerdiapp.viewmodels.ViewModelMainActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    /**
     * What if want one dependencies to be in some cases the same instance and in some others another one
     *
     * For example
     * Case 1:
     * instance1 Activity -> instance1 ViewModel
     * instance2 Activity -> instance2 ViewModel                                -> can be solve by not putting scope annotations
     * for each new instance of activity we have a new instance of viewmodel
     *
     * case 2: @Singleton scope
     * instance1 Activity -> instance1 ViewModel
     * instance2 Activity -> instance1 ViewModel                                -> @Singleton scope annotation can handle this case
     * for each new instance of activity we have the same instance of ViewModel
     *
     * Case 3:
     * instance1 Activity -> instance1 ViewModel
     * instance2 Activity -> instance1 ViewModel
     * instance4 Activity -> instance1 ViewModel    -> the scope of the viewModel is different from the scope of the activity
     * instance4 Activity -> instance2 ViewModel       the scope of the component which has the dependency of the viewmodel as type is different from the scope of the type dependency
     * instance4 Activity -> instance3 ViewModel
     *
     * R => we should have subcomponent with its own graph dependencies and its own scope
     * to scope  a dependency to the lifecycle of its client we need a subcomponent and a new scope
     */
    @Inject lateinit var loginViewModel: LoginViewModel

    lateinit var loggingComponent:LoggingComponent

    private val viewModel: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "MainActivity hash: ${this.hashCode()}")
        Log.i(TAG, "onCreate: creating loggingComponent which  is a subcomponent of appComponent")
         loggingComponent = (this.application as MyApplication).appComponent.loginComponent().create()
        loggingComponent.inject(this)

        /**
         * LoginComponent is created in the activity's onCreate() method,
         * and it'll get implicitly destroyed when the activity gets destroyed.
         * The LoginComponent must always provide the same instance of LoginViewModel each time
         * it's requested.
         * You can ensure this by creating a custom annotation scope
         * and annotating both LoginComponent and LoginViewModel with it.
         * Note that you cannot use the @Singleton annotation because it's already been used
         * by the parent component and that'd make the object an application singleton (unique instance for the whole app).
         * You need to create a different annotation scope.
         */
        Log.i(TAG, loginViewModel.text)
        Log.i(TAG, viewModel.text)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    companion object{
        const val TAG = "LOG:MainActivityL"
    }
}