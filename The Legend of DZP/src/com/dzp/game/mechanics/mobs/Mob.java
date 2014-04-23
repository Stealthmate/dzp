package com.dzp.game.mechanics.mobs;

import android.graphics.Point;
import android.widget.ImageView;

public abstract class Mob {

    private Point position;
    private Point prevPos;
    private final int maxHP;
    private int HP;
    private static ImageView skin;
    
    public Mob(int maxHP, Point position) {
        this.maxHP = maxHP;
        this.setPosition(position);
        this.prevPos = null;
    }
    
    public void act() {
        
    }
    
    private int findPath() {
        return 0; //NOT DONE YET!!!
    }
    
    private void setPosition(Point p) {
        this.position = p;
    }
    
    public static void setSkin(ImageView skin) {
        Mob.skin = skin;
    }
    
    private void setHP(int HP) {
        this.HP = HP;
    } 
            
    public static ImageView getSkin() {
        return Mob.skin;
    }
    
    public int getHP() {
        return this.HP;
    }
    
    public Point getPosition() {
        return this.position;
    }
}