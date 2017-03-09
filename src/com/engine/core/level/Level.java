package com.engine.core.level;

import com.engine.core.entity.Entity;
import com.engine.core.graphics.Screen;
import com.engine.core.level.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Blake on 3/6/2017.
 */
public abstract class Level {
    protected int width, height;
    protected Tile[] map;
    protected int[] tileIDs;
    private ArrayList<Entity> entities;
    protected HashMap<Integer, Tile> tileList;

    public Level(String path) {
        tileList = new HashMap<>();
        entities = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(Level.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tileIDs = new int[width * height];
            map = new Tile[width * height];
            image.getRGB(0, 0, width, height, tileIDs, 0, width);
            addAllTiles();
            loadTiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = ((xScroll + screen.getWidth()) >> 4) + 1;
        int y0 = yScroll >> 4;
        int y1 = ((yScroll + screen.getHeight()) >> 4) + 1;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                if (x >= 0 && x < width && y >= 0 && y < height)
                    map[x + y * width].render(x, y, screen);
                else
                    tileList.get(0).render(x, y, screen);
            }
        }
        for (int i = 0; i < entities.size(); i++) entities.get(i).render(screen);
    }

    public void update() {


        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
            }
        }
    }

    protected void loadTiles() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x + y * width] = getTile(tileIDs[x + y * width]);
            }
        }
    }

    public Tile getTile(int ID) {
        if (tileList.containsKey(ID))
            return tileList.get(ID);
        else
            return tileList.get(0);
    }

    protected void addTile(int ID, Tile tile) {
        tileList.put(ID, tile);
    }

    protected abstract void addAllTiles();

    public Tile getTile(float x, float y) {
        if (x < 0 || y < 0) {
            return getTile(0);
        }
        if (x >= 0 && x < width && y >= 0 && y < height)
            return map[(int) x + (int) y * width];
        else return getTile(0);
    }

    public void add(Entity e) {
        entities.add(e);
    }
}
