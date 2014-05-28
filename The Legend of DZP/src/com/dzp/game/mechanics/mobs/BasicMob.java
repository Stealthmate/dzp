/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dzp.game.mechanics.mobs;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.dzp.game.resourceHandler.CurrentGame;

/**
 *
 * @author Madara1233
 */
public class BasicMob extends Mob{

    public BasicMob(Point position, int maxHP, int HP, int speed, Bitmap skin) {
        super(position, HP, maxHP, speed, skin);
    }

    @Override
    protected void act(int collision) {
        switch(collision) {
            case Mob.COLLIDE_NEXUS : {
                CurrentGame.getCurrentGame().getNexus().strike();
            }
        }
    }

    @Override
    protected void drawAct() {
        
    }
    

}
