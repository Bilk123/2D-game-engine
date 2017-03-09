package com.engine.core.graphics;

/**
 * Created by Blake on 3/5/2017.
 */
public class Screen {
    public static int clearColor = 0x0;
    private int xOffset = 0, yOffset = 0;
    private int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x, y, clearColor);
            }
        }
    }

    public void render(int px, int py, Sprite sprite) {
        int color;
        for (int y = 0; y < sprite.getHeight(); y++) {
            for (int x = 0; x < sprite.getWidth(); x++) {
                color = sprite.getImage()[x + y * sprite.getWidth()];
                if (color != 0xffff00ff)
                    setPixel(px - xOffset + x, py - yOffset + y, color);
            }
        }
    }

    public void renderFixed(int px, int py, Sprite sprite) {
        int color;
        for (int y = 0; y < sprite.getHeight(); y++) {
            for (int x = 0; x < sprite.getWidth(); x++) {
                color = sprite.getImage()[x + y * sprite.getWidth()];
                if (color != 0xffff00ff)
                    setPixel(y + py, x + px, color);
            }
        }
    }

    public void renderTile(int tx, int ty, Sprite sprite) {
        int w = sprite.getWidth();
        int h = sprite.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                setPixel((tx * w) + x - xOffset, (ty * h) + y - yOffset, sprite.getImage()[x + y * w]);
            }
        }
    }

    public void clear() {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = clearColor;
        }
    }

    public void setPixel(int x, int y, int color) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            pixels[x + y * width] = color;
    }

    public static void setClearColor(int color) {
        clearColor = color;
    }

    public int getPixel(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return pixels[x + y * width];
        else return 0;
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
