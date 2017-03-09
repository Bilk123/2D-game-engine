package com.engine.core.entity.projectile;

import com.engine.core.entity.Entity;
import com.engine.core.entity.Particle;
import com.engine.core.graphics.Screen;
import com.engine.core.graphics.Sprite;
import com.engine.core.graphics.SpriteSheet;
import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

import java.util.ArrayList;

/**
 * Created by Blake on 3/8/2017.
 */
public abstract class Projectile extends Entity {
    protected final Vector2f origin;
    protected double angle;
    protected Sprite sprite;
    protected ProjectileType type;
    protected static ArrayList<Sprite> projectileSprites;
    protected int originID;

    static {
        SpriteSheet sheet = new SpriteSheet("/sprite sheets/projectile sheet.png");
        projectileSprites = new ArrayList<>();
        projectileSprites.add(new Sprite(16, 16, 0, 0, sheet));
    }

    public Projectile(Vector2f origin, double angle, int originID, ProjectileType type, Level level) {
        super(level);
        this.origin = origin;
        this.originID = originID;
        this.angle = angle;
        mov.set((float) Math.cos(angle), (float) Math.sin(angle));
        sprite = projectileSprites.get(0);
        loc = origin;
        this.type = type;
        speed = type.getSpeed();
    }

    @Override
    public void update() {
        if (loc.sub(origin).length() > type.getRange()) {
            remove();
        }
        move(mov);
        super.update();
    }

    @Override
    protected void move(Vector2f dir) {
        if (!collision(mov.getX(), mov.getY()))
            loc = loc.add(dir.mul(speed));
        else {
            Vector2f temp = loc.add(mov.mul(speed + 16)).div(16);

            int[] colors = level.getTile(temp.getX(), temp.getY()).getTileSprite().getImage();
            Particle.makeSwarm(loc.div(16), 6, 2500, level, colors);
            remove();
        }
    }

    @Override
    public void render(Screen screen) {
        screen.render((int) (loc.getX() - sprite.getWidth() / 2), (int) (loc.getY() - sprite.getHeight() / 2), sprite);
    }

    @Override
    public boolean collision(float xa, float ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            float xt = (loc.getX() + xa * speed + c % 2 * 8 - 4) / 16;
            float yt = (loc.getY() + ya * speed + c / 2 * 8 - 4) / 16;
            if (level.getTile(xt, yt).isSolid()) solid = true;
        }
        return solid;
    }
}
