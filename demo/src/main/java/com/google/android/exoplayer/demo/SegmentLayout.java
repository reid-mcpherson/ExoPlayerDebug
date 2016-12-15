package com.google.android.exoplayer.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.exoplayer.demo.debug.view.SegmentView;

public class SegmentLayout extends LinearLayout implements SegmentView {

    @Bind(R.id.url) TextView url;
    @Bind(R.id.durationSecs) TextView durationSecs;
    @Bind(R.id.sequenceNum) TextView sequenceNum;
    @Bind(R.id.startTimeUs) TextView startTimeUs;
    @Bind(R.id.encrypted) TextView encrypted;
    @Bind(R.id.encryptionKeyUri) TextView encryptionKeyUri;
    @Bind(R.id.encryptionIV) TextView encryptionIV;
    @Bind(R.id.byterangeOffset) TextView byterangeOffset;
    @Bind(R.id.byterangeLength) TextView byterangeLength;

    public SegmentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @Override
    public void displayUrl(String text) {
        url.setText(getResources().getString(R.string.segment_url, text));
    }

    @Override
    public void displayDurationSecs(double durationSecs) {
        this.durationSecs.setText(getResources().getString(R.string.segment_duration, durationSecs));
    }

    @Override
    public void displayDiscontinuitySequenceNumber(int sequenceNum) {
        this.sequenceNum.setText(getResources().getString(R.string.segment_discontinuity, sequenceNum));
    }

    @Override
    public void displayStartTime(long startTimeUs) {
        this.startTimeUs.setText(getResources().getString(R.string.segment_start_time, startTimeUs));
    }

    @Override
    public void displayEncrypted(boolean encrypted) {
        this.encrypted.setText(getResources().getString(R.string.segment_encrypted, encrypted));
    }

    @Override
    public void displayEncryptionKeyUri(String encryptionKeyUri) {
        this.encryptionKeyUri.setText(getResources().getString(R.string.segment_encryption_key, encryptionKeyUri));
    }

    @Override
    public void displayEncryptionIV(String encryptionIV) {
        this.encryptionIV.setText(getResources().getString(R.string.segment_encryption_IV, encryptionIV));
    }

    @Override
    public void displayByterangeOffset(long byterangeOffset) {
        this.byterangeOffset.setText(getResources().getString(R.string.segment_byterange_offset, byterangeOffset));
    }

    @Override
    public void displayByterangeLength(long byterangeLength) {
        this.byterangeLength.setText(getResources().getString(R.string.segment_byterange_length, byterangeLength));
    }
}
