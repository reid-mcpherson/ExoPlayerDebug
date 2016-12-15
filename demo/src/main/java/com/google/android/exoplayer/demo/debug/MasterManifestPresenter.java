package com.google.android.exoplayer.demo.debug;

import com.google.android.exoplayer.demo.debug.model.MasterManifest;
import com.google.android.exoplayer.demo.debug.presenter.Presenter;
import com.google.android.exoplayer.demo.debug.view.MasterManifestView;
import com.google.android.exoplayer.demo.debug.util.NullObject;

public class MasterManifestPresenter implements Presenter<MasterManifestView> {

    private final MasterManifest masterManifest;

    public MasterManifestPresenter(MasterManifest masterManifest) {
        this.masterManifest = masterManifest;
    }

    private static final MasterManifestView NULL_VIEW = NullObject.create(MasterManifestView.class);
    private MasterManifestView view = NULL_VIEW;

    @Override
    public void attachView(MasterManifestView masterManifestView) {
        this.view = masterManifestView;
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
        view.displayType(masterManifest.type);
        view.displayBaseUri(masterManifest.baseUri);
        view.displayMuxedAudioLanguage(masterManifest.muxedAudioLanguage);
        view.displayMuxedCaptionLanguage(masterManifest.muxedCaptionLanguage);

        if (masterManifest.variants.isEmpty()) {
            view.hideVariantsSubtitle();
        } else {
            view.displayVariants(masterManifest.variants);
        }

        if (masterManifest.subtitles.isEmpty()) {
            view.hideSubtitlesSubtitle();
        } else {
            view.displaySubtitleVariants(masterManifest.subtitles);
        }

        if (masterManifest.audios.isEmpty()) {
            view.hideAudioSubtitle();
        } else {
            view.displayAudioVariants(masterManifest.audios);
        }
    }
}
