package com.google.android.exoplayer.demo.debug;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.exoplayer.demo.R;
import com.google.android.exoplayer.demo.debug.model.MasterManifest;
import com.google.android.exoplayer.demo.debug.view.DebugView;
import com.google.android.exoplayer.demo.player.DemoPlayer;
import com.google.android.exoplayer.hls.HlsMasterPlaylist;
import com.google.android.exoplayer.util.DebugTextViewHelper;
import com.google.android.exoplayer.util.VerboseLogUtil;

import java.util.List;

public class VideoDebugView extends LinearLayout implements DebugView {

    @Bind(R.id.player_state_view) TextView playerStateView;
    @Bind(R.id.debug_text_view) TextView debugTextView;
    @Bind(R.id.controls_root) ViewGroup controlsRoot;
    @Bind(R.id.video_controls) Button videoButton;
    @Bind(R.id.audio_controls) Button audioButton;
    @Bind(R.id.text_controls) Button textButton;
    @Bind(R.id.master_manifest) Button masterManifestButton;
    @Bind(R.id.verbose_log_controls) Button verboseLogControls;
    @Bind(R.id.retry_button) Button retryButton;

    private DebugView.OnClickListener onClickListener;

    private DebugTextViewHelper debugViewHelper;

    public VideoDebugView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.video_controls, R.id.audio_controls, R.id.text_controls, R.id.master_manifest, R.id.verbose_log_controls, R.id.retry_button})
    public void onClick(View view) {
        if (onClickListener != null) {
            switch (view.getId()) {
                case R.id.video_controls:
                    onClickListener.onVideoButtonClicked();
                    break;
                case R.id.audio_controls:
                    onClickListener.onAudioButtonClicked();
                    break;
                case R.id.text_controls:
                    onClickListener.onTextButtonClicked();
                    break;
                case R.id.master_manifest:
                    onClickListener.onManifestButtonClicked();
                    break;
                case R.id.verbose_log_controls:
                    onClickListener.onVerboseLogControlsClicked();
                    break;
                case R.id.retry_button:
                    onClickListener.onRetryButtonClicked();
                    break;
                default:
            }
        }
    }

    @Override
    public void hideControls() {
        controlsRoot.setVisibility(GONE);
    }

    @Override
    public void showControls() {
        controlsRoot.setVisibility(VISIBLE);
    }

    @Override
    public void setOnClickListener(DebugView.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void displayPlayerStateText(String text) {
        playerStateView.setText(text);
    }

    @Override
    public void displayRetryButton() {
        retryButton.setVisibility(VISIBLE);
    }

    @Override
    public void displayVideoButton() {
        videoButton.setVisibility(VISIBLE);
    }

    @Override
    public void displayAudioButton() {
        audioButton.setVisibility(VISIBLE);
    }

    @Override
    public void displayTextButton() {
        textButton.setVisibility(VISIBLE);
    }

    @Override
    public void hideRetryButton() {
        retryButton.setVisibility(GONE);
    }

    @Override
    public void hideVideoButton() {
        videoButton.setVisibility(GONE);
    }

    @Override
    public void hideAudioButton() {
        audioButton.setVisibility(GONE);
    }

    @Override
    public void hideTextButton() {
        textButton.setVisibility(GONE);
    }

    @Override
    public void displayManifestButton(String text, boolean enabled) {
        masterManifestButton.setText(text);
        masterManifestButton.setEnabled(enabled);
    }

    @Override
    public void displayMasterManifest(HlsMasterPlaylist hlsMasterPlaylist) {
        FragmentManager fragmentManager = ((Activity) getContext()).getFragmentManager();
        MasterManifestDialogFragment masterManifestDialogFragment = new MasterManifestDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MasterManifestDialogFragment.MASTER_MANIFEST, new MasterManifest(hlsMasterPlaylist));
        masterManifestDialogFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, masterManifestDialogFragment).addToBackStack(null).commit();
    }

    @Override
    public void displayAudioPopUp(boolean enableBackgroundAudio, final PopupMenuClickListener popupMenuClickListener, int menuGroup, int offset, int trackCount, List<String> trackNames, int selectedTrack) {
        PopupMenu popup = new PopupMenu(getContext(), audioButton);
        Menu menu = popup.getMenu();
        menu.add(Menu.NONE, Menu.NONE, Menu.NONE, R.string.enable_background_audio);
        final MenuItem backgroundAudioItem = menu.findItem(0);
        backgroundAudioItem.setCheckable(true);
        backgroundAudioItem.setChecked(enableBackgroundAudio);
        PopupMenu.OnMenuItemClickListener clickListener = new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item == backgroundAudioItem) {
                    return popupMenuClickListener.onMenuItemClick(item.getGroupId(), item.getItemId(), true, !item.isChecked());
                }
                return popupMenuClickListener.onMenuItemClick(item.getGroupId(), item.getItemId(), false, false);
            }
        };
        configurePopupWithTracks(popup, clickListener, trackNames, menuGroup, offset, selectedTrack);
        popup.show();
    }

    @Override
    public void displayVideoPopup(final PopupMenuClickListener popupMenuClickListener, int menuGroup, int offset, int trackCount, List<String> trackNames, int selectedTrack) {
        PopupMenu popup = new PopupMenu(getContext(), videoButton);
        configurePopupWithTracks(popup, new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return popupMenuClickListener.onMenuItemClick(item.getGroupId(), item.getItemId(), false, false);
            }
        }, trackNames, menuGroup, offset, selectedTrack);
        popup.show();
    }

    @Override
    public void displayError(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayTextPopup(final PopupMenuClickListener popupMenuClickListener, int menuGroup, int offset, int trackCount, List<String> trackNames, int selectedTrack) {
        PopupMenu popup = new PopupMenu(getContext(), textButton);
        configurePopupWithTracks(popup, new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return popupMenuClickListener.onMenuItemClick(item.getGroupId(), item.getItemId(), false, false);
            }
        }, trackNames, menuGroup, offset, selectedTrack);
        popup.show();
    }

    @Override
    public void displayVerboseLogPopUp(final PopupMenuClickListener popupMenuClickListener) {
        PopupMenu popup = new PopupMenu(getContext(), verboseLogControls);
        Menu menuVerbose = popup.getMenu();
        menuVerbose.add(Menu.NONE, 0, Menu.NONE, R.string.logging_normal);
        menuVerbose.add(Menu.NONE, 1, Menu.NONE, R.string.logging_verbose);
        menuVerbose.setGroupCheckable(Menu.NONE, true, true);
        menuVerbose.findItem((VerboseLogUtil.areAllTagsEnabled()) ? 1 : 0).setChecked(true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                popupMenuClickListener.onMenuItemClick(-1, -1, item.getItemId() != 0, false);
                return true;
            }
        });
        popup.show();
    }

    private void configurePopupWithTracks(PopupMenu popup, PopupMenu.OnMenuItemClickListener menuItemClickListener, List<String> trackNames, int menuGroup, int offset, int selectedTrack) {
        popup.setOnMenuItemClickListener(menuItemClickListener);
        Menu menu = popup.getMenu();
        menu.add(menuGroup, DemoPlayer.TRACK_DISABLED + offset, Menu.NONE, R.string.off);
        for (int i = 0; i < trackNames.size(); i++) {
            menu.add(menuGroup, i + offset, Menu.NONE, trackNames.get(i));
        }
        menu.setGroupCheckable(menuGroup, true, true);
        menu.findItem(selectedTrack + offset).setChecked(true);
    }

    @Override
    public void prepareDebugTextViewHelper(DebugTextViewHelper.Provider provider) {
        debugViewHelper = new DebugTextViewHelper(provider, debugTextView);
        debugViewHelper.start();
    }

    @Override
    public void releaseDebugTextViewHelper() {
        debugViewHelper.stop();
        debugViewHelper = null;
    }
}
