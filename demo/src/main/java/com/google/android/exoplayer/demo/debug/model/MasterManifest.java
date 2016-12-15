package com.google.android.exoplayer.demo.debug.model;

import com.google.android.exoplayer.hls.HlsMasterPlaylist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MasterManifest implements Serializable {
    public final List<Variant> variants = new ArrayList<>();
    public final List<Variant> audios = new ArrayList<>();
    public final List<Variant> subtitles = new ArrayList<>();

    public final String muxedAudioLanguage;
    public final String muxedCaptionLanguage;

    public final int type;
    public final String baseUri;

    public MasterManifest(HlsMasterPlaylist hlsMasterPlaylist) {
        for (com.google.android.exoplayer.hls.Variant variant : hlsMasterPlaylist.variants) {
            variants.add(new Variant(variant));
        }

        for (com.google.android.exoplayer.hls.Variant variant : hlsMasterPlaylist.audios) {
            audios.add(new Variant(variant));
        }

        for (com.google.android.exoplayer.hls.Variant variant : hlsMasterPlaylist.subtitles) {
            subtitles.add(new Variant(variant));
        }

        this.muxedAudioLanguage = hlsMasterPlaylist.muxedAudioLanguage;
        this.muxedCaptionLanguage = hlsMasterPlaylist.muxedCaptionLanguage;
        this.type = hlsMasterPlaylist.type;
        this.baseUri = hlsMasterPlaylist.baseUri;
    }

    public static class Variant implements Serializable {
        public final String url;
        public final Format format;

        public Variant(com.google.android.exoplayer.hls.Variant variant) {
            this.url = variant.url;
            this.format = new Format(variant.format);
        }

        public static class Format implements Serializable {
            public final String id;
            public final String mimeType;
            public final int bitrate;
            public final int width;
            public final int height;
            public final float frameRate;
            public final int audioChannels;
            public final int audioSamplingRate;
            public final String codecs;
            public final String language;

            public Format(com.google.android.exoplayer.chunk.Format format) {
                this.id = format.id;
                this.mimeType = format.mimeType;
                this.bitrate = format.bitrate;
                this.width = format.width;
                this.height = format.height;
                this.frameRate = format.frameRate;
                this.audioChannels = format.audioChannels;
                this.audioSamplingRate = format.audioSamplingRate;
                this.codecs = format.codecs;
                this.language = format.language;
            }
        }

    }
}


