package com.google.android.exoplayer.demo.debug;

import com.google.android.exoplayer.demo.debug.model.MasterManifest;
import com.google.android.exoplayer.demo.debug.presenter.Presenter;
import com.google.android.exoplayer.demo.debug.util.ManifestProvider;
import com.google.android.exoplayer.demo.debug.util.NullObject;
import com.google.android.exoplayer.demo.debug.view.VariantView;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;

import java.io.IOException;

public class VariantPresenter implements Presenter<VariantView>, VariantView.ClickListener {
    private static final VariantView NULL_VIEW = NullObject.create(VariantView.class);

    private final MasterManifest.Variant variant;
    private final ManifestProvider manifestProvider;

    private VariantView view = NULL_VIEW;

    public VariantPresenter(MasterManifest.Variant variant, ManifestProvider manifestProvider) {
        this.variant = variant;
        this.manifestProvider = manifestProvider;
    }

    @Override
    public void attachView(VariantView variantView) {
        this.view = variantView;
        view.setClickListener(this);
    }

    @Override
    public void detachView() {
        view.setClickListener(null);
        this.view = NULL_VIEW;
    }

    @Override
    public void pauseView() {
        //no-op
    }

    @Override
    public void resumeView() {
        //no-op
    }

    @Override
    public void present() {
        view.displayUrl(variant.url);
        MasterManifest.Variant.Format format = variant.format;
        view.displayId(format.id);
        view.displayMimeType(format.mimeType);
        view.displayBitRate(format.bitrate);
        view.displayWidth(format.width);
        view.displayHeight(format.height);
        view.displayFramerate(format.frameRate);
        view.displayAudioChannels(format.audioChannels);
        view.displayAudioSamplingRate(format.audioSamplingRate);
        view.displayCodecs(format.codecs);
        view.displayLanguage(format.language);
    }

    @Override
    public void onVariantClicked() {
        view.displayLoading();
        view.setClickListener(null);
        manifestProvider.fetchManifest(variant.url, new ManifestProvider.ManifestListener() {
            @Override
            public void onMasterManifest(HlsMasterPlaylist hlsMasterPlaylist) {
                view.hideLoading();
                view.setClickListener(VariantPresenter.this);
                view.displayError("Incorrect manifest returned");
            }

            @Override
            public void onMediaPlaylist(HlsMediaPlaylist hlsMediaPlaylist) {
                view.setClickListener(VariantPresenter.this);
                view.displayMediaPlaylist(hlsMediaPlaylist);
                view.hideLoading();
            }

            @Override
            public void onError(IOException e) {
                view.hideLoading();
                view.setClickListener(VariantPresenter.this);
                view.displayError("Error retrieving media manifest");
            }
        });
    }
}
