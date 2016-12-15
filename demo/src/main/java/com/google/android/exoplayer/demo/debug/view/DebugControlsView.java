package com.google.android.exoplayer.demo.debug.view;

import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.util.DebugTextViewHelper;

import java.util.List;

public interface DebugControlsView {

    void hideControls();

    void showControls();

    void setOnClickListener(OnClickListener onClickListener);

    void displayPlayerStateText(String text);

    void displayRetryButton();

    void displayVideoButton();

    void displayAudioButton();

    void displayTextButton();

    void hideRetryButton();

    void hideVideoButton();

    void hideAudioButton();

    void hideTextButton();

    void displayManifestButton(String text, boolean enabled);

    void displayError(String text);

    void displayMasterManifest(HlsMasterPlaylist hlsMasterPlaylist);

    void displayAudioPopUp(boolean enableBackgroundAudio,
                           PopupMenuClickListener popupMenuClickListener,
                           int menuGroup,
                           int offset,
                           int trackCount,
                           List<String> trackNames,
                           int selectedTrack);

    void displayVideoPopup(PopupMenuClickListener popupMenuClickListener,
                           int menuGroup,
                           int offset,
                           int trackCount,
                           List<String> trackNames,
                           int selectedTrack);

    void displayTextPopup(PopupMenuClickListener popupMenuClickListener,
                          int menuGroup,
                          int offset,
                          int trackCount,
                          List<String> trackNames,
                          int selectedTrack);

    void displayVerboseLogPopUp(PopupMenuClickListener popupMenuClickListener);

    void prepareDebugTextViewHelper(DebugTextViewHelper.Provider provider);

    void releaseDebugTextViewHelper();

    interface OnClickListener {
        void onVideoButtonClicked();

        void onAudioButtonClicked();

        void onTextButtonClicked();

        void onManifestButtonClicked();

        void onVerboseLogControlsClicked();

        void onRetryButtonClicked();
    }

    interface PopupMenuClickListener {
        boolean onMenuItemClick(int groupId, int itemId, boolean enabled, boolean checked);
    }
}
