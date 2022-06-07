package org.mooner.imageframe;

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
