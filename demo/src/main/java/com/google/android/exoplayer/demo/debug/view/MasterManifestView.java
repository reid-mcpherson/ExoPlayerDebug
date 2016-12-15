package com.google.android.exoplayer.demo.debug.view;

import com.google.android.exoplayer.demo.debug.model.MasterManifest;

import java.util.List;

public interface MasterManifestView {

    void displayType(int type);

    void displayBaseUri(String baseUri);

    void displayMuxedAudioLanguage(String muxedAudioLanguage);

    void displayMuxedCaptionLanguage(String muxedCaptionLanguage);

    void hideVariantsSubtitle();

    void hideSubtitlesSubtitle();

    void hideAudioSubtitle();

    void displayVariants(List<MasterManifest.Variant> variants);

    void displaySubtitleVariants(List<MasterManifest.Variant> variants);

    void displayAudioVariants(List<MasterManifest.Variant> variants);
}
