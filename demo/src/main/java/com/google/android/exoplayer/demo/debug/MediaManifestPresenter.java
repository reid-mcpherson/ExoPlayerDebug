package com.google.android.exoplayer.demo.debug;

import com.google.android.exoplayer.demo.debug.model.MediaManifest;
import com.google.android.exoplayer.demo.debug.presenter.Presenter;
import com.google.android.exoplayer.demo.debug.util.NullObject;
import com.google.android.exoplayer.demo.debug.view.MediaManifestView;

public class MediaManifestPresenter implements Presenter<MediaManifestView> {

    private static final MediaManifestView NULL_VIEW = NullObject.create(MediaManifestView.class);

    private MediaManifestView view = NULL_VIEW;

    private final MediaManifest mediaManifest;

    public MediaManifestPresenter(MediaManifest mediaManifest) {
        this.mediaManifest = mediaManifest;
    }

    @Override
    public void attachView(MediaManifestView mediaManifestView) {
        this.view = mediaManifestView;
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
        view.displayType(mediaManifest.type);
        view.displayBaseUri(mediaManifest.baseUri);
        view.displayMediaSequence(mediaManifest.mediaSequence);
        view.displayTargetDurationSecs(mediaManifest.targetDurationSecs);
        view.displayVersion(mediaManifest.version);
        view.displayLive(mediaManifest.live);
        view.displayDurationUs(mediaManifest.durationUs);
        if (mediaManifest.segments.isEmpty()) {
            view.hideSegmentTitle();
        } else {
            view.displaySegments(mediaManifest.segments);
        }
    }
}
