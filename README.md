# FoxApplication

Scope: 
App makes a Retrofit call to display Fox images in a recyclerview. 

Implementation:
The project has been implemented following the MVVM architectural design pattern. I used RxJava and LiveData to implement the Observable/Subscriber design pattern to make async calls to the server and update the UI accordingly. 

Further TO-DO: 
- Persistent data solution: Right now I configured the retrofit app instance to cache the responses. Going forward, and based on additional features that will be built, I would look at a SQLite implementation of data storage using a library like Room. 
- Better error handling: right now I am logging stacktraces to the error Throwable objects. Ideally would like to display some sort of custom implementation of AlertDialogue. 
