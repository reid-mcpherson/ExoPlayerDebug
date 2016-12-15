package com.google.android.exoplayer.demo.debug;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer.demo.debug.util.UiExecutor;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylistParser;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.ManifestFetcher;
import com.google.android.exoplayer.util.Util;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.Executor;

public class ManifestProvider {

    private final Context context;
    private final Handler handler;
    private final Executor executor;
    private final UiExecutor uiExecutor;
    private final HlsPlaylistParser parser = new HlsPlaylistParser();
    private final String userAgent;

    @Inject
    public ManifestProvider(Context context, Executor executor, UiExecutor uiExecutor, Handler handler) {
        this.executor = executor;
        this.uiExecutor = uiExecutor;
        this.context = context;
        this.userAgent = Util.getUserAgent(context, "ExoPlayerDemo");
        this.handler = handler;
    }

    public void fetchManifest(final String url, final ManifestListener manifestListener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ManifestFetcher<HlsPlaylist> playlistFetcher = new ManifestFetcher<>(url, new DefaultUriDataSource(context, userAgent), parser);
                playlistFetcher.singleLoad(handler.getLooper(), new ManifestFetcher.ManifestCallback<HlsPlaylist>() {
                    @Override
                    public void onSingleManifest(final HlsPlaylist manifest) {
                        uiExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (manifest instanceof HlsMasterPlaylist) {
                                    manifestListener.onMasterManifest((HlsMasterPlaylist) manifest);
                                } else if (manifest instanceof HlsMediaPlaylist) {
                                    manifestListener.onMediaPlaylist((HlsMediaPlaylist) manifest);
                                } else {
                                    manifestListener.onError(new IOException("Manifest returned was not a Master manifest"));
                                }
                            }
                        });

                    }

                    @Override
                    public void onSingleManifestError(final IOException e) {
                        uiExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                manifestListener.onError(e);
                            }
                        });
                    }
                });
            }
        });
    }

    public interface ManifestListener {
        void onMasterManifest(HlsMasterPlaylist hlsMasterPlaylist);

        void onMediaPlaylist(HlsMediaPlaylist hlsMediaPlaylist);

        void onError(IOException e);
    }
}
