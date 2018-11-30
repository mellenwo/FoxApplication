package com.jobrapp.androidinterview.data

import com.jobrapp.androidinterview.androidmanagers.NetManager
import com.jobrapp.server.Server
import com.jobrapp.server.User
import io.reactivex.Observable
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import javax.inject.Inject

class UserRepository @Inject constructor(var netManager: NetManager){

    fun getUsers() : Observable<ArrayList<User>> {
        return Observable.create {
            subscriber ->
            if (netManager.isConnectedToInternet!!) {
                GlobalScope.async {
                    val networkResponse = withContext(Dispatchers.IO) {
                        Server(netManager.applicationContext).getDeferredUsers()
                    }.await()

                    if (networkResponse.isSuccessful) {

                        val users = networkResponse.body()?.map {
                            val item = it
                            User(it.name, it.profile_url)
                        } as ArrayList<User>
                        subscriber.onNext(users)
                        subscriber.onComplete()
                    } else {
                        subscriber.onError(Throwable(networkResponse.message()))
                    }
                }
            } else {
                subscriber.onError(Throwable("You are not connected to the internet."))
            }
        }
    }
}
