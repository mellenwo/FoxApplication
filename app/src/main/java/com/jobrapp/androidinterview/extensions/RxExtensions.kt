package com.jobrapp.androidinterview.extensions

import android.text.TextUtils
import android.widget.ImageView
import com.jobrapp.androidinterview.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.Exception

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun ImageView.loadImg(imageUrl: String) {


    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.get().load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.get()
                .load(imageUrl)
                .centerCrop()
                .fit()
                .into(this, object : Callback {
                    override fun onError(e: Exception?) {
                        if (e != null) {
                            e.printStackTrace()
                        }
                    }

                    override fun onSuccess() {
                    }
                })
    }
}