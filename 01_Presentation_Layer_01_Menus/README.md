# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.


__01_Presentation_Layer_01_Menus__:<br>
> - Understanding the different types of menus: App bar, contextual menu, contextualmode, pop menu
  
- Concepts, Classes,...
  - Android Framework Classes
      - ViewModel
      - Log
      - FragmentManager
      - MutableLiveData
        - behaviour: observeForever

  - Views
    - behaviour: setOnLongClickListener

  - RecyclerView
    - Behavior: 
      - attrs: layoutManager
      - tools attrs: listitem
    - Adapter (oop Design pattern)
      - RecyclerView.Adapter<Class extends ViewHolderAlarm>
        - onCreateViewHolder
        - onBindViewHolder
        - getItemCount
        - notifyDataSetChanged
      - behaviour: createAdapter
    - RecyclerView.ViewHolder
  - Dialogs related
    - Dialog
      - behaviour: 
        - onCreateDialog
      - AlertDialog
        - setItems
        - attrs: title, message, set<feeeling>Button
        - AlertDialog.Builder
          - setPositiveButton
          - setNegativeButton
      - DatePickerDialog
      - TimePickerDialog
      - DialogInterface
        - behaviour: DialogInterface.OnClickListener
    - DialogFragment
      - onAttach
      - onCreateDialog 
      - onDismiss
      - onCancel

  - Menus
    - toolbar
      - InflateMenu
      - clear
      - setOnMenuItemClickListener
      setOnMenuItemClickListener
    - menu
      - item
        - attrs: icon, showAsAction, title, checked, visible
      - behaviourL: findItem
      - PopupMenu
        - behaviour:
          - setOnMenuItemClickListener
      - Activity menu related
        - behaviour: requireActivity
        - menus 
          - onCreateOptionsMenu
          - onOptionsItemSelected
          - invalidateOptionsMenu
      - Fragment menu related
        - menus 
          - setHasOptionsMenu
          - registerForContextMenu
          - onOptionsItemSelected
          - onCreateContextMenu
          - onContextItemSelected
          - onPrepareOptionsMenu
      - ActionMode
        - behaviour
          - ActionMode.Callback
            - onCreateActionMode
            - onPrepareActionMode
            - onActionItemClicked
            - onDestroyActionMode

- Kotlin topics
  - inner class
  - internal var 
  - init block 
  - this@<Class>
  - lambda functions 
  - when closure 
  - companion object
  - const val
  - data class
  - lateinit var

- Object Oriented Design
  - Adapter Pattern
  - Observer Pattern
  - Builder design pattern
  

