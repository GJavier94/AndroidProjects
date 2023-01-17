# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.

__01_Presentation_Layer_03_CustomViews__:<br>
> - Custom Views and how to create them
  


 ### Creating a Custom View
 
 All of the view class inherit from View  ( even ViewGroups )
 you can create a custom view by inheriting from View or some of its subclasses

 So you can reuse the already Specific View like EditText e.g.:


 View Class needs two parameters for its construction ( the context, the set of attributes [which are in the xml] )

 - How can we define new customizable attributes and also define its possible values ?

   - By using a static resource (e.g. res/values/attrs.xml) -> This is a new Namespace
   - Then the sdk android looks for the declare-styleable classes
   - you can declare a namespace with custom name <customNameNamespace> by using the xmlns tag and as a value res-auto or the fully qualified package name
   - you can call the attrs by the syntax <customNameNamespace>:attr = "value"
   - then  the resource bundle pass the xml attrs to the java/kotlin class withing an object AttributSet
   - there is a method which can help to parse the attrs ->     context.theme.obtainStyledAttributes
   - by the returned type of this method --->  you can call getBoolean,getInteger and so on to get the attrs in the customView class
   - R.java  returns a typedArray and also a set of id's for each attr i.e.
      - TypedArray: <br>
         ```
         typedArray(
              attri:<dataType>
                ...:....
              attrN:<dataType>
            )
         ```
      - AttributeSet: <br>
        `set(attriId:Integer,..., attrNId:Integer)`
      - Project reference: <br>
        | Syntax      | Description |
        | ----------- | ----------- |
        | `<declare-styleable name =  <name> >`      | `R.styleable.<name>`       |
- Concepts, Classes,...
  - Android Framework 
    - Gestures
      - OnGestureListener
        - onDown
        - onShowPress
        - onSingleTapUp
        - onScroll
        - onLongPress
        - onFling
      - GestureDetector


  
