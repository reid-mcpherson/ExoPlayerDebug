package com.google.android.exoplayer.demo;

public interface VideoDebugView {

    void hideControls();

    void showControls();

    void displayMasterManifest();

    void displayAudioPopUp();

    void displayVideoPopUp();

    void displayTextPopUp();

    void displayVerboseLogPopUp(MenuCallback menuCallback);

    interface MenuCallback {
        void onMenuItemClick(boolean enabled);
    }
}
