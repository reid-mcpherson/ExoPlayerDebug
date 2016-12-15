package com.google.android.exoplayer.demo.debug;

import com.google.android.exoplayer.demo.debug.model.MasterManifest;
import com.google.android.exoplayer.demo.debug.presenter.Presenter;
import com.google.android.exoplayer.demo.debug.util.NullObject;
import com.google.android.exoplayer.demo.debug.view.VariantView;

public class VariantPresenter implements Presenter<VariantView> {
    private static final VariantView NULL_VIEW = NullObject.create(VariantView.class);

    private final MasterManifest.Variant variant;

    private VariantView view = NULL_VIEW;

    public VariantPresenter(MasterManifest.Variant variant) {
        this.variant = variant;
    }

    @Override
    public void attachView(VariantView variantView) {
        this.view = variantView;
    }

    @Override
    public void detachView() {
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
}
