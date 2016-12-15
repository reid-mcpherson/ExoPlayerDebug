package com.google.android.exoplayer.demo.debug.presenter;

import com.google.android.exoplayer.demo.debug.view.DebugControlsView;
import com.google.android.exoplayer.demo.debug.view.PlayerView;

public interface DebugControlsPresenter {

    void attachView(DebugControlsView debugControlsView, PlayerView playerView);

    void detachView();

    void toggleControls(boolean playerControlsVisible);

    void updateButtonVisibilities();
}
