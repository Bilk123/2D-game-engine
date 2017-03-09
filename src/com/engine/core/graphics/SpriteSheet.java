package com.engine.core.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Blake on 3/6/2017.
 */
public class SpriteSheet {


    private int width, height;
    private int[] sheet;

    public SpriteSheet(String path) {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            sheet = new int[width * height];
            image.getRGB(0, 0, width, height, sheet, 0, width);
        } catch (IOException e) {

            e.printStackTrace();
        }
        int[] test = sheet;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[] getSheet() {
        return sheet;
    }
}
