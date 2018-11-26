package com.jobrapp.androidinterview.data

import com.jobrapp.androidinterview.androidmanagers.NetManager
import com.jobrapp.server.Server
import com.jobrapp.server.User
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(var netManager: NetManager){


    fun getUsers() : Observable<ArrayList<User>> {
        return Observable.create {
            subscriber ->
            if (netManager.isConnectedToInternet!!) {
                val callResponse = Server(netManager.applicationContext).getUsers()
                val response = callResponse.execute()

                if (response.isSuccessful) {
                    val users = response.body()!!.map {
                        val item = it
                        User(it.name, it.profile_url)
                    } as ArrayList<User>
                    subscriber.onNext(users)
                    subscriber.onComplete()
                } else {
                    subscriber.onError(Throwable(response.message()))
                }
            } else {
                subscriber.onError(Throwable("You are not connected to the internet."))
            }
        }
    }
}
