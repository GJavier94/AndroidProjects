# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.


__01_Presentation_Layer_01_Menus__:<br>
> - Understanding the different types of menus: App bar, contextual menu, contextualmode, pop menu
  
- Concepts, Classes,...
  - Android Framework Classes
      - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
      - [Log](https://developer.android.com/reference/android/util/Log)
      - [FragmentManager](https://developer.android.com/reference/androidx/fragment/app/FragmentManager.html)
      - [MutableLiveData](https://developer.android.com/reference/androidx/lifecycle/MutableLiveData)
        - behaviour: observeForever

  - [Views](https://developer.android.com/reference/android/view/View)
    - behaviour: setOnLongClickListener

  - [RecyclerView](https://developer.android.com/develop/ui/views/layout/recyclerview)
    - Behavior: 
      - attrs: layoutManager
      - tools attrs: listitem
    - [Adapter](https://www.geeksforgeeks.org/adapter-pattern/) (oop Design pattern)
      - [RecyclerView.Adapter<Class extends ViewHolderAlarm>](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)
        - onCreateViewHolder
        - onBindViewHolder
        - getItemCount
        - notifyDataSetChanged
      - behaviour: createAdapter
    - [RecyclerView.ViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)
  - Dialogs related
    - [Dialog](https://developer.android.com/develop/ui/views/components/dialogs)
      - behaviour: 
        - onCreateDialog
      - [AlertDialog](https://developer.android.com/reference/android/app/AlertDialog)
        - setItems
        - attrs: title, message, set<feeeling>Button
        - AlertDialog.Builder
          - setPositiveButton
          - setNegativeButton
      - [DatePickerDialog](https://developer.android.com/reference/android/app/DatePickerDialog)
      - [TimePickerDialog](https://developer.android.com/reference/android/app/TimePickerDialog)
      - [DialogInterface](https://developer.android.com/reference/android/content/DialogInterface)
        - behaviour: DialogInterface.OnClickListener
    - [DialogFragment](https://developer.android.com/reference/android/app/DialogFragment)
      - onAttach
      - onCreateDialog 
      - onDismiss
      - onCancel

  - [Menus](https://developer.android.com/develop/ui/views/components/menus)
    - [toolbar](https://developer.android.com/reference/android/widget/Toolbar)
      - InflateMenu
      - clear
      - setOnMenuItemClickListener
      setOnMenuItemClickListener
    - menu
      - item
        - attrs: icon, showAsAction, title, checked, visible
      - behaviourL: findItem
      - [PopupMenu](https://developer.android.com/reference/android/widget/PopupMenu)
        - behaviour:
          - setOnMenuItemClickListener
      - Activity menu related
        - behaviour: requireActivity
        - [menus](https://developer.android.com/develop/ui/views/components/menus) 
          - onCreateOptionsMenu
          - onOptionsItemSelected
          - invalidateOptionsMenu
      - [Fragment menu related](https://developer.android.com/develop/ui/views/components/menus)
        - menus 
          - setHasOptionsMenu
          - registerForContextMenu
          - onOptionsItemSelected
          - onCreateContextMenu
          - onContextItemSelected
          - onPrepareOptionsMenu
      - [ActionMode](https://developer.android.com/develop/ui/views/components/menus#CAB)
        - behaviour
          - [ActionMode.Callback](https://developer.android.com/reference/android/view/ActionMode.Callback)
            - onCreateActionMode
            - onPrepareActionMode
            - onActionItemClicked
            - onDestroyActionMode

- Kotlin topics
  - [inner class](https://kotlinlang.org/docs/nested-classes.html)
  - [internal var ](https://kotlinlang.org/docs/visibility-modifiers.html#class-members)
  - [init block ](https://kotlinlang.org/docs/classes.html)
  - [this@<Class>](https://kotlinlang.org/docs/this-expressions.html)
  - [lambda functions ](https://kotlinlang.org/docs/lambdas.html)
  - [when](https://kotlinlang.org/docs/control-flow.html) closure 
  - [companion object](https://kotlinlang.org/docs/object-declarations.html)
  - [const val](https://kotlinlang.org/docs/properties.html)
  - [data class](https://kotlinlang.org/docs/data-classes.html)
  - [lateinit var](https://kotlinlang.org/docs/properties.html)

- Object Oriented Design
  - [Adapter Pattern](https://www.geeksforgeeks.org/adapter-pattern/)
  - [Observer Pattern](https://www.geeksforgeeks.org/observer-pattern-set-1-introduction/)https://www.geeksforgeeks.org/observer-pattern-set-1-introduction/
  - [Builder design pattern](https://www.geeksforgeeks.org/builder-design-pattern/)
