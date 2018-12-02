package com.jobrapp.androidinterview.data

import com.jobrapp.androidinterview.androidmanagers.NetManager
import com.jobrapp.server.Server
import com.jobrapp.server.User
import com.jobrapp.server.UserList
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(var netManager: NetManager){

    private var userList: UserList? = null


    fun getUsers() : Observable<ArrayList<User>> {
        return Observable.create {
            subscriber ->
            if (netManager.isConnectedToInternet!!) {
                val response = Server(netManager.applicationContext).getInfiniteList()
                    val users = response.map {
                        User(it.name, it.profile_url)
                    } as ArrayList<User>
                    subscriber.onNext(users)
                    subscriber.onComplete()
            } else {
                subscriber.onError(Throwable("You are not connected to the internet."))
            }
        }
    }
}
