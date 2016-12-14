package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.hls.HlsMasterPlaylist;

public interface VideoDebugView {

    void hideControls();

    void showControls();

    void displayMasterManifest(HlsMasterPlaylist hlsMasterPlaylist);

    void displayAudioPopUp();

    void displayVideoPopUp();

    void displayTextPopUp();

    void displayError(String text);

    void displayVerboseLogPopUp(MenuCallback menuCallback);

    interface MenuCallback {
        void onMenuItemClick(boolean enabled);
    }
}
