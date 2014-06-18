/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dzp.game.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.dzp.game.DZPgame;

public class GameView extends SurfaceView implements
        SurfaceHolder.Callback {

    public static Bitmap gameImage = Bitmap.createBitmap(480, 320, Bitmap.Config.ALPHA_8);
    private static ImageView screen;
    private static Bitmap map;
    private final View exitBox;
    private final MenuBar menu;
    private SurfaceHolder holder;
    private Renderer renderer;
    private boolean RendererIsRunning = false;
    private boolean over = false;
//<editor-fold desc="Renderer, MenuBar and Surface functions">

    private class Renderer extends Thread {

        private final SurfaceHolder sh;
        private static final int fps = 40;

        public Renderer(SurfaceHolder sh) {
            this.sh = sh;
        }

        @Override
        public void run() {
            while (!over) {
                synchronized (sh) {
                    Canvas c = sh.lockCanvas();
                    if (c != null) {
                        c.drawColor(Color.BLACK);
                        onDraw(c);
                        sh.unlockCanvasAndPost(c);
                    }
                }
            }
        }

    }

    public void surfaceCreated(SurfaceHolder sh) {
        renderer = new Renderer(sh);
        RendererIsRunning = true;
        renderer.start();
    }

    public void surfaceChanged(SurfaceHolder sh, int i, int i1, int i2) {

    }

    public void surfaceDestroyed(SurfaceHolder sh) {
        if (RendererIsRunning) {
            over = true;
        }
    }

    private class MenuBar extends View {

        private final Rectangle space;

        private Bitmap dzpIcon;
        private final Bitmap background;
        private volatile int dzp;
        private final ImageButton exit;

        public MenuBar(Context context, Rectangle space) {
            super(context);
            this.space = space;
            this.background = DZPgame.getResizedBitmap((Bitmap) DZPgame.get(1), space.width, space.height);
            exit = new ImageButton(context);
            exit.setImageBitmap((Bitmap) DZPgame.get(DZPgame.DZP_ICON));
            exit.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    System.out.println("Exit");
                    showExitBox();
                }

            });
            exit.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(background, space.x, space.y, paint);
        }

    }
//</editor-fold>
    private float x = 0, y = 0;

    private class GestureListener extends SimpleOnGestureListener {

        private static final float slideSpeed = 8;
        private static final int box = 5;
        private float xSrc;
        private float ySrc;

        @Override
        public boolean onDown(MotionEvent me) {
            System.out.println("Down");
            xSrc = me.getX();
            ySrc = me.getY();
            prevDX = 0;
            prevDY = 0;
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent me) {
            System.out.println("SingleTapUp");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent me) {
            System.out.println("long");
            zoomOut();
        }

        @Override
        public boolean onDoubleTap(MotionEvent me) {
            System.out.println("wtf");
            zoomIn();
            return true;
        }

        float prevDX, prevDY;

        @Override
        public boolean onScroll(MotionEvent src, MotionEvent me, float dx, float dy) {

            //System.out.println(GameView.this.getWidth());
            float x = me.getX();
            float y = me.getY();
            float newX = GameView.this.x, newY = GameView.this.y;
            //System.out.println(gameImage.getWidth());
            if ((dx < -box || dx > box)
                    && (-dx + newX <= 0 && -dx + newX + gameImage.getWidth() >= GameView.this.getWidth())) {
                newX -= dx;
                prevDX = dx;
            }

            if ((dy < -box || dy > box)
                    && (-dy + newY <= 0 && -dy + newY + gameImage.getHeight() >= GameView.this.getHeight()))  {
                newY -= dy;
                prevDY = dy;
            }
            GameView.this.x = newX;
            GameView.this.y = newY;
            //System.err.println(newX + " " + newY);
            GameView.this.invalidate();

            return true;
        }
    }

    private final GestureListener listener;
    private final GestureDetector detector;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameImage = (Bitmap) DZPgame.get(110);

        listener = new GestureListener();
        detector = new GestureDetector(context, listener);
        this.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View view, MotionEvent me) {
                return detector.onTouchEvent(me);
            }

        });

        map = gameImage;
        screen = new ImageView(context) {

            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawBitmap(gameImage, GameView.this.x, GameView.this.y, paint);
            }
        };
        screen.setImageBitmap(gameImage);

        exitBox = new View(context) {
            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawBitmap(
                        (Bitmap) DZPgame.get(DZPgame.EXIT_BOX_IMG),
                        DZPgame.screenWidth / 2 - this.getWidth() / 2,
                        DZPgame.screenHeight / 2 - this.getHeight() / 2, paint);
            }
        };
        menu = new MenuBar(context, new Rectangle(100, 10, 280, 40));
    }

    Paint paint = new Paint();
    int selected;

    @Override
    protected void onDraw(Canvas canvas) {
        screen.draw(canvas);
        menu.draw(canvas);
    }

    private void zoomOut() {
        if(gameImage.getWidth()/1.5f<this.getWidth() || gameImage.getHeight()/1.5f<this.getHeight()) return;
        gameImage = DZPgame.getResizedBitmap(map, gameImage.getWidth() / 1.5f, gameImage.getHeight() / 1.5f);
        this.x = 0;
        this.y = 0;
    }

    private void zoomIn() {
        if(gameImage.getWidth()*1.5f>this.getWidth()*2 || gameImage.getHeight()*1.5f>this.getHeight()*2) return;
        gameImage = DZPgame.getResizedBitmap(map, gameImage.getWidth() * 1.5f, gameImage.getHeight() * 1.5f);
        
    }

    public void showExitBox() {
        System.out.println("exit?");
    }

}
