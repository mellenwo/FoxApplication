package com.jobrapp.androidinterview

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jobrapp.androidinterview.data.UserRepository
import com.jobrapp.androidinterview.extensions.plusAssign
import com.jobrapp.server.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserViewModel (private var userRepository: UserRepository) : ViewModel() {

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)
    var users = MutableLiveData <ArrayList<User>>()

    private val compositeDisposable = CompositeDisposable()

    fun loadUsers() {
        isLoading.set(true)
        compositeDisposable +=
                userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<User>>(){
                    override fun onComplete() {
                        isLoading.set(false)
                    }

                    override fun onNext(data: ArrayList<User>) {
                        users.value = data
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }
}