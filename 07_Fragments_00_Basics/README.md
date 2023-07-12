# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__07_Fragments_00_Basics__:<br>
> - Fragments, Fragment LifeCycle, Fragment Manager

- Concepts, Classes,...
  - Fragment Related 
    - [__FragmentFactory__](https://developer.android.com/reference/androidx/fragment/app/FragmentFactory)
    - [__Fragment__](https://developer.android.com/guide/fragments)
      - behaviour: 
        - [requireArguments](https://developer.android.com/guide/fragments/create#add-programmatic)
          - getString,...
      - [__supportFragmentManager__](https://developer.android.com/guide/fragments/fragmentmanager)
        - behaviour: 
          - [popBackStack](https://developer.android.com/guide/fragments/transactions)
          - [commit](https://developer.android.com/guide/fragments/transactions)
          - setPrimaryNavigationFragment
          - findFragmentById
          - show
          - isHidden
          - isVisible
          - crud: add, replace, remove 
          - setReorderingAllowed
          - addToBackStack
      - [__parentFragmentManager__](https://developer.android.com/guide/fragments/fragmentmanager)
        - behaviour: 
          - ||
      - [__childFragmentManager__](https://developer.android.com/guide/fragments/fragmentmanager)
        - behaviour: 
          - ||
    - XML components 
      - __[FragmentContainerVie](https://developer.android.com/reference/androidx/fragment/app/FragmentContainerView)w__


- Kotlin/Java topics
  - __[ClassLoader](https://developer.android.com/reference/kotlin/java/lang/ClassLoader)__
  - __class.java__


#### Fragment Factory


If our Fragments have custom constructor It should be necessary to create a new __FragmentFactory__ -> Factory class in charge of instantiating Fragments using factory method __instantiate()__ and give it to the __fragmentManager__ e.g.: <br>

```
FragmentManager
     factoryClass -> FragmentFactory  -> Factory Method -> instantiate()
     customFactoryClass -> FragmentTopFactory -> Factory Method -> instantiate()
```



#### Fragment Overview

__Fragments__ are a reusable and modular part of the __UI__, one class can be instantiated many times so that a fragment can be positioned multiple times in an activity and positioned in multiple activities for this reason a fragment class should only care about its own UI behaviour and design.

  - __Fragment__ instance is set into a __FragmentContainerView__
    - An instance can be set either statically in xml  or programmatically
    - Statically: within the xml FragmentContainerView by using the attr app:name = "<classFragmentName>" so that android sdk is in charge of:
            1. create a new Fragment instance
            1. inflate the layout within the parent
            1. add the fragment as a transaction in the fragmentManager

    - Programatically: In kotlin or java by:
       1. create a new fragment instance
       1. set it withing a FragmentTransaction
       1. Commit the transaction  into The fragment manager ( adding it to the backstack if desired)


#### Fragment parent relationship

- A Fragment manager has a set of child Fragments __Fi__ operations can be performed on __Fi__ such as __OP = {add, remove, replace} then an OP operation (Fi)__

- A transaction is counted as a set of operations applied to __Fi__ so __Ti = {OP (Fi) 1, OP (Fi) 2, ..., OP (Fi) n}__

- You can have a record of operations in a FragmentManager with a __backStack__ that is basically a stack where it considers that the last transaction performed is the first to get rid of and the first will be the last to get rid of.

#### FragmentManager

__FragmentManager__ __FM__ has Object __TransactionManager__ to create __Ti's__ TransactionManager  object has attr's to perform __add__, __remove__, __replace__ operations.<br>

Operations require the id of the container, class name of the fragment  at least and if it's desired to send values to the fragment __bundles__ can be used __args bundle argument__



