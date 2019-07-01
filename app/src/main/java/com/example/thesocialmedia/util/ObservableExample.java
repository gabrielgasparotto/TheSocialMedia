package com.example.thesocialmedia.util;

import android.util.Log;
import com.example.thesocialmedia.model.Posts;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class ObservableExample {

    private static String TAG = "Observable";

    public static void observableExample(ArrayList<Posts> posts){
        Observable<Posts> postsObservable =  Observable
                .fromIterable(posts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        postsObservable.subscribe(new Observer<Posts>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: subscribed");
            }

            @Override
            public void onNext(Posts posts) {
                Log.d(TAG, "onNext: "+posts.getTitle());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: true");
            }
        });
    }
}
