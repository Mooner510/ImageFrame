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

import static org.mooner.imageframe.Main.*;

public class ImageFrame {
    public ImageFrame() {
        File f = new File(FILE_NAME);
        new File("src/main/resources/result/").mkdirs();
        final String[] list = new File("src/main/resources/result/").list();
        if(list != null) {
            for (String s : list) {
                new File(s).delete();
            }
        }
        double grabSize = MAX_DURATION_SECONDS * FRAMERATE;
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(f));
            grab.seekToSecondPrecise(START_SECONDS);
            int i = 0;
            while (true) {
                try {
                    if(grabSize >= 0 &&  ++i > grabSize) {
                        System.out.println("Max Grab size limited");
                        break;
                    }
                    Picture p = grab.getNativeFrame();
                    BufferedImage image = AWTUtil.toBufferedImage(p);
                    System.out.println("frame-" + i + ".png");
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
