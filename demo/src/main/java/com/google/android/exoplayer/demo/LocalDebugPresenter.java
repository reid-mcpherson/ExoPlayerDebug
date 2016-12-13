package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.demo.util.NullObject;
import com.google.android.exoplayer.util.VerboseLogUtil;

public class LocalDebugPresenter implements VideoDebugPresenter {

    private static final VideoDebugView NULL_VIEW = NullObject.create(VideoDebugView.class);

    private VideoDebugView view = NULL_VIEW;

    @Override
    public void attachView(VideoDebugView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = NULL_VIEW;
    }

    @Override
    public void present() {
        //no-op
    }

    @Override
    public void onRootViewTouched(boolean controlsVisible) {
        if (controlsVisible) {
            view.hideControls();
        } else {
            view.showControls();
        }
    }

    @Override
    public void onVerboseLoggingButtonClicked() {
        view.displayVerboseLogPopUp(new VideoDebugView.MenuCallback() {
            @Override
            public void onMenuItemClick(boolean enabled) {
                VerboseLogUtil.setEnableAllTags(enabled);
            }
        });
    }

    @Override
    public void onMasterManifestButtonClicked() {
        view.displayMasterManifest();
    }

    @Override
    public void onAudioButtonClicked() {
        view.displayAudioPopUp();
    }

    @Override
    public void onTextButtonClicked() {
        view.displayTextPopUp();
    }

    @Override
    public void onVideoButtonClicked() {
        view.displayVideoPopUp();
    }
}
