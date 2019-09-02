package com.example.thesocialmedia.api.observable

import android.util.Log
import com.example.thesocialmedia.model.Posts
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

object ObservableExample {

    private val TAG = "Observable"

    fun observableExample(posts: ArrayList<Posts>) {
        val postsObservable = Observable
            .fromIterable(posts)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        postsObservable.subscribe(object : Observer<Posts> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: subscribed")
            }

            override fun onNext(posts: Posts) {
                Log.d(TAG, "onNext: " + posts.title)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete: true")
            }
        })
    }
}
