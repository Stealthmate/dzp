/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dzp.game.mechanics.mobs;

import com.dzp.game.Rectangle;
import com.dzp.game.mechanics.towers.Tower;

/**
 *
 * @author Madara1233
 */
public class BasicMob extends Mob{

    public BasicMob(int maxHP, Rectangle position, int speed) {
        super(maxHP, position, speed);
    }

    @Override
    protected void act(Tower collideTower) {
        if(!collideTower.equals(Tower.noTower)) {
            //Deal damage and suicide
        }
    }
    

}
