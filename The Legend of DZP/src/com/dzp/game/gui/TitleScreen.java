/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dzp.game.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.view.MotionEvent;
import android.view.View;
import com.dzp.game.DZPgame;

/**
 *
 * @author madara1233
 */
public class TitleScreen extends View {

    private final static Dimension btnDimensions = new Dimension(640/3, 241/3);
    private final static int btnX = DZPgame.screenWidth / 2 - btnDimensions.width / 2;
    private final static int btnY = DZPgame.screenHeight/30;
    private final static Point startBtnPos = new Point(btnX, 6 * btnY);
    private final static Point optionsBtnPos = new Point(btnX, 13 * btnY);
    private final static Point aboutBtnPos = new Point(btnX, 20 * btnY);

    private final GameButton start;
    private final GameButton options;
    private final GameButton about;
    private final Bitmap background;
    private static final Paint drawPaint = new Paint();
    private static final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);

    private abstract class GameButton extends View {

        private boolean selected;
        private final Bitmap img;
        private final Rectangle hitbox;

        public GameButton(Context context, Bitmap img, int x, int y) {
            super(context);
            this.img = img;
            selected = false;
            hitbox = new Rectangle(x, y, btnDimensions.width, btnDimensions.height);
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            switch (e.getActionMasked()) {
                case MotionEvent.ACTION_OUTSIDE:
                case MotionEvent.ACTION_MOVE:
                    if (!this.isInHitbox(e.getX(), e.getY())) {
                        selected = false;
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    selected = true;
                    break;
                case MotionEvent.ACTION_UP:
                    if (selected) {
                        act();
                        selected = false;
                    }
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(
                    img, hitbox.x, hitbox.y, drawPaint);
        }

        boolean isInHitbox(float x, float y) {
            boolean isX = x > hitbox.x && x < hitbox.x + hitbox.width;
            boolean isY = y > hitbox.y && y < hitbox.y + hitbox.height;
            return isX && isY;
        }

        protected abstract void act();
    }

    public TitleScreen(Context context, Bitmap background) {
        super(context);

        this.background = background;
        
        Bitmap buttonImg = (Bitmap) DZPgame.get(DZPgame.START_BTN_IMG);
        buttonImg = DZPgame.getResizedBitmap(buttonImg, btnDimensions.width, btnDimensions.height);
        //buttonImg = DZPgame.getResizedBitmap(buttonImg, btnDimensions.width, btnDimensions.height);

        start = new GameButton(getContext(), buttonImg, startBtnPos.x, startBtnPos.y) {

            @Override
            protected void act() {
                tg.startTone(ToneGenerator.TONE_PROP_BEEP);
            }
        };
        buttonImg = (Bitmap) DZPgame.get(DZPgame.OPTIONS_BTN_IMG);
        buttonImg = DZPgame.getResizedBitmap(buttonImg, btnDimensions.width, btnDimensions.height);
        options = new GameButton(getContext(), buttonImg, optionsBtnPos.x, optionsBtnPos.y) {

            @Override
            protected void act() { 
                tg.startTone(ToneGenerator.TONE_CDMA_ANSWER);
            }

        };
        buttonImg = (Bitmap) DZPgame.get(DZPgame.ABOUT_BTN_IMG);
        buttonImg = DZPgame.getResizedBitmap(buttonImg, btnDimensions.width, btnDimensions.height);
        about = new GameButton(getContext(), buttonImg, aboutBtnPos.x, aboutBtnPos.y) {

            @Override
            protected void act() {
                tg.startTone(ToneGenerator.TONE_PROP_BEEP);
            }
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, drawPaint);
        start.draw(canvas);
        options.draw(canvas);
        about.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Point hit = new Point((int) e.getX(), (int) e.getY());
        if (start.isInHitbox(e.getX(), e.getY())) {
            start.onTouchEvent(e);
        } else if (options.isInHitbox(e.getX(), e.getY())) {
            options.onTouchEvent(e);
        } else if (about.isInHitbox(e.getX(), e.getY())) {
            about.onTouchEvent(e);
        }
        return true;
    }
}
