package org.mooner.imageframe;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static double START_SECONDS = 0;
    public static double FRAMERATE = 60;
    public static double MAX_DURATION_SECONDS = 0;
    public static String FILE_NAME = "test.mp4";
    public static String RESULT_NAME = "frame-{i}.png";

    public static void main(String[] args) {
        new ImageFrame();
    }
}
