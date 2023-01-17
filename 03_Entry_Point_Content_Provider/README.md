# Android Projects
### The purpose of this section is to learn and to put in practice the next topics.


__03_Entry_Point_Content_Provider__:<br>
> - Content Provider entry point and how to consume it.

- Concepts, Classes,...

  - Android Content Provider related
    - Cursor
    - SimpleCursorAdapter
      - Constructor: context, layout, cursor, array from, array to
      - swapCursor
    - android.provider
      - Telephony
      - ContactsContract
      -  ContactsContract.CommonDataKinds
    - ContentResolver
      - insert
      - update
      - query
    - ContentValues
      - put
    - Uri
    - ContentUris
      - parseId

  - Android Permissions 
    - context 
      - behaviour: 
        - checkSelfPermission
        - requestPermissions
        - onRequestPermissionsResult
          - args: requestCode, permissions, grantResults
    - PackageManager
      - attrs: PERMISSION_DENIED
  - ViewModel 
    - onCleared

  
- Kotlin/Java topics
  
  - intArrayOf
  - by lazy


### CRUD operations on Content Provider 

#### Create


In order to insert a value into the content provider  we just need two things:
1. A __ContentValue__ object in which case each column value of the row is registered through the method __put(Key(column), value )__
1. Call the insert method of the contentResolver specifying the __URI__ (table route) and the content value object -> the new record
__Return value__: Returns the URI of the new record -> content://user_dictionary/words/<id_value>, to get It we call __ContentUri.parseId(URI)__ otherwise is null if  couldn't be inserted or crashes.

Some content providers provide methods to insert new element without using insert() e.g addMessage.

Here we have an example: 

``` 
  val contentValues = ContentValues()
  
  //we insert a new contact without any values
  var uriResult:Uri? = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, contentValues)

  // Parse de Id to a Long type
  val iDContact:Long = ContentUris.parseId(uriResult!!)

  // id to select row
  contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, iDContact)
  // mime type and value for column insertion
  contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
  contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, this.attrDisplayName)

  // Insertion
  uriResult  = contentResolver.insert(ContactsContract.Data.CONTENT_URI, contentValues)
  Log.i(TAG, "insertion with values uriResult: $uriResult")


```


#### Read 
1. Get __permission__: 
  - Obtain permission from the manifest to use the content provider 
```
<uses-permission android:name = "android.permission.READ_USER_DICTIONARY"/>

```
  - Obtain the permission at runtime
  ```
   Log.i(TAG, "Checking permissions required")
        if( !vm.permissionToReadContentProvider || this.applicationContext.checkSelfPermission(PERMISSION_READ_SMS)== PackageManager.PERMISSION_DENIED ){
            Log.i(TAG, "One Permission is required")
            Log.i(TAG, "Requiring the permission $PERMISSION_READ_SMS")
            // Require the permission one more time
            requestPermissions(arrayOf(PERMISSION_READ_SMS), PERMISSION_REQUEST_CODE)
        }
  ```
1. Use __ContentResolver__: In order to establish communication to the contentProvider we need to call its __query__ method
 via the __query__ method from contentResolver -> ContentResolver is an API to manage binding between source data and other app entry


The query methods needs:
```
  cursor = contentResolver.query(
  UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
  projection,                        // The columns to return for each row --> arrayOfStrings(col1,coli)
  selectionClause,                   // Selection criteria      String for where clause
  selectionArgs.toTypedArray(),      // Selection criteria      array with arguments to replace in selectionsClause to avoid SQLInjection
  sortOrder                          // The sort order for the returned rows --> order of the rows
)
```
     
#### Update

Just a few steps very similar to SQL Delete operation shown in this piece of code

```
    internal var permissionToWriteContacts: Boolean = false
    internal var attrDisplayUpdateName:String = ""
    internal var attrNameNew: String = ""

  

    //instead of a projection we need to write a ContentValues object with the new values we want to set

    


    fun update(contentResolver: ContentResolver) {

        //1 Create the record
        val updateValues = ContentValues().apply{
            put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, this@ViewModelUpdateActivity.attrNameNew )
        }

        // 2 prepare the selection... is the where clause in SQL
        var selectionClause:String? = "${ ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME} = ?"
        var selectionArgs:Array<String> = arrayOf("")

        // 3 Call update method
        val numRows = contentResolver.update(
            ContactsContract.Data.CONTENT_URI,   // the user dictionary content URI
            updateValues,                      // the columns to update
            selectionClause,                   // the column to select on
            selectionArgs                      // the value to compare to
        )

    }
```

#### Delete

This is very similar to update but we are not using ContentValues object because we will not insert anything
```
    // 1 prepare the selection... is the where clause in SQL
    internal var selectionClause:String? = "${ ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME} = ?"
    internal var selectionArgs:Array<String> = arrayOf("")



    var attrDisplayName: String = ""
    var permissionToWriteContacts: Boolean  = false

    fun delete(contentResolver: ContentResolver?) {
        val numRows = contentResolver?.delete(
            ContactsContract.Data.CONTENT_URI,
            selectionClause,
            selectionArgs
        )
    }
```

