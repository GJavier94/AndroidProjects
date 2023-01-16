# Android Projects
### The purpose of this section is to learn and implement in practice the next topics.

__01_Presentation_Layer_02_Databinding__:<br>
> - Data binding library; how to replace findViewById by using this library
  
- Concepts, Classes,...
  - Android Framework 
      - DataBinding
        - DataBindingUtil
        
        - DSL: Domain Specific language (Language expression) 
          - operators: 
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

        - One way Databiding 
          - > `
                checkbox ->
                android:checked="@{viewModelMainActivity.rememberUser}"
                android:onCheckedChanged="@{(view,value) -> viewModelMainActivity.rememberChanged(view,value)}"
              `

        - Two way Databinding
          - > `
                android:checked="@={viewModelMainActivity.rememberUser}
              `
          - Custom Converters
            - direct method, inverse method
            - Annotation: @JvmStatic

      - Activity related: 
        - lifecycleOwner


  - java classes 
    - Calendar
    - DateFormat
    - Date

    
