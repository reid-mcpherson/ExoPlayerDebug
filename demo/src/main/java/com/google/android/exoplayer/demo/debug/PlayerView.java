package com.google.android.exoplayer.demo.debug;

public interface PlayerView {

    boolean backgroundAudioEnabled();

    void enableBackgroundAudio(boolean backgroundAudio);

    void preparePlayer(boolean playWhenReady);

    boolean needsPrepare();

    boolean haveTracks(int type);
}