package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.demo.debug.model.MediaManifest;
import com.google.android.exoplayer.demo.debug.presenter.Presenter;
import com.google.android.exoplayer.demo.debug.util.NullObject;
import com.google.android.exoplayer.demo.debug.view.SegmentView;

public class SegmentPresenter implements Presenter<SegmentView> {

    private static final SegmentView NULL_VIEW = NullObject.create(SegmentView.class);

    private SegmentView view = NULL_VIEW;

    private final MediaManifest.Segment segment;

    public SegmentPresenter(MediaManifest.Segment segment) {
        this.segment = segment;
    }

    @Override
    public void attachView(SegmentView segmentView) {
        this.view = segmentView;
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
        view.displayUrl(segment.url);
        view.displayDurationSecs(segment.durationSecs);
        view.displayDiscontinuitySequenceNumber(segment.discontinuitySequenceNumber);
        view.displayStartTime(segment.startTimeUs);
        view.displayEncrypted(segment.isEncrypted);
        view.displayEncryptionKeyUri(segment.encryptionKeyUri);
        view.displayEncryptionIV(segment.encryptionIV);
        view.displayByterangeOffset(segment.byterangeOffset);
        view.displayByterangeLength(segment.byterangeLength);
    }
}
