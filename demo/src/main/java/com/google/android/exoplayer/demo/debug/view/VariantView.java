package com.google.android.exoplayer.demo.debug.view;

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
}
