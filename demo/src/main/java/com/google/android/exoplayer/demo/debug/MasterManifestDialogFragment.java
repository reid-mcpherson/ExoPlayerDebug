package com.google.android.exoplayer.demo.debug;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.Demo;
import com.google.android.exoplayer.demo.R;
import com.google.android.exoplayer.demo.debug.model.MasterManifest;
import com.google.android.exoplayer.demo.debug.util.ManifestProvider;
import com.google.android.exoplayer.demo.debug.view.MasterManifestView;
import com.google.android.exoplayer.hls.HlsPlaylist;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MasterManifestDialogFragment extends DialogFragment implements MasterManifestView {

    public static final String MASTER_MANIFEST = "masterManifest";

    @Inject ManifestProvider manifestProvider;

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

    private final List<VariantPresenter> variantPresenters = new ArrayList<>();
    private MasterManifestPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Demo) getActivity().getApplication()).getObjectGraph().inject(this);
        presenter = new MasterManifestPresenter((MasterManifest) getArguments().getSerializable(MASTER_MANIFEST));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_master_manifest, container, false);
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
        for (VariantPresenter presenter : variantPresenters) {
            presenter.detachView();
        }
        variantPresenters.clear();
        variantContainer.removeAllViews();
        subtitleContainer.removeAllViews();
        audioContainer.removeAllViews();

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
    public void displayMuxedAudioLanguage(String muxedAudioLanguage) {
        this.muxedAudioLanguage.setText(getResources().getString(R.string.manifest_muxed_audio_lang, muxedAudioLanguage));
    }

    @Override
    public void displayMuxedCaptionLanguage(String muxedCaptionLanguage) {
        this.muxedCaptionLanguage.setText(getResources().getString(R.string.manifest_muxed_caption_lang, muxedCaptionLanguage));
    }

    @Override
    public void hideVariantsSubtitle() {
        variantSubtitle.setVisibility(GONE);
    }

    @Override
    public void hideSubtitlesSubtitle() {
        subtitleSubtitle.setVisibility(GONE);
    }

    @Override
    public void hideAudioSubtitle() {
        audioSubtitle.setVisibility(GONE);
    }

    @Override
    public void displayVariants(List<MasterManifest.Variant> variants) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        for (MasterManifest.Variant variant : variants) {
            View divider = inflater.inflate(R.layout.divider, variantContainer, false);
            variantContainer.addView(divider);
            VariantLayout content = (VariantLayout) inflater.inflate(R.layout.view_variant, variantContainer, false);
            VariantPresenter presenter = new VariantPresenter(variant, manifestProvider);
            presenter.attachView(content);
            presenter.present();
            variantPresenters.add(presenter);
            variantContainer.addView(content);
        }
    }

    @Override
    public void displaySubtitleVariants(List<MasterManifest.Variant> variants) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        for (MasterManifest.Variant variant : variants) {
            View divider = inflater.inflate(R.layout.divider, subtitleContainer, false);
            subtitleContainer.addView(divider);
            VariantLayout content = (VariantLayout) inflater.inflate(R.layout.view_variant, subtitleContainer, false);
            VariantPresenter presenter = new VariantPresenter(variant, manifestProvider);
            presenter.attachView(content);
            presenter.present();
            variantPresenters.add(presenter);
            subtitleContainer.addView(content);
        }
    }

    @Override
    public void displayAudioVariants(List<MasterManifest.Variant> variants) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        for (MasterManifest.Variant variant : variants) {
            View divider = inflater.inflate(R.layout.divider, audioContainer, false);
            audioContainer.addView(divider);
            VariantLayout content = (VariantLayout) inflater.inflate(R.layout.view_variant, audioContainer, false);
            VariantPresenter presenter = new VariantPresenter(variant, manifestProvider);
            presenter.attachView(content);
            presenter.present();
            variantPresenters.add(presenter);
            audioContainer.addView(content);
        }
    }
}
