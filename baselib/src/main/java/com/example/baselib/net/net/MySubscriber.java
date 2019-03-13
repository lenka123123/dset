package com.example.baselib.net.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import rx.Subscriber;

public abstract class MySubscriber<T> extends Subscriber<T> {
    private Context context;

    public MySubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("tag", "MySubscriber.onStart()");
        if (!NetworkUtil.isConn(context)) {
            Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();

            if (!isUnsubscribed()) {
                unsubscribe();
            }
            return;
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("tag", "MySubscriber.throwable =" + e.toString());
        Log.e("tag", "MySubscriber.throwable =" + e.getMessage());
        if (e instanceof Exception) {

            onError(ExceptionHandle.handleException(e));
        } else {

            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable responeThrowable);

    @Override
    public void onCompleted() {
        Log.i("tag", "MySubscriber.onComplete()");
    }
}





















