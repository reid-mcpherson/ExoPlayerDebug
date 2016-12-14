package com.google.android.exoplayer.demo.debug;

import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.PopupMenu;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.demo.player.DemoPlayer;
import com.google.android.exoplayer.demo.util.NullObject;
import com.google.android.exoplayer.util.DebugTextViewHelper;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.VerboseLogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.R.attr.enabled;

public class DebugControlsPresenterImpl implements DebugControlsPresenter, DemoPlayer.Listener {
    private static final int MENU_GROUP_TRACKS = 1;
    private static final int ID_OFFSET = 2;

    private final DebugControlsView NULL_VIEW = NullObject.create(DebugControlsView.class);
    private final PlayerView NULL_PLAYER_VIEW = NullObject.create(PlayerView.class);
    private final VideoPlayer videoPlayer;
    private final ManifestProvider manifestProvider;

    private DebugControlsView view = NULL_VIEW;
    private PlayerView playerView = NULL_PLAYER_VIEW;

    public DebugControlsPresenterImpl(VideoPlayer videoPlayer, ManifestProvider manifestProvider) {
        this.videoPlayer = videoPlayer;
        this.manifestProvider = manifestProvider;
    }

    @Override
    public void attachView(DebugControlsView videoDebugControlsView, PlayerView playerView) {
        this.view = videoDebugControlsView;
        this.playerView = playerView;
        videoPlayer.addListener(this);
        prepareDebugTextViewHelper(videoPlayer);
    }

    @Override
    public void detachView() {
        releaseDebugTextViewHelper();
        videoPlayer.removeListener(this);
        view.setOnClickListener(null);
        this.view = NULL_VIEW;
        this.playerView = NULL_PLAYER_VIEW;
    }

    @Override
    public void present() {
        view.setOnClickListener(new DebugControlsView.OnClickListener() {
                                    @Override
                                    public void onVideoButtonClicked() {
                                        if (videoPlayer == null) {
                                            return;
                                        }

                                        view.displayVideoPopup(new DebugControlsView.PopupMenuClickListener() {
                                                                   @Override
                                                                   public boolean onMenuItemClick(int groupId, int itemId, boolean enabled, boolean checked) {
                                                                       return onTrackItemClick(groupId, itemId, DemoPlayer.TYPE_VIDEO);
                                                                   }
                                                               },
                                                               MENU_GROUP_TRACKS,
                                                               ID_OFFSET,
                                                               videoPlayer.getTrackCount(DemoPlayer.TYPE_VIDEO),
                                                               getTrackNameList(DemoPlayer.TYPE_VIDEO, videoPlayer.getTrackCount(DemoPlayer.TYPE_VIDEO)),
                                                               videoPlayer.getSelectedTrack(DemoPlayer.TYPE_VIDEO));
                                    }

                                    @Override
                                    public void onAudioButtonClicked() {
                                        if (videoPlayer == null) {
                                            return;
                                        }

                                        view.displayAudioPopUp(playerView.backgroundAudioEnabled(),
                                                               new DebugControlsView.PopupMenuClickListener() {
                                                                   @Override
                                                                   public boolean onMenuItemClick(int groupId, int itemId, boolean enabled, boolean checked) {
                                                                       if (enabled) {
                                                                           playerView.enableBackgroundAudio(checked);
                                                                           return true;
                                                                       }
                                                                       return onTrackItemClick(groupId, itemId, DemoPlayer.TYPE_AUDIO);
                                                                   }
                                                               },
                                                               MENU_GROUP_TRACKS,
                                                               ID_OFFSET,
                                                               videoPlayer.getTrackCount(DemoPlayer.TYPE_AUDIO),
                                                               getTrackNameList(DemoPlayer.TYPE_AUDIO, videoPlayer.getTrackCount(DemoPlayer.TYPE_AUDIO)),
                                                               videoPlayer.getSelectedTrack(DemoPlayer.TYPE_AUDIO));
                                    }

                                    @Override
                                    public void onTextButtonClicked() {
                                        if (videoPlayer == null) {
                                            return;
                                        }

                                        view.displayTextPopup(new DebugControlsView.PopupMenuClickListener() {
                                                                  @Override
                                                                  public boolean onMenuItemClick(int groupId, int itemId, boolean enabled, boolean checked) {
                                                                      return onTrackItemClick(groupId, itemId, DemoPlayer.TYPE_TEXT);
                                                                  }
                                                              },
                                                              MENU_GROUP_TRACKS,
                                                              ID_OFFSET,
                                                              videoPlayer.getTrackCount(DemoPlayer.TYPE_TEXT),
                                                              getTrackNameList(DemoPlayer.TYPE_TEXT, videoPlayer.getTrackCount(DemoPlayer.TYPE_TEXT)),
                                                              videoPlayer.getSelectedTrack(DemoPlayer.TYPE_TEXT));
                                    }

                                    @Override
                                    public void onMasterManifestButtonClicked() {
                                        if (manifestProvider.getMasterPlaylist() == null) {
                                            view.displayError("Master Manifest is null");
                                        } else {
                                            view.displayMasterManifest(manifestProvider.getMasterPlaylist());
                                        }
                                    }

                                    @Override
                                    public void onVerboseLogControlsClicked() {
                                        view.displayVerboseLogPopUp(new DebugControlsView.PopupMenuClickListener() {
                                                                        @Override
                                                                        public boolean onMenuItemClick(int groupId, int itemId, boolean enabled, boolean checked) {
                                                                            VerboseLogUtil.setEnableAllTags(enabled);
                                                                            return true;
                                                                        }
                                                                    }

                                                                   );
                                    }

                                    @Override
                                    public void onRetryButtonClicked() {
                                        playerView.preparePlayer(true);
                                    }
                                }

                               );
    }

    private void prepareDebugTextViewHelper(DebugTextViewHelper.Provider provider) {
        view.prepareDebugTextViewHelper(provider);
        if (playerView.needsPrepare()) {
            updateButtonVisibilities();
        }
    }


    private void releaseDebugTextViewHelper() {
        view.releaseDebugTextViewHelper();
    }

    @Override
    public void toggleControls(boolean playerControlsVisible) {
        if (playerControlsVisible) {
            view.hideControls();
        } else {
            view.showControls();
        }
    }

    @Override
    public void onStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == ExoPlayer.STATE_ENDED) {
            view.showControls();
        }
        String text = "playWhenReady=" + playWhenReady + ", playbackState=";
        switch (playbackState) {
            case ExoPlayer.STATE_BUFFERING:
                text += "buffering";
                break;
            case ExoPlayer.STATE_ENDED:
                text += "ended";
                break;
            case ExoPlayer.STATE_IDLE:
                text += "idle";
                break;
            case ExoPlayer.STATE_PREPARING:
                text += "preparing";
                break;
            case ExoPlayer.STATE_READY:
                text += "ready";
                break;
            default:
                text += "unknown";
                break;
        }
        view.displayPlayerStateText(text);
        updateButtonVisibilities();
    }

    @Override
    public void onError(Exception e) {
        updateButtonVisibilities();
    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        //no-op
    }

    @Override
    public void updateButtonVisibilities() {
        if (playerView.needsPrepare()) {
            view.displayRetryButton();
        } else {
            view.hideRetryButton();
        }

        if (playerView.haveTracks(DemoPlayer.TYPE_VIDEO)) {
            view.displayVideoButton();
        } else {
            view.hideVideoButton();
        }

        if (playerView.haveTracks(DemoPlayer.TYPE_AUDIO)) {
            view.displayAudioButton();
        } else {
            view.hideAudioButton();
        }

        if (playerView.haveTracks(DemoPlayer.TYPE_TEXT)) {
            view.displayTextButton();
        } else {
            view.hideTextButton();
        }
    }

    public boolean onTrackItemClick(int groupId, int itemId, int type) {
        if (videoPlayer == null || groupId != MENU_GROUP_TRACKS) {
            return false;
        }
        videoPlayer.setSelectedTrack(type, itemId - ID_OFFSET);
        return true;
    }

    private List<String> getTrackNameList(int trackType, int trackCount) {
        List<String> trackList = new ArrayList<>();
        for (int i = 0; i < trackCount; i++) {
            trackList.add(buildTrackName(videoPlayer.getTrackFormat(trackType, i)));
        }
        return trackList;
    }

    private static String buildTrackName(MediaFormat format) {
        if (format.adaptive) {
            return "auto";
        }
        String trackName;
        if (MimeTypes.isVideo(format.mimeType)) {
            trackName = joinWithSeparator(joinWithSeparator(buildResolutionString(format),
                                                            buildBitrateString(format)), buildTrackIdString(format));
        } else if (MimeTypes.isAudio(format.mimeType)) {
            trackName = joinWithSeparator(joinWithSeparator(joinWithSeparator(buildLanguageString(format),
                                                                              buildAudioPropertyString(format)), buildBitrateString(format)),
                                          buildTrackIdString(format));
        } else {
            trackName = joinWithSeparator(joinWithSeparator(buildLanguageString(format),
                                                            buildBitrateString(format)), buildTrackIdString(format));
        }
        return trackName.length() == 0 ? "unknown" : trackName;
    }

    private static String buildResolutionString(MediaFormat format) {
        return format.width == MediaFormat.NO_VALUE || format.height == MediaFormat.NO_VALUE
               ? "" : format.width + "x" + format.height;
    }

    private static String buildAudioPropertyString(MediaFormat format) {
        return format.channelCount == MediaFormat.NO_VALUE || format.sampleRate == MediaFormat.NO_VALUE
               ? "" : format.channelCount + "ch, " + format.sampleRate + "Hz";
    }

    private static String buildLanguageString(MediaFormat format) {
        return TextUtils.isEmpty(format.language) || "und".equals(format.language) ? ""
                                                                                   : format.language;
    }

    private static String buildBitrateString(MediaFormat format) {
        return format.bitrate == MediaFormat.NO_VALUE ? ""
                                                      : String.format(Locale.US, "%.2fMbit", format.bitrate / 1000000f);
    }

    private static String joinWithSeparator(String first, String second) {
        return first.length() == 0 ? second : (second.length() == 0 ? first : first + ", " + second);
    }

    private static String buildTrackIdString(MediaFormat format) {
        return format.trackId == null ? "" : " (" + format.trackId + ")";
    }
}
