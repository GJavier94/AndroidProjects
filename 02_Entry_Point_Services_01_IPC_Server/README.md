# Android Projects
### This repository was developed to grasp the fundamentals of Android Development

__02_Entry_Point_Services_01_IPC_Server__:<br>
> - Messenger API to  make IPC
  
### Service overview

A service is an entry point which __does not__ have User Interface
It's of general purpose, it is made to execute work whatever the app is (foreground or background) the service will continue be running
It can be created by any other app component.

#### Services types

There are two types of services
1. __Started services__: they are started by a component and will continue be running until it stops itself or another component.
    - __background__: Those services which run without the awareness of the user: Example downloading sth.
    - __foreground__: Those services which run with the awareness of the user: Example playing a song in a stream music app.
1. __Bind Services__: Services which the app component is bound to, It will stop if no other component is bound to the app.
1. __Mixture__: as long as the service implements the methods of the 1) and 2) it can behave as both.

#### Services vs Threads
A service run's on __UI Thread__. If the amount of work is heavy another thread within the service should be created
A Thread can runs independently from the main thread but its lifecycle is bound to an activity or component whereas a service runs completely independent.


Started service can manage tasks in a queued way (serially and in order).

##### Description:
A basic Thread java object once started and when executing method run(), it executes the code there and once
it finishes it is stopped and destroyed.

In order to execute **n** taks, **n** threads need to be created impacting the program's speed.
It is be better to reuse  **h** < **n** threads in such a way we can improve the performances. <br>
  1. The thread needs to keep running -> using a looper
  1. the thread should be reusable since we want to execute several tasks -> looper and messageQueue object
  1. many Threads can send messages of execution so that the same thread executes the task serially

Summary to achieve the above desired behaviour:
1. A __looper__  which has a __messageQueue__.
1. A __handler__ which  enqueue the messages in to the message queue and also provides methods to handle those messages.
1. One thread only have one Looper object which is stored in local storage.
1. There's only one looper per thread and also one MessageQueue per thread.
1. A thread can have many handlers.
1. A handler enqueue tasks by using __sendMessage()__.
1. __stopSelfWithResult(startId)__ finishes a services if the most recent called with startId is __finished()__.
1. __HandlerThread__ is provided by android and it has robust implementation.




