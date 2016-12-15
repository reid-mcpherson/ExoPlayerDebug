package com.google.android.exoplayer.demo.debug.view;

public interface PlayerView {

    boolean backgroundAudioEnabled();

    void hideControls();

    void enableBackgroundAudio(boolean backgroundAudio);

    void preparePlayer(boolean playWhenReady);

    boolean needsPrepare();

    boolean haveTracks(int type);
}