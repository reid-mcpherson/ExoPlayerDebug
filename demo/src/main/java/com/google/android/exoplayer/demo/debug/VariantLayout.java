package com.google.android.exoplayer.demo.debug;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.R;
import com.google.android.exoplayer.demo.debug.model.MediaManifest;
import com.google.android.exoplayer.demo.debug.view.VariantView;
import com.google.android.exoplayer.hls.HlsMediaPlaylist;

public class VariantLayout extends FrameLayout implements VariantView {

    @Bind(R.id.url) TextView url;
    @Bind(R.id.id) TextView id;
    @Bind(R.id.mimeType) TextView mimeType;
    @Bind(R.id.bitRate) TextView bitRate;
    @Bind(R.id.width) TextView width;
    @Bind(R.id.height) TextView height;
    @Bind(R.id.frameRate) TextView frameRate;
    @Bind(R.id.audioChannels) TextView audioChannels;
    @Bind(R.id.audioSamplingRate) TextView audioSamplingRate;
    @Bind(R.id.codecs) TextView codecs;
    @Bind(R.id.language) TextView language;
    @Bind(R.id.progress) ProgressBar progress;

    public VariantLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void displayUrl(String url) {
        this.url.setText(getResources().getString(R.string.variant_url, url));
    }

    @Override
    public void displayId(String id) {
        this.id.setText(getResources().getString(R.string.variant_id, id));
    }

    @Override
    public void displayMimeType(String mimeType) {
        this.mimeType.setText(getResources().getString(R.string.variant_mime_type, mimeType));
    }

    @Override
    public void displayBitRate(int bitrate) {
        this.bitRate.setText(getResources().getString(R.string.variant_bitrate, bitrate));
    }

    @Override
    public void displayWidth(int width) {
        this.width.setText(getResources().getString(R.string.variant_width, width));
    }

    @Override
    public void displayHeight(int height) {
        this.height.setText(getResources().getString(R.string.variant_height, height));
    }

    @Override
    public void displayFramerate(float frameRate) {
        this.frameRate.setText(getResources().getString(R.string.variant_frame_rate, frameRate));
    }

    @Override
    public void displayAudioChannels(int audioChannels) {
        this.audioChannels.setText(getResources().getString(R.string.variant_audio_channels, audioChannels));
    }

    @Override
    public void displayAudioSamplingRate(int samplingRate) {
        this.audioSamplingRate.setText(getResources().getString(R.string.variant_audio_sampling_rate, samplingRate));
    }

    @Override
    public void displayCodecs(String codecs) {
        this.codecs.setText(getResources().getString(R.string.variant_codecs, audioSamplingRate));
    }

    @Override
    public void displayLanguage(String language) {
        this.language.setText(getResources().getString(R.string.variant_language, language));
    }

    @Override
    public void displayError(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayMediaPlaylist(HlsMediaPlaylist hlsMediaPlaylist) {
        FragmentManager fragmentManager = ((Activity) getContext()).getFragmentManager();
        MediaManifestDialogFragment mediaManifestDialogFragment = new MediaManifestDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MediaManifestDialogFragment.MEDIA_MANIFEST, new MediaManifest(hlsMediaPlaylist));
        mediaManifestDialogFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, mediaManifestDialogFragment).addToBackStack(null).commit();
    }

    @Override
    public void setClickListener(final ClickListener clickListener) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onVariantClicked();
            }
        });
    }

    @Override
    public void displayLoading() {
        progress.setVisibility(VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(GONE);
    }
}
