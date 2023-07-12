 # Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__04_Entry_Point_Broadcast_Receiver__:<br>
> - Broadcast Receiver entry point

  
- Concepts, Classes,...

  - Android Broadcast receiver related
    - Android OS messages 
      - __Action__ 
        - [__ACTION_HEADSET_PLUG__](https://developer.android.com/reference/android/content/Intent#ACTION_HEADSET_PLUG)
    - [__BroadcastReceiver__](https://developer.android.com/guide/components/broadcasts) 
      - onReceive
    - __[LocalBroadcastManager](https://developer.android.com/reference/androidx/localbroadcastmanager/content/LocalBroadcastManager)__
      - [registerReceiver](https://developer.android.com/reference/androidx/localbroadcastmanager/content/LocalBroadcastManager#registerReceiver(android.content.BroadcastReceiver,android.content.IntentFilter))
      - [sendBroadcast](https://developer.android.com/reference/androidx/localbroadcastmanager/content/LocalBroadcastManager#sendBroadcast(android.content.Intent))
      - [unregisterReceiver](https://developer.android.com/reference/androidx/localbroadcastmanager/content/LocalBroadcastManager#unregisterReceiver(android.content.BroadcastReceiver))
    - Activity 
      - behaviour:
        - registerReceiver
        - unregisterReceiver
        - sendOrderedBroadcast
        - sendBroadcast
      - __[IntentFilter](https://developer.android.com/reference/android/content/IntentFilter)__



- Kotlin/Java topics
  - __[StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/)__
    - append
  - repeat


- [Broadcast receiver](https://developer.android.com/guide/components/broadcasts)
  - Types 
    - Declaration way
      - Manifest declared
      - Programatically declared
    - Scope 
      - local Broadcast 
      - external component broadcast 
        - Permissions 
          - Sender permissions 
          - Receiver permissions

