
# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__08_Storage_01_Non_Structured_Data__:<br>
> - Understanding the 4 types of non structured data storage; App specific, Media Storage, SharedPreferences.

Taken from android guides

#### Data storage overview 

Android uses a file system that's similar to disk-based file systems on other platforms. The system provides several options for you to save your app data.

- __App-specific storage__: Store files that are meant for your app's use only, either in dedicated directories within an internal storage volume or different dedicated directories within external storage. Use the directories within internal storage to save sensitive information that other apps shouldn't access.
- __Shared storage__: Store files that your app intends to share with other apps, including media, documents, and other files.
- __Preferences__: Store private, primitive data in key-value pairs.
- __Databases__: Store structured data in a private database using the __Room persistence library__.

#### Summary options

  
|                  | Type of content | Access method | Permissions needed | Can other apps access? | Files removed on app uninstall? | 
| :--------------  | :-------------- | :------------ | :----------------- | :--------------------- | :------------------------------ | 
| App-specific files|  Files meant for your app's use only | From internal storage, getFilesDir() or getCacheDir() From external storage, getExternalFilesDir() or getExternalCacheDir() | Never needed for internal storage Not needed for external storage when your app is used on devices that run Android 4.4 (API level 19) or higher | No | Yes |
| Media | Shareable media files (images, audio files, videos) | MediaStore API | READ_EXTERNAL_STORAGE when accessing other apps' files on Android 11 (API level 30) or higher READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE when accessing other apps' files on Android 10 (API level 29) Permissions are required for all files on Android 9 (API level 28) or lower | Yes, though the other app needs the READ_EXTERNAL_STORAGE permission | No |
| Documents and other files | Other types of shareable content, including downloaded files | Storage Access Framework | None | Yes, through the system file picker | No |
| App preferences | Key-value pairs | Jetpack Preferences library | None | No | Yes |
| Database | Structured data | Room persistence library | None | No | Yes |


#### How to choose

- __How much space does your data require?__<br>
Internal storage has limited space for app-specific data. Use other types of storage if you need to save a substantial amount of data.
- __How reliable does data access need to be?__<br>
If your app's basic functionality requires certain data, such as when your app is starting up, place the data within internal storage directory or a database. App-specific files that are stored in external storage aren't always accessible because some devices allow users to remove a physical device that corresponds to external storage.
- __What kind of data do you need to store?__<br>
If you have data that's only meaningful for your app, use app-specific storage. For shareable media content, use shared storage so that other apps can access the content. For structured data, use either preferences (for key-value data) or a database (for data that contains more than 2 columns).
- __Should the data be private to your app?__<br>
When storing sensitive data—data that shouldn't be accessible from any other app—use internal storage, preferences, or a database. Internal storage has the added benefit of the data being hidden from users.

