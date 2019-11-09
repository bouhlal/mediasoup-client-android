package org.mediasoup.droid;

import android.support.annotation.Nullable;

import org.webrtc.CalledByNative;
import org.webrtc.MediaStreamTrack;
import org.webrtc.RTCUtils;

public class Consumer {

  public interface Listener {

    @CalledByNative
    void onTransportClose(Consumer consumer);
  }

  private long mNativeConsumer;

  @Nullable private MediaStreamTrack cachedTrack;

  @CalledByNative
  public Consumer(long nativeProducer) {
    mNativeConsumer = nativeProducer;
    long nativeTrack =  getNativeTrack(mNativeConsumer);
    cachedTrack = RTCUtils.createMediaStreamTrack(nativeTrack);
  }

  public String getId() {
    return getNativeId(mNativeConsumer);
  }

  public String getProducerId() {
    return getNativeProducerId(mNativeConsumer);
  }

  public boolean isClosed() {
    return isNativeClosed(mNativeConsumer);
  }

  public boolean isPaused() {
    return isNativePaused(mNativeConsumer);
  }

  public String getKind() {
    return getNativeKind(mNativeConsumer);
  }

  public MediaStreamTrack getTrack() {
    return cachedTrack;
  }

  public String getRtpParameters() {
    return getNativeRtpParameters(mNativeConsumer);
  }

  public String getAppData() {
    return getNativeAppData(mNativeConsumer);
  }

  public void resume() {
    nativeResume(mNativeConsumer);
  }

  public void pause() {
    nativePause(mNativeConsumer);
  }

  public String getStats() {
    return getNativeStats(mNativeConsumer);
  }

  public void close() {
    nativeClose(mNativeConsumer);
  }

  private static native String getNativeId(long nativeConsumer);

  private static native String getNativeProducerId(long nativeConsumer);

  private static native boolean isNativeClosed(long nativeConsumer);

  private static native boolean isNativePaused(long nativeConsumer);

  private static native String getNativeKind(long nativeConsumer);

  private static native long getNativeTrack(long nativeConsumer);

  private static native String getNativeRtpParameters(long nativeConsumer);

  private static native String getNativeAppData(long nativeConsumer);

  private static native void nativeResume(long nativeConsumer);

  private static native void nativePause(long nativeConsumer);

  private static native String getNativeStats(long nativeConsumer);

  private static native void nativeClose(long nativeConsumer);
}
