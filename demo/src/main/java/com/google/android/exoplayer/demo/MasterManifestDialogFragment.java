package com.google.android.exoplayer.demo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.hls.HlsPlaylist;
import com.google.android.exoplayer.hls.Variant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MasterManifestDialogFragment extends DialogFragment {

    public static final String MASTER_MANIFEST = "masterManifest";

    @Bind(R.id.type) TextView type;
    @Bind(R.id.baseUri) TextView baseUri;
    @Bind(R.id.audioSubtitle) View audioSubtitle;
    @Bind(R.id.variantSubtitle) View variantSubtitle;
    @Bind(R.id.subtitleSubtitle) View subtitleSubtitle;
    @Bind(R.id.muxedAudioLanguage) TextView muxedAudioLanguage;
    @Bind(R.id.muxedCaptionLanguate) TextView muxedCaptionLanguage;
    @Bind(R.id.variantContainer) ViewGroup variantContainer;
    @Bind(R.id.audioContainer) ViewGroup audioContainer;
    @Bind(R.id.subtitleContainer) ViewGroup subtitleContainer;

    private SerializableHlsMasterPlaylist masterManifest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        masterManifest = (SerializableHlsMasterPlaylist) getArguments().getSerializable(MASTER_MANIFEST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_master_manifest, container, false);
        ButterKnife.bind(this, view);
        String typeText = getResources().getString(R.string.manifest_type, masterManifest.type == HlsPlaylist.TYPE_MASTER ? "MASTER" : "MEDIA");
        type.setText(typeText);
        baseUri.setText(getResources().getString(R.string.manifest_base_uri, masterManifest.baseUri));
        muxedAudioLanguage.setText(getResources().getString(R.string.manifest_muxed_audio_lang, masterManifest.muxedAudioLanguage));
        muxedCaptionLanguage.setText(getResources().getString(R.string.manifest_muxed_caption_lang, masterManifest.muxedCaptionLanguage));

        if (masterManifest.variants.isEmpty()) {
            variantSubtitle.setVisibility(GONE);
        } else {
            for (SerializableVariant variant : masterManifest.variants) {
                View divider = inflater.inflate(R.layout.divider, variantContainer, false);
                variantContainer.addView(divider);
                VariantView content = (VariantView) inflater.inflate(R.layout.view_variant, variantContainer, false);
                content.setUp(variant);
                variantContainer.addView(content);
            }
        }

        if (masterManifest.audios.isEmpty()) {
            audioSubtitle.setVisibility(GONE);
        } else {
            for (SerializableVariant variant : masterManifest.audios) {
                View divider = inflater.inflate(R.layout.divider, audioContainer, false);
                audioContainer.addView(divider);
                VariantView content = (VariantView) inflater.inflate(R.layout.view_variant, audioContainer, false);
                content.setUp(variant);
                audioContainer.addView(content);
            }
        }

        if (masterManifest.subtitles.isEmpty()) {
            subtitleSubtitle.setVisibility(GONE);
        } else {
            for (SerializableVariant variant : masterManifest.subtitles) {
                View divider = inflater.inflate(R.layout.divider, subtitleContainer, false);
                subtitleContainer.addView(divider);
                VariantView content = (VariantView) inflater.inflate(R.layout.view_variant, subtitleContainer, false);
                content.setUp(variant);
                subtitleContainer.addView(content);
            }
        }

        return view;
    }


    public static class SerializableHlsMasterPlaylist implements Serializable {
        public final List<SerializableVariant> variants = new ArrayList<>();
        public final List<SerializableVariant> audios = new ArrayList<>();
        public final List<SerializableVariant> subtitles = new ArrayList<>();

        public final String muxedAudioLanguage;
        public final String muxedCaptionLanguage;

        public final int type;
        public final String baseUri;

        public SerializableHlsMasterPlaylist(HlsMasterPlaylist hlsMasterPlaylist) {
            for (Variant variant : hlsMasterPlaylist.variants) {
                variants.add(new SerializableVariant(variant));
            }

            for (Variant variant : hlsMasterPlaylist.audios) {
                audios.add(new SerializableVariant(variant));
            }

            for (Variant variant : hlsMasterPlaylist.subtitles) {
                subtitles.add(new SerializableVariant(variant));
            }

            this.muxedAudioLanguage = hlsMasterPlaylist.muxedAudioLanguage;
            this.muxedCaptionLanguage = hlsMasterPlaylist.muxedCaptionLanguage;
            this.type = hlsMasterPlaylist.type;
            this.baseUri = hlsMasterPlaylist.baseUri;
        }
    }

    public static class SerializableVariant implements Serializable {
        public final String url;
        public final SerializableFormat format;

        public SerializableVariant(Variant variant) {
            this.url = variant.url;
            this.format = new SerializableFormat(variant.format);
        }

    }

    public static class SerializableFormat implements Serializable {
        public final String id;
        public final String mimeType;
        public final int bitrate;
        public final int width;
        public final int height;
        public final float frameRate;
        public final int audioChannels;
        public final int audioSamplingRate;
        public final String codecs;
        public final String language;

        public SerializableFormat(Format format) {
            this.id = format.id;
            this.mimeType = format.mimeType;
            this.bitrate = format.bitrate;
            this.width = format.width;
            this.height = format.height;
            this.frameRate = format.frameRate;
            this.audioChannels = format.audioChannels;
            this.audioSamplingRate = format.audioSamplingRate;
            this.codecs = format.codecs;
            this.language = format.language;
        }
    }
}
