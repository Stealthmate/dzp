package com.dzp.game.mechanics.mobs;

import android.widget.ImageView;
import com.dzp.game.Rectangle;
import com.dzp.game.gui.Run;
import com.dzp.game.mechanics.Map;
import com.dzp.game.mechanics.Tile;
import com.dzp.game.resourceHandler.CurrentGame;

public abstract class Mob {

    private static final int COLLIDE_STRUCTURE = 1;
    private static final int COLLIDE_EMPTY = 2;
    private static final int NO_COLLIDE = 0;

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

    protected abstract void act(int action);
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

    private int willCollide(int x, int y) {

        Map checkMap = CurrentGame.getCurrentGame().getLevel().getMap();
        for (int i = x; i <= x + this.position.width; i++) {
            for (int j = y; j <= y + this.position.height; j++) {
                switch (checkMap.tileAt(i, j)) {
                    case TOWER:
                    case NEXUS:
                        return COLLIDE_STRUCTURE;
                    case IMMOVABLE:
                    case EMPTY:
                        return COLLIDE_EMPTY;
                }
            }
        }
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
