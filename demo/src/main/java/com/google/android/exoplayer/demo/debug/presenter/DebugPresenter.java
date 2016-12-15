package com.google.android.exoplayer.demo.debug.presenter;

import com.google.android.exoplayer.demo.debug.view.DebugView;
import com.google.android.exoplayer.demo.debug.view.PlayerView;

public interface DebugPresenter {

    void attachView(DebugView debugView, PlayerView playerView);

    void detachView();

    void toggleControls(boolean playerControlsVisible);

    void updateButtonVisibilities();
}
