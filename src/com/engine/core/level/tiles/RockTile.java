package com.engine.core.level.tiles;

import com.engine.core.graphics.Sprite;

/**
 * Created by Blake on 3/6/2017.
 */
public class RockTile extends Tile {
    public RockTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
