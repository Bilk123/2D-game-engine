package com.engine.core.entity.mob;

import com.engine.core.entity.Entity;
import com.engine.core.entity.projectile.ProjectileType;
import com.engine.core.graphics.Screen;
import com.engine.core.graphics.Sprite;
import com.engine.core.graphics.SpriteSheet;
import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

/**
 * Created by Blake on 3/6/2017.
 */
public abstract class Mob extends Entity {
    protected static Sprite movement[][];
    protected Sprite sprite;
    protected int width, height;
    protected boolean moving = false;
    protected int dir;
    protected float angle;
    protected boolean canShoot = true;
    protected int shootTimer = 0;
    protected ProjectileType projectileType;
    protected int mobID;
    protected static int count = 0;

    public Mob(Vector2f spawn, int spriteWidth, int spriteHeight, int directions, int animations, SpriteSheet sheet, Level level) {
        super(spawn.mul(16), level);
        movement = new Sprite[directions][animations];
        for (int y = 0; y < animations; y++) {
            for (int x = 0; x < directions; x++) {
                movement[x][y] = new Sprite(spriteWidth, spriteHeight, x, y, sheet);
            }
        }
        sprite = movement[0][0];
        width = spriteWidth;
        height = spriteHeight;
        count++;
    }

    protected abstract void shoot();


    @Override
    public void render(Screen screen) {
        screen.render((int) (loc.getX() - width / 2.0), (int) (loc.getY() - height / 2.0), sprite);
    }

    @Override
    public void update() {
        super.update();

        move(mov);
        moving = !mov.equals(Vector2f.ZERO);


    }

    @Override
    public boolean collision(float xa, float ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            float xt = (loc.getX() + xa * speed + c % 2 * 14 - 7) / 16;
            float yt = (loc.getY() + ya * speed + c / 2 * 12 + 3) / 16;
            if (level.getTile(xt, yt).isSolid()) solid = true;
        }
        return solid;
    }

    public void setProjectileType(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    protected void shootTimer() {

        if (shootTimer < 60) {
            shootTimer += projectileType.getRate();
        } else {

            canShoot = true;
            shootTimer = 0;
        }

    }
}
