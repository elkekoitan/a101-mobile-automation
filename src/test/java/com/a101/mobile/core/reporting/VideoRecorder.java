package com.a101.mobile.core.reporting;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import java.io.File;
import lombok.extern.log4j.Log4j2;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

@Log4j2
public class VideoRecorder {
    private ScreenRecorder screenRecorder;
    private static final String VIDEO_PATH = "test-output/videos/";

    public void startRecording(String methodName) {
        try {
            File file = new File(VIDEO_PATH + methodName + "/");
            if (!file.exists()) {
                file.mkdirs();
            }

            Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

            this.screenRecorder = new ScreenRecorder(gc,
                    captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null,
                    file);

            this.screenRecorder.start();
            log.info("Video recording started for test: {}", methodName);

        } catch (Exception e) {
            log.error("Failed to start video recording: {}", e.getMessage());
        }
    }

    public void stopRecording() {
        if (screenRecorder != null) {
            try {
                screenRecorder.stop();
                log.info("Video recording stopped successfully");
            } catch (Exception e) {
                log.error("Failed to stop video recording: {}", e.getMessage());
            }
        }
    }

    public void deleteRecording() {
        if (screenRecorder != null) {
            try {
                File dir = screenRecorder.getCreatedMovieFiles().get(0).getParentFile();
                for (File file : dir.listFiles()) {
                    file.delete();
                }
                dir.delete();
                log.info("Video recording files deleted successfully");
            } catch (Exception e) {
                log.error("Failed to delete video recording: {}", e.getMessage());
            }
        }
    }
}
