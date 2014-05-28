package com.dzp.game.mechanics.mobs;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.dzp.game.mechanics.Occupator;
import com.dzp.game.resourceHandler.CurrentGame;

public abstract class Mob {

    protected static final int NO_COLLIDE = 0;
    protected static final int COLLIDE_EMPTY = 1;
    protected static final int COLLIDE_NEXUS = 2;

    private final int speed;
    private final int maxHP;
    private Point position;
    private Point prevPos;
    private boolean canMove;
    private int HP;
    protected final Bitmap skin;

    public Mob(Point position, int HP, int maxHP, int speed, Bitmap skin) {
        this.maxHP = maxHP;
        this.speed = speed;
        this.skin = skin;
        setCanMove(true);
        setPosition(position);
        setPreviousPosition(null);
    }

    protected abstract void act(int collision);

    protected abstract void drawAct();

    public void action() {

        if (!canMove) {
            act(COLLIDE_EMPTY);
        }

        int x = this.position.x;
        int y = this.position.y;
        int xp = this.prevPos.x;
        int yp = this.prevPos.y;

        int dx = 0, dy = 1;
        Point[] d = new Point[4];
        d[0] = new Point(0, speed);
        d[1] = new Point(0, -speed);
        d[2] = new Point(speed, 0);
        d[3] = new Point(-speed, 0);
        int results[] = {5, 5, 5, 5};

        for (int i = 0; i <= 3; i++) {
            if (!(x + d[i].x == xp && y + d[i].y == yp)) {
                results[i] = willCollide(x + d[i].x, y + d[i].y);
            }
        }

        for (int i = 0; i <= 3; i++) {
            if (results[i] == NO_COLLIDE) {

                move(d[i].x, d[i].y);
                act(results[i]);
            }
        }
        for (int i = 0; i <= 3; i++) {
            if (results[i] == COLLIDE_NEXUS) {
                act(results[i]);
            }
        }
    }

    private void move(int dx, int dy) {
        setPreviousPosition(this.position);
        setPosition(new Point(this.position.x + dx, this.position.y + dy));
        drawMovement();
    }

    private void drawMovement() {

    }

    public void despawn() {
        this.HP = 0;
        this.position = null;
        this.prevPos = null;
    }

    private int willCollide(int x, int y) {
        switch (CurrentGame.getCurrentGame().getMap().getTile(x, y).getOccupator()) {
            case EMPTY:
            case IMMOVABLE:
                return COLLIDE_EMPTY;
            case NEXUS:
                return COLLIDE_NEXUS;
            default:
                return NO_COLLIDE;
        }
    }

    private Occupator checkTile(int x, int y) {
        return CurrentGame.getCurrentGame().getMap().getTile(x, y).getOccupator();
    }

    //Set Functions
    private void setPosition(Point p) {
        setPreviousPosition(this.position);
        this.position = p;
    }

    public final void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    private void setPreviousPosition(Point p) {
        this.prevPos = p;
    }

    private void setHP(int HP) {
        this.HP = HP;
    }

    //Get Functions
    protected final Bitmap getSkin() {
        return this.skin;
    }

    public int getHP() {
        return this.HP;
    }

    public Point getPreviousPosition() {
        return this.prevPos;
    }

    public Point getPosition() {
        return this.position;
    }
}
