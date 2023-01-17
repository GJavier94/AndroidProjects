# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__05_Multithreading__:<br>
> - Using Handler Looper for Threading communication
  
- Concepts, Classes,...
  - Multithreading
    - Thread 
      - currentThread
    - HandlerThread
      - run
    - Handler
      - handleMessage
      - obtainMessage
      - sendMessage

#### Behaviour overview and Objective

1. Here we create two threads so that each thread runs with the __UIThread__ as a parent
1. Each thread will have one __handler__
1. The __handler__ defines a method which will handle the message in the other thread
1. The __handler__ will be responsible for getting the message from the __queue__ and executing them
