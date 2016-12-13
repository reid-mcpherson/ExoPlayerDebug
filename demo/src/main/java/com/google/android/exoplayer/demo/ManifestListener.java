package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.hls.HlsMasterPlaylist;

public interface ManifestListener {
    void onManifest(HlsMasterPlaylist manifest);
}