package com.google.android.exoplayer.demo.debug;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.R;
import com.google.android.exoplayer.demo.SegmentLayout;
import com.google.android.exoplayer.demo.SegmentPresenter;
import com.google.android.exoplayer.demo.debug.model.MediaManifest;
import com.google.android.exoplayer.demo.debug.view.MediaManifestView;
import com.google.android.exoplayer.hls.HlsPlaylist;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MediaManifestDialogFragment extends DialogFragment implements MediaManifestView {

    public static final String MEDIA_MANIFEST = "mediaManifest";

    @Bind(R.id.type) TextView type;
    @Bind(R.id.baseUri) TextView baseUri;
    @Bind(R.id.mediaSequence) TextView mediaSequence;
    @Bind(R.id.targetDurationSecs) TextView targetDurationSecs;
    @Bind(R.id.version) TextView version;
    @Bind(R.id.live) TextView live;
    @Bind(R.id.durationUs) TextView durationUs;
    @Bind(R.id.segmentTitle) TextView segmentTitle;
    @Bind(R.id.segmentContainer) ViewGroup segmentContainer;

    private MediaManifestPresenter presenter;
    private List<SegmentPresenter> segmentPresenters = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MediaManifestPresenter((MediaManifest) getArguments().getSerializable(MEDIA_MANIFEST));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_media_manifest, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.present();
    }

    @Override
    public void onPause() {
        presenter.detachView();
        for (SegmentPresenter presenter : segmentPresenters) {
            presenter.detachView();
        }
        segmentPresenters.clear();
        segmentContainer.removeAllViews();
        super.onPause();
    }

    @Override
    public void displayType(int type) {
        String typeText = getResources().getString(R.string.manifest_type, type == HlsPlaylist.TYPE_MASTER ? "MASTER" : "MEDIA");
        this.type.setText(typeText);
    }

    @Override
    public void displayBaseUri(String baseUri) {
        this.baseUri.setText(getResources().getString(R.string.manifest_base_uri, baseUri));
    }

    @Override
    public void displayMediaSequence(int mediaSequence) {
        this.mediaSequence.setText(getResources().getString(R.string.manifest_media_sequence, mediaSequence));
    }

    @Override
    public void displayTargetDurationSecs(int targetDurationSecs) {
        this.targetDurationSecs.setText(getResources().getString(R.string.manifest_target_duration, targetDurationSecs));
    }

    @Override
    public void displayVersion(int version) {
        this.version.setText(getResources().getString(R.string.manifest_version, version));
    }

    @Override
    public void displayLive(boolean live) {
        this.live.setText(getResources().getString(R.string.manifest_live, live));
    }

    @Override
    public void displayDurationUs(long durationUs) {
        this.durationUs.setText(getResources().getString(R.string.manifest_duration, durationUs));
    }

    @Override
    public void hideSegmentTitle() {
        segmentTitle.setVisibility(GONE);
    }

    @Override
    public void displaySegments(List<MediaManifest.Segment> segments) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        for (MediaManifest.Segment segment : segments) {
            View divider = inflater.inflate(R.layout.divider, segmentContainer, false);
            segmentContainer.addView(divider);
            SegmentLayout content = (SegmentLayout) inflater.inflate(R.layout.view_segment, segmentContainer, false);
            SegmentPresenter presenter = new SegmentPresenter(segment);
            presenter.attachView(content);
            presenter.present();
            segmentPresenters.add(presenter);
            segmentContainer.addView(content);
        }
    }
}
