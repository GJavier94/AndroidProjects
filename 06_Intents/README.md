# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__06_Intents__:<br>
> - Understanding Intents Implicit, Explicit
  
- Concepts, Classes,...
  - __Android OS__
    - [__Environment__](https://developer.android.com/reference/android/os/Environment)
      - global variables: __DIRECTORY_PICTURES__
  - __Android core__ 
    - __[FileProvider](https://developer.android.com/reference/androidx/core/content/FileProvider)__
      - getUriForFile
  - __[Androidx Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)__ 
    - __[ViewModelProvider](https://developer.android.com/reference/androidx/lifecycle/ViewModelProvider)__
  - __[Android.provider](https://developer.android.com/reference/android/provider/package-summary)__
    - [__MediaStore__](https://developer.android.com/reference/android/provider/MediaStore)
      - vars: 
        - __ACTION_IMAGE_CAPTURE__
        - __ACTION_VIDEO_CAPTURE__
  - __Activity__ 
    - behaviour: 
      - [startActivityForResult](https://developer.android.com/training/basics/intents/result)
      - [onActivityResult](https://developer.android.com/training/basics/intents/result#launch)
  - __Intent__
    - action: MediaStore.__ACTION_IMAGE_CAPTURE__
  - context 
    - [getExternalFilesDir](https://developer.android.com/reference/android/content/Context#getExternalFilesDir(java.lang.String))
  - __[MediaScannerConnection](https://developer.android.com/reference/android/media/MediaScannerConnection)__
    - scanFile
    - __[OnScanCompletedListener](https://developer.android.com/reference/android/media/MediaScannerConnection.OnScanCompletedListener)__
  - [Common Intents](https://developer.android.com/guide/components/intents-common)

- Kotlin/Java topics
  - __[File](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/java.io.-file/)__
    - [createTempFile](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/create-temp-file.html)
    - absolutePath
  - __[Locale](https://developer.android.com/reference/java/util/Locale)
  - [__SimpleDateFormat__](https://developer.android.com/reference/android/icu/text/SimpleDateFormat)
