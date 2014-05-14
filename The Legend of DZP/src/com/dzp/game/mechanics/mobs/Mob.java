package com.dzp.game.mechanics.mobs;

import android.widget.ImageView;
import com.dzp.game.Rectangle;
import com.dzp.game.mechanics.Map;
import com.dzp.game.mechanics.towers.Tower;
import com.dzp.game.resourceHandler.CurrentGame;

public abstract class Mob {

    protected static final Tower COLLIDE_EMPTY = Tower.noTower;
    protected static final Tower NO_COLLIDE = null;

    private final int speed;

    private Rectangle position;
    private Rectangle prevPos;
    private boolean canMove;
    private final int maxHP;
    private int HP;
    private static ImageView skin;

    public Mob(int maxHP, Rectangle position, int speed) {
        this.maxHP = maxHP;
        this.speed = speed;
        setCanMove(true);
        setPosition(position);
        setPreviousPosition(null);
    }

    protected abstract void act(Tower ction);
    
    public void move() {

        if(!canMove) {
            act(COLLIDE_EMPTY);
        }
        
        int x = this.position.x;
        int y = this.position.y;
        int xp = this.prevPos.x;
        int yp = this.prevPos.y;

        int dx = 0, dy = 1;

        if (!(x + dx == xp && y + dy == yp)) {
            act(willCollide(x + dx, y + dy));
        }

        dx = 0;
        dy = -1;

        if (!(x + dx == xp && y + dy == yp)) {
            act(willCollide(x + dx, y + dy));
        }

        dx = 1;
        dy = 0;

        if (!(x + dx == xp && y + dy == yp)) {
            act(willCollide(x + dx, y + dy));
        }

        dx = -1;
        dy = 0;

        if (!(x + dx == xp && y + dy == yp)) {
            act(willCollide(x + dx, y + dy));
        }

    }

    public void despawn() {
        this.HP = 0;
        this.position = null;
        this.prevPos = null;
    }

    private Tower willCollide(int x, int y) {

        Map checkMap = CurrentGame.getCurrentGame().getLevel().getMap();
        for (int i = x; i <= x + this.position.width; i++) {
            for (int j = y; j <= y + this.position.height; j++) {
                switch (checkMap.getTile(i, j).getOccupator()) {
                    case TOWER:
                    case NEXUS:
                        return checkMap.getTile(i, j).getOccupatorTower();
                    case IMMOVABLE:
                    case EMPTY:
                        return COLLIDE_EMPTY;
                }
            }
        }
        
        Rectangle newPos = new Rectangle(
                this.position.x+x, 
                this.position.y+y, 
                this.position.width, 
                this.position.height);
        
        this.setPosition(newPos);
        
        return NO_COLLIDE;
    }

    //Set Functions
    private void setPosition(Rectangle p) {
        setPreviousPosition(this.position);
        this.position = p;
    }
    public final void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
    private void setPreviousPosition(Rectangle p) {
        this.prevPos = p;
    }

    public static void setSkin(ImageView skin) {
        Mob.skin = skin;
    }

    private void setHP(int HP) {
        this.HP = HP;
    }

    //Get Functions
    public static ImageView getSkin() {
        return Mob.skin;
    }

    public int getHP() {
        return this.HP;
    }

    public Rectangle getPreviousPosition() {
        return this.prevPos;
    }

    public Rectangle getPosition() {
        return this.position;
    }
}
