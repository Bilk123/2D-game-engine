package com.engine.core.level.tiles;

import com.engine.core.graphics.Sprite;

/**
 * Created by Blake on 3/8/2017.
 */
public class WallTile extends Tile {

    public WallTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
