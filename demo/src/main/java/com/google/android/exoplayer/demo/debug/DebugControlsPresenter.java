package com.google.android.exoplayer.demo.debug;

public interface DebugControlsPresenter {

    void attachView(DebugControlsView debugControlsView, PlayerView playerView);

    void detachView();

    void present();

    void toggleControls(boolean playerControlsVisible);

    void onStateChanged(boolean playWhenReady, int playbackState);

    void updateButtonVisibilities();
}
