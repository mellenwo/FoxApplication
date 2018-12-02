# FoxApplication

Scope: 
App makes a call to the mock server to a retrieve an infinite list of foxes. I added swipe-up to refresh functionality using this third party library: https://github.com/omadahealth/SwipyRefreshLayout. I also replaced the refresh button I had at the bottom, with a refresh icon in the action bar.  

Implementation:
The project has been implemented following the MVVM architectural design pattern. I used RxJava and LiveData to implement the Observable/Subscriber design pattern to make async calls to the server and update the UI accordingly. 

Further TO-DO: 
- Persistent data solution: Right now I configured the retrofit app instance to cache the responses. Based on the scope of the app going forward, I would look at a SQLite implementation of data storage using a library like Room. 
- Better error handling: Currently I am logging the error messages provided by the appropriate Throwable. Ideally, I would like to display some sort of custom implementation of AlertDialogue. 
