# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.

__01_Presentation_Layer_02_Databinding__:<br>
> - Data binding library; how to replace findViewById by using this library
  
- Concepts, Classes,...
  - Android Framework 
      - [DataBinding](https://developer.android.com/topic/libraries/data-binding)
        - [DataBindingUtil](https://developer.android.com/topic/libraries/data-binding/expressions)
        
        - DSL: Domain Specific language (Language expression) 
          - [operators](https://developer.android.com/topic/libraries/data-binding/expressions#expression_language): 
            - mathematical + - / *
            - string concatenation +
            - logical && ||
            - binary & | ^
            - unary + - ! ~
            - shift >> << >>>
            - comparison == , >=, <=, and &lt (lower than ) you know the problme with < it is also used for opening a tag- that's why
            - grouping()
            - literals -> 'a', "Hello", 4, null
            - cast -> (class)object
            - method calls -> object.method()
            - field -> access object.field1
            - array access -> []
            - ternary operator-> ?

        - [One way Databiding](https://developer.android.com/topic/libraries/data-binding/architecture)
          - > `
                checkbox ->
                android:checked="@{viewModelMainActivity.rememberUser}"
                android:onCheckedChanged="@{(view,value) -> viewModelMainActivity.rememberChanged(view,value)}"
              `

        - [Two way Databinding](https://developer.android.com/topic/libraries/data-binding/two-way)
          - > `
                android:checked="@={viewModelMainActivity.rememberUser}
              `
          - [Custom Converters](https://developer.android.com/topic/libraries/data-binding/binding-adapters)
            - direct method, inverse method
            - Annotation: @JvmStatic

      - Activity related: 
        - [lifecycleOwner](https://developer.android.com/reference/androidx/lifecycle/LifecycleOwner)


  - java classes 
    - [Calendar](https://developer.android.com/reference/android/icu/util/Calendar)
    - [DateFormat](https://developer.android.com/reference/android/icu/text/DateFormat)
    - [Date](https://developer.android.com/reference/java/sql/Date)

    
