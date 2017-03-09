package com.engine.core.level;


import com.engine.core.graphics.Sprite;
import com.engine.core.graphics.SpriteSheet;
import com.engine.core.level.tiles.*;

import java.util.HashMap;


/**
 * Created by Blake on 3/6/2017.
 */
public class DefaultLevel extends Level {
    private static HashMap<Integer, Sprite> defaultSprites;

    public static final int DEFAULT_VOID = 0;
    public static final int DEFAULT_GRASS;
    public static final int DEFAULT_GRASS2;
    public static final int DEFAULT_FLOWER;
    public static final int DEFAULT_ROCK;
    public static final int DEFAULT_DIRT;
    public static final int DEFAULT_ROCKPATH1;
    public static final int DEFAULT_ROCKPATH2;
    private static final int DEFAULT_WALL;

    static {
        SpriteSheet sheetTest = new SpriteSheet("/sprite sheets/test sheet.png");
        defaultSprites = new HashMap<>();
        defaultSprites.put(DEFAULT_VOID, new Sprite(16, 16, 0x1e90ff));
        defaultSprites.put(DEFAULT_GRASS = 0xFF00FF00, new Sprite(16, 16, 0, 0, sheetTest));
        defaultSprites.put(DEFAULT_GRASS2 = 0xFF00FF7F, new Sprite(16, 16, 1, 0, sheetTest));
        defaultSprites.put(DEFAULT_FLOWER = 0xFFFFFF00, new Sprite(16, 16, 2, 0, sheetTest));
        defaultSprites.put(DEFAULT_ROCK = 0xFF7F7F00, new Sprite(16, 16, 3, 0, sheetTest));
        defaultSprites.put(DEFAULT_DIRT = 0xFF7F3300, new Sprite(16, 16, 4, 0, sheetTest));
        defaultSprites.put(DEFAULT_ROCKPATH1 = 0xFF7F7F7F, new Sprite(16, 16, 8, 0, sheetTest));
        defaultSprites.put(DEFAULT_ROCKPATH2 = 0xFF878787, new Sprite(16, 16, 9, 0, sheetTest));
        defaultSprites.put(DEFAULT_WALL = 0xFF434343, new Sprite(16, 16, 0, 1, sheetTest));
    }

    public DefaultLevel(String path) {
        super(path);

    }

    @Override
    protected void addAllTiles() {
        addTile(DEFAULT_VOID, new VoidTile(defaultSprites.get(DEFAULT_VOID)));
        addTile(DEFAULT_GRASS, new GrassTile(defaultSprites.get(DEFAULT_GRASS)));
        addTile(DEFAULT_GRASS2, new GrassTile(defaultSprites.get(DEFAULT_GRASS2)));
        addTile(DEFAULT_FLOWER, new FlowerTile(defaultSprites.get(DEFAULT_FLOWER)));
        addTile(DEFAULT_ROCK, new RockTile(defaultSprites.get(DEFAULT_ROCK)));
        addTile(DEFAULT_DIRT, new DirtTile(defaultSprites.get(DEFAULT_DIRT)));
        addTile(DEFAULT_ROCKPATH1, new RockPathTile(defaultSprites.get(DEFAULT_ROCKPATH1)));
        addTile(DEFAULT_ROCKPATH2, new RockPathTile(defaultSprites.get(DEFAULT_ROCKPATH2)));
        addTile(DEFAULT_WALL, new WallTile(defaultSprites.get(DEFAULT_WALL)));
    }


}
