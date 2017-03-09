package com.engine.core.level.tiles;

import com.engine.core.graphics.Screen;
import com.engine.core.graphics.Sprite;


/**
 * Created by Blake on 3/5/2017.
 */
public abstract class Tile {
    private Sprite sprite;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int tx, int ty, Screen screen) {
        screen.renderTile(tx, ty, sprite);
    }

    public Sprite getTileSprite() {
        return sprite;
    }

    public boolean isSolid() {
        return false;
    }
}
