package com.engine.core.entity.mob;

import com.engine.core.Game;
import com.engine.core.entity.Particle;
import com.engine.core.entity.projectile.DefaultProjectile;
import com.engine.core.entity.projectile.ProjectileType;
import com.engine.core.graphics.Screen;
import com.engine.core.graphics.SpriteSheet;
import com.engine.core.input.Keyboard;
import com.engine.core.input.Mouse;
import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

/**
 * Created by Blake on 3/6/2017.
 */
public class Player extends Mob {
    private Keyboard key;
    public static final int PLAYER_ID = 1;

    public Player(Vector2f spawn, String path, Keyboard key, Level level) {
        super(spawn, 32, 32, 4, 3, new SpriteSheet(path), level);
        this.key = key;
        projectileType = ProjectileType.DEFAULT_PROJECTILE;
        mobID = PLAYER_ID * 10 + count;
        speed = 1;
    }


    @Override
    public void update() {
        mov.set(0, 0);
        if (key.up) mov = mov.add(0, -1);
        if (key.right) mov = mov.add(1, 0);
        if (key.down) mov = mov.add(0, 1);
        if (key.left) mov = mov.add(-1, 0);

        if (canShoot) {
            if (Mouse.getMb() == 1) {
                shoot();
            }
        } else {
            shootTimer();
        }
        if (moving) {
            double a = mov.getDegree();
            if (a == -90 || a == -45) dir = 0;
            if (a == 0 || a == 45) dir = 1;
            if (a == 90 || a == 135) dir = 2;
            if (a == 180 || a == -135) dir = 3;
            Vector2f temp = loc.div(16);
            if (tickCount % 10 + speed > 10 - speed) {
                Particle.makeSwarm(temp, 1, 1200, level, level.getTile((int) temp.getX(), (int) temp.getY()).getTileSprite().getImage());
            }
        } else {
            int px = (int) (Game.WIDTH * Game.SCALE / 2.0);
            int py = (int) (Game.HEIGHT * Game.SCALE / 2.0);
            double a = (float) Math.toDegrees(Math.atan2(Mouse.getMy() - py, Mouse.getMx() - px)) + 180;
            if (a > 45 && a <= 135) dir = 0;
            if (a > 135 && a <= 225) dir = 1;
            if (a > 225 && a <= 315) dir = 2;
            if (a > 315 || a <= 45) dir = 3;
        }
        super.update();


    }

    @Override
    public void render(Screen screen) {
        if (moving) {
            if (tickCount % 20 > 10) {
                currentAnimFrame = 1;
            } else {
                currentAnimFrame = 2;
            }
        } else {
            currentAnimFrame = 0;
        }
        sprite = movement[dir][currentAnimFrame];
        screen.render((int) (loc.getX() - sprite.getWidth() / 2), (int) (loc.getY() - sprite.getHeight() / 2), sprite);
    }

    @Override
    protected void shoot() {
        int px = (int) (Game.WIDTH * Game.SCALE / 2.0);
        int py = (int) (Game.HEIGHT * Game.SCALE / 2.0);
        angle = (float) (Math.atan2(Mouse.getMy() - py, Mouse.getMx() - px));
        level.add(new DefaultProjectile(loc, angle, mobID, level));
        canShoot = false;
    }

    public Vector2f getLoc() {
        return loc;
    }
}
