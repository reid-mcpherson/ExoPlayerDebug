package com.google.android.exoplayer.demo.debug.view;

import com.google.android.exoplayer.hls.HlsMediaPlaylist;

public interface VariantView {

    void displayUrl(String url);

    void displayId(String id);

    void displayMimeType(String mimeType);

    void displayBitRate(int bitRate);

    void displayWidth(int width);

    void displayHeight(int height);

    void displayFramerate(float frameRate);

    void displayAudioChannels(int audioChannels);

    void displayAudioSamplingRate(int samplingRate);

    void displayCodecs(String codecs);

    void displayLanguage(String language);

    void setClickListener(ClickListener clickListener);

    void displayLoading();

    void hideLoading();

    void displayError(String text);

    void displayMediaPlaylist(HlsMediaPlaylist hlsMediaPlaylist);

    interface ClickListener {
        void onVariantClicked();
    }
}
