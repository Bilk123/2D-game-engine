package com.engine.core.level.tiles;

import com.engine.core.graphics.Sprite;

/**
 * Created by Blake on 3/6/2017.
 */
public class VoidTile extends Tile {
    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
