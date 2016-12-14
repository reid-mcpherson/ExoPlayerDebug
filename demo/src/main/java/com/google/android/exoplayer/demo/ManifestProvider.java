package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.util.ManifestFetcher;

import javax.inject.Inject;
import java.io.IOException;

public class ManifestProvider implements ManifestFetcher.ManifestCallback<HlsPlaylist> {

    private HlsMasterPlaylist masterPlaylist;
    private HlsMediaPlaylist mediaPlaylist;

    @Inject
    public ManifestProvider() {}


    public HlsMasterPlaylist getMasterPlaylist() {
        return masterPlaylist;
    }

    public HlsMediaPlaylist getMediaPlaylist() {
        return mediaPlaylist;
    }

    @Override
    public void onSingleManifest(HlsPlaylist manifest) {
        if (manifest instanceof HlsMasterPlaylist) {
            this.masterPlaylist = (HlsMasterPlaylist) manifest;
        } else if (manifest instanceof HlsMediaPlaylist) {
            this.mediaPlaylist = (HlsMediaPlaylist) manifest;
        }
    }

    @Override
    public void onSingleManifestError(IOException e) {
        //no-op (Already being notified by HlsRendererBuilder)
    }
}
