package com.google.android.exoplayer.demo.debug;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.demo.player.DemoPlayer;
import com.google.android.exoplayer.util.DebugTextViewHelper;

public interface VideoPlayer extends DebugTextViewHelper.Provider {
    void setSelectedTrack(int type, int index);

    int getTrackCount(int type);

    MediaFormat getTrackFormat(int type, int index);

    int getSelectedTrack(int type);

    void addListener(DemoPlayer.Listener listener);

    void removeListener(DemoPlayer.Listener listener);
}
