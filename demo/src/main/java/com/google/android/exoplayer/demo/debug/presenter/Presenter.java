package com.google.android.exoplayer.demo.debug.presenter;

public interface Presenter<V> {
    void attachView(V v);

    void detachView();

    void pauseView();

    void resumeView();

    void present();
}