package com.google.android.exoplayer.demo.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.R;

public class VariantLayout extends LinearLayout implements com.google.android.exoplayer.demo.debug.view.VariantView {

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
}
