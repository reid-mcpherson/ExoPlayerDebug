package com.google.android.exoplayer.demo;

public interface VideoDebugPresenter {

    void attachView(VideoDebugView view);

    void detachView();

    void present();

    void onRootViewTouched(boolean controlsVisible);

    void onVerboseLoggingButtonClicked();

    void onMasterManifestButtonClicked();

    void onAudioButtonClicked();

    void onVideoButtonClicked();

    void onTextButtonClicked();
}
