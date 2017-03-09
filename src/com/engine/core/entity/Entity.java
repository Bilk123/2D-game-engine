package com.engine.core.entity;

import com.engine.core.graphics.Screen;
import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

/**
 * Created by Blake on 3/6/2017.
 */
public abstract class Entity {
    public Vector2f loc;
    protected Vector2f mov;
    protected float speed = 1.2f;
    protected Level level;
    protected int tickCount;
    protected int currentAnimFrame;
    private boolean removed = false;

    public Entity(Level level) {
        loc = new Vector2f();
        mov = new Vector2f();
        this.level = level;
    }

    public Entity(Vector2f loc, Level level) {
        this.loc = loc;
        mov = new Vector2f();
        this.level = level;
    }

    public void update() {
        if (tickCount < 1000) tickCount++;
        else tickCount = 0;
    }

    protected void move(Vector2f vec) {
        mov.normalise();
        float xa = vec.getX();
        float ya = vec.getY();
        mov.set(0, 0);
        if (!collision(xa, 0)) {
            mov = mov.add(xa, 0);
        }
        if (!collision(0, ya)) {
            mov = mov.add(0, ya);
        }
        loc = loc.add(mov.mul(speed));
    }

    public boolean collision(float xa, float ya) {

        return false;
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void render(Screen screen) {
    }

}
