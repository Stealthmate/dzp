/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dzp.game.mechanics.mobs;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 *
 * @author Madara1233
 */
public class BasicMob extends Mob {

    public BasicMob(Point position, int maxHP, int HP, int speed, Bitmap skin) {
        super(position, HP, maxHP, speed, skin);
    }

    @Override
    protected void act() {
    }

    @Override
    protected void drawAct() {

    }

}
