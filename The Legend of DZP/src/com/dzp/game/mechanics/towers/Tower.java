package com.dzp.game.mechanics.towers;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.dzp.game.resourceHandler.CurrentGame;

public abstract class Tower {

    //Properties
    protected static final int maxLevel = 5;
    protected final Point position;
    protected final int maxCharge;
    protected final int cost;
    protected final Bitmap skin;
    protected int frequency;
    protected int damage;
    protected int range;
    protected int level;
    protected int charge;
    //End of Properties

    protected Tower(
            Point position,
            int range,
            int level,
            int maxCharge,
            int cost,
            int frequency,
            int damage,
            Bitmap skin) {

        this.position = position;
        this.maxCharge = maxCharge;
        this.cost = cost;
        this.frequency = frequency;
        this.damage = damage;
        this.skin = skin;
        setLevel(level);
        setRange(range);
        setCharge(maxCharge);
    }

    ///Set parameters
    protected final void setRange(int range) {
        this.range = range;
    }

    protected final void setLevel(int level) {
        this.level = level;
    }

    protected final void setCharge(int charge) {
        this.charge = charge;
    }

    ///Get parameters
    public final int getRange() {
        return this.range;
    }

    public final Point getPosition() {
        return this.position;
    }

    public final int getCost() {
        return this.cost;
    }

    public final int getDamage() {
        return this.damage;
    }

    public final int getFrequency() {
        return this.frequency;
    }

    public final int getCharge() {
        return this.charge;
    }

    public final int getLevel() {
        return this.level;
    }

    ///Status updates
    public void despawn() {
        CurrentGame.getCurrentGame().manager.despawnTower(this);
    }

    public abstract void upgrade();

    ///Action
    public boolean hasInRange() {

        for (int x = 0; x <= range; x++) {
            for (int y = 0; y <= range; y++) {
                if (x * x + y * y <= range * range) {
                    
                }
            }
        }
        return false;
    }

    public abstract void fire();
}
