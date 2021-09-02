package com.example.fragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /***
         * If our Fragments have custom constructor it should be necessary to create a new
         * FragmentFactory -> Factory class in charge of instantiating Fragments using factoryMethod instantiate()
         * and give it to the fragmentManager
         * e.g.
         * FragmentManager
         *      factoryClass -> FragmentFactory  -> Factory Method -> instantiate()
         *      customFactoryClass ->FragmentTopFactory -> Factory Method -> instantiate()
         *
         */


        this.supportFragmentManager.fragmentFactory = MyFragmentFactory(DataFragmentTop("Javier","Armenta",18))



        //Fragments are a reusable and modular part of the UI
        //one class can be instantiated many times so that a fragment can be positioned
        //multiple times in an activity and positioned in multiple activities
        //for that reason a fragment class should only care about its own UI behaviour and design
        /**
         *  Fragment instance is set into a FragmentContainerView
         *  An instance can be set either statically in xml  or programmatically
         *  1.-Statically
         *      within the xml FragmentContainerView by using the attr app:name = "<classFragmentName>"
         *      so that android sdk is in charge of
         *          1.- create a new Fragment instance
         *          2.- inflate the layout within the parent
         *          3.- add the fragment as a transaction in the fragmentManager
         *
         *  2.-Programatically
         *         in kotlin or java by:
         *         1.- create a new fragment instance
         *         2.- set it withing a FragmentTransaction
         *         3.- Commit the transaction  into The fragment manager ( adding it to the backstack if desired)
         *
         *
         */
         // in this case FragmentTop is 1) and FragmentBottom is 2)


        supportFragmentManager.commit {
            add(R.id.fragmentContainerViewBottom, FragmentBottom::class.java, bundleOf("key" to "hola"), "FragmentParent" )
            add<FragmentTop>(R.id.fragmentContainerViewTop )
            setReorderingAllowed(true)

        }


        /**
         * a Fragment manager has a set of child Fragments Fi
         * operations can be performed on Fi such as OP = {add, remove, replace}
         * then an OP operation (Fi)
         *
         * A transaction is counted as a set of operations applied to Fi
         * so Ti = {OP (Fi) 1, OP (Fi) 2, ..., OP (Fi) n}
         *
         * you can have a record of operations in a FragmentManager with
         * a backStack that is basically a stack where it considers that the last transaction performed
         * is the first to get rid and the first will be the last to get rid
         *
         * BackStack
         * {t3, t2, t1, t0}
         *
         * FragmentManager FM has Object TransactionManager to create Ti's
         * TransactionManager  object has attr's to perform add, remove, replace operaciones
         * operations require the id of the container, nameClass of the fragment  at least
         * if it's desired to send values to the fragment bundles can be used args bundle argument
         *
         */


        //let's apply a Transaction


        var swapDone = false
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener{
            var bottomContainer =  R.id.fragmentContainerViewBottom
            var topContainer = R.id.fragmentContainerViewTop

            if(!swapDone){
                bottomContainer =  R.id.fragmentContainerViewTop
                topContainer = R.id.fragmentContainerViewBottom
            }

            this.supportFragmentManager.commit {
                replace<FragmentBottom>(bottomContainer, args = bundleOf("key" to "remplazando"))
                setReorderingAllowed(true)// this is for animations and transitions
                replace<FragmentTop>(topContainer)
                addToBackStack(null)
            }
            swapDone = !swapDone
        }

        /**
         * by using the methods  with the sign  <nameMethod>'<'<nameClass>'>'
         * we are telling the FragmentManager to use the FragmentFactory class to instantiate the object fragments
         *
         * what if we wanted to have a Fragment with a custom Constructor
         * FragmentFactory wouldn't know how to instantiate it
         * ->Define the new constructor into the Fragment subclass
         * -> extend FragmentFactory and override its factory method
         */


    }
}



