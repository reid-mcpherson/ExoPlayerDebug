package com.google.android.exoplayer.demo.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.R;

public class VariantView extends LinearLayout {

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

    public VariantView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setUp(MasterManifestDialogFragment.SerializableVariant variant) {
        url.setText(getResources().getString(R.string.variant_url, variant.url));
        MasterManifestDialogFragment.SerializableFormat format = variant.format;
        id.setText(getResources().getString(R.string.variant_id, format.id));
        mimeType.setText(getResources().getString(R.string.variant_mime_type, format.mimeType));
        bitRate.setText(getResources().getString(R.string.variant_bitrate, format.bitrate));
        width.setText(getResources().getString(R.string.variant_width, format.width));
        height.setText(getResources().getString(R.string.variant_height, format.height));
        frameRate.setText(getResources().getString(R.string.variant_frame_rate, format.frameRate));
        audioChannels.setText(getResources().getString(R.string.variant_audio_channels, format.audioChannels));
        audioSamplingRate.setText(getResources().getString(R.string.variant_audio_sampling_rate, format.audioSamplingRate));
        codecs.setText(getResources().getString(R.string.variant_audio_sampling_rate, format.audioSamplingRate));
        language.setText(getResources().getString(R.string.variant_language, format.language));

    }
}
