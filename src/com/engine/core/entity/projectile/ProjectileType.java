package com.engine.core.entity.projectile;

/**
 * Created by Blake on 3/8/2017.
 */
public enum ProjectileType {
    DEFAULT_PROJECTILE(5, 1, 75, 10);


    private int rate;
    private int speed;
    private int range;
    private int damage;

    ProjectileType(int rate, int speed, int range, int damage) {
        this.rate = rate;
        this.speed = speed;
        this.range = range;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getRate() {
        return rate;
    }

    public int getSpeed() {
        return speed;
    }
}
