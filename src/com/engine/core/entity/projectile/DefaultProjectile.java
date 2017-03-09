package com.engine.core.entity.projectile;

import com.engine.core.level.Level;
import com.engine.core.util.math.Vector2f;

/**
 * Created by Blake on 3/8/2017.
 */
public class DefaultProjectile extends Projectile {

    public DefaultProjectile(Vector2f origin, double angle, int originID, Level level) {
        super(origin, angle, originID, ProjectileType.DEFAULT_PROJECTILE, level);
    }
}
