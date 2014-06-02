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
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.view.MotionEvent;
import android.view.View;
import com.dzp.game.DZPgame;
import com.dzp.game.R;

/**
 *
 * @author madara1233
 */
public class TitleScreen extends View {

    private final static Dimension btnDimensions = new Dimension(100, 30);
    private final static int btnX = DZPgame.screenWidth / 2 - btnDimensions.width / 2;
    private final static int btnY = DZPgame.screenHeight / 9;
    private final static Point startBtnPos = new Point(btnX, 2 * btnY);
    private final static Point optionsBtnPos = new Point(btnX, 4 * btnY);
    private final static Point scoreBtnPos = new Point(btnX, 6 * btnY);

    private final GameButton start;
    private final GameButton options;
    private final GameButton score;
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
        
        Bitmap buttonImg = ((BitmapDrawable) getResources().getDrawable(R.drawable.screen1)).getBitmap();
        
        buttonImg = DZPgame.getResizedBitmap(buttonImg, btnDimensions.width, btnDimensions.height);

        start = new GameButton(getContext(), buttonImg, startBtnPos.x, startBtnPos.y) {

            @Override
            protected void act() {
                tg.startTone(ToneGenerator.TONE_PROP_BEEP);
            }
        };

        options = new GameButton(getContext(), buttonImg, optionsBtnPos.x, optionsBtnPos.y) {

            @Override
            protected void act() { 
                tg.startTone(ToneGenerator.TONE_PROP_BEEP);
            }

        };

        score = new GameButton(getContext(), buttonImg, scoreBtnPos.x, scoreBtnPos.y) {

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
        score.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Point hit = new Point((int) e.getX(), (int) e.getY());
        if (start.isInHitbox(e.getX(), e.getY())) {
            start.onTouchEvent(e);
        } else if (options.isInHitbox(e.getX(), e.getY())) {
            options.onTouchEvent(e);
        } else if (score.isInHitbox(e.getX(), e.getY())) {
            score.onTouchEvent(e);
        }
        return true;
    }
}
