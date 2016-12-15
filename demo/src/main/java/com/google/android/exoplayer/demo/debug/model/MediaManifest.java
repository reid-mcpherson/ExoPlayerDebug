package com.google.android.exoplayer.demo.debug.model;

import com.google.android.exoplayer.hls.HlsMediaPlaylist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MediaManifest implements Serializable {

    public static final String ENCRYPTION_METHOD_NONE = "NONE";
    public static final String ENCRYPTION_METHOD_AES_128 = "AES-128";

    public final int type;
    public final String baseUri;
    public final int mediaSequence;
    public final int targetDurationSecs;
    public final int version;
    public final List<Segment> segments = new ArrayList<>();
    public final boolean live;
    public final long durationUs;

    public MediaManifest(HlsMediaPlaylist hlsMediaPlaylist) {
        this.type = hlsMediaPlaylist.type;
        this.baseUri = hlsMediaPlaylist.baseUri;
        this.mediaSequence = hlsMediaPlaylist.mediaSequence;
        this.targetDurationSecs = hlsMediaPlaylist.targetDurationSecs;
        this.version = hlsMediaPlaylist.version;
        this.live = hlsMediaPlaylist.live;
        this.durationUs = hlsMediaPlaylist.durationUs;
        for (HlsMediaPlaylist.Segment segment : hlsMediaPlaylist.segments) {
            segments.add(new Segment(segment));
        }
    }

    public static class Segment implements Serializable {
        public final String url;
        public final double durationSecs;
        public final int discontinuitySequenceNumber;
        public final long startTimeUs;
        public final boolean isEncrypted;
        public final String encryptionKeyUri;
        public final String encryptionIV;
        public final long byterangeOffset;
        public final long byterangeLength;

        public Segment(HlsMediaPlaylist.Segment segment) {
            url = segment.url;
            durationSecs = segment.durationSecs;
            discontinuitySequenceNumber = segment.discontinuitySequenceNumber;
            startTimeUs = segment.startTimeUs;
            isEncrypted = segment.isEncrypted;
            encryptionKeyUri = segment.encryptionKeyUri;
            encryptionIV = segment.encryptionIV;
            byterangeOffset = segment.byterangeOffset;
            byterangeLength = segment.byterangeLength;
        }
    }
}
