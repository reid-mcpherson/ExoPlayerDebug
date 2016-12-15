package com.google.android.exoplayer.demo.debug.view;

import com.google.android.exoplayer.demo.debug.model.MediaManifest;

import java.util.List;

public interface MediaManifestView {

    void displayType(int type);

    void displayBaseUri(String baseUri);

    void displayMediaSequence(int mediaSequence);

    void displayTargetDurationSecs(int targetDurationSecs);

    void displayVersion(int version);

    void displayLive(boolean live);

    void displayDurationUs(long durationUs);

    void hideSegmentTitle();

    void displaySegments(List<MediaManifest.Segment> segments);
}
