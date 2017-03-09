package com.engine.core.entity;

import com.engine.core.graphics.Screen;
import com.engine.core.graphics.Sprite;
import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

import java.util.Random;


/**
 * Created by Blake on 3/8/2017.
 */
public class Particle extends Entity {
    private int color;
    private int life;
    private Sprite sprite;
    protected Vector2f acceleration;

    protected static final Random random = new Random();

    public Particle(Vector2f loc, int color, int life, Level level) {
        super(loc.mul(16), level);
        this.color = color;
        this.life = life;
        sprite = new Sprite(3, 3, this.color);
        mov.set((float) random.nextGaussian() / 3, (float) random.nextGaussian() / 3);
        level.add(this);
        acceleration = mov.mul(-1);
    }

    @Override
    public void render(Screen screen) {
        screen.render((int) loc.getX(), (int) loc.getY(), sprite);
    }

    @Override
    public void update() {
        if (tickCount < life) {
            tickCount += 1000.0 / 60.0;
        } else {
            tickCount = 0;
            remove();
        }
        move(mov);
    }

    @Override
    protected void move(Vector2f vec) {
        loc = loc.add(mov.mul(speed));
        acceleration = mov.mul(-0.05f);
        mov = mov.add(acceleration);
    }

    public static void makeSwarm(Vector2f origin, int amount, int life, Level level, int... colors) {
        for (int i = 0; i < amount; i++) {
            new Particle(origin, colors[random.nextInt(colors.length)], life, level);
        }
    }
}
