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
    public static String FILE_NAME = "test.mp4";
    public static String RESULT_NAME = "frame-{i}.png";

    public static void main(String[] args) {
        File f = new File("src/main/resources/" + FILE_NAME);
        final String[] list = new File("src/main/resources/result/").list();
        if(list != null) {
            for (String s : list) {
                new File(s).delete();
            }
        }
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(f));
            grab.seekToSecondPrecise(START_SECONDS);
            int i = 0;
            new File("src/main/resources/result/").mkdir();
            while (true) {
                try {
                    Picture p = grab.getNativeFrame();
                    BufferedImage image = AWTUtil.toBufferedImage(p);
                    System.out.println("frame-" + ++i + ".png");
                    ImageIO.write(image, "png", new File("src/main/resources/result/" + RESULT_NAME.replace("{i}", String.valueOf(i))));
                } catch (Exception e) {
                    break;
                }
            }
        } catch (IOException | JCodecException e) {
            e.printStackTrace();
        }
    }
}
