package com.google.android.exoplayer.demo.debug.view;

public interface SegmentView {

    void displayUrl(String text);

    void displayDurationSecs(double durationSecs);

    void displayDiscontinuitySequenceNumber(int sequenceNum);

    void displayStartTime(long startTimeUs);

    void displayEncrypted(boolean encrypted);

    void displayEncryptionKeyUri(String encryptionKeyUri);

    void displayEncryptionIV(String encryptionIV);

    void displayByterangeOffset(long byterangeOffset);

    void displayByterangeLength(long byterangeLength);
}
