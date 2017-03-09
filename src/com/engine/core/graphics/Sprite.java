package com.engine.core.graphics;


/**
 * Created by Blake on 3/6/2017.
 */
public class Sprite {

    private int[] image;
    private int width, height;

    public Sprite(int width, int height, int x, int y, SpriteSheet spriteSheet) {
        this.width = width;
        this.height = height;
        image = new int[width * height];
        for (int y_ = 0; y_ < height; y_++) {
            for (int x_ = 0; x_ < width; x_++) {
                image[x_ + y_ * width] = spriteSheet.getSheet()[x * width + x_ + (y * height + y_) * spriteSheet.getWidth()];
            }
        }
    }

    public Sprite(int width, int height, int color) {
        this.width = width;
        this.height = height;
        image = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            image[i] = color;
        }
    }

    public int[] getImage() {
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
