package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.demo.util.NullObject;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.VerboseLogUtil;

import java.io.IOException;

public class LocalDebugPresenter implements VideoDebugPresenter, ManifestFetcher.ManifestCallback<HlsPlaylist> {

    private static final VideoDebugView NULL_VIEW = NullObject.create(VideoDebugView.class);

    private VideoDebugView view = NULL_VIEW;

    private HlsMasterPlaylist manifest;

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
        view.setManifestCallback(this);
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
        if (manifest == null) {
            view.displayError("Manifest is null");
        } else {
            view.hideControls();
            view.displayMasterManifest(manifest);
        }
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

    @Override
    public void onSingleManifest(HlsPlaylist manifest) {
        if (manifest instanceof HlsMasterPlaylist) {
            this.manifest = (HlsMasterPlaylist) manifest;
        }
    }

    @Override
    public void onSingleManifestError(IOException e) {
        //no-op
    }
}
