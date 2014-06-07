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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import com.dzp.game.DZPgame;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public static Bitmap gameImage = Bitmap.createBitmap(480, 320, Bitmap.Config.ALPHA_8);
    private static Bitmap map;
    private final View exitBox;
    private final MenuBar menu;
    private SurfaceHolder holder;
    private Renderer renderer;
    private boolean RendererIsRunning = false;

    private class Renderer extends Thread {

        private final SurfaceHolder sh;
        private static final int fps = 40;

        public Renderer(SurfaceHolder sh) {
            this.sh = sh;
        }

        @Override
        public void run() {
            while (true) {
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
            renderer.destroy();
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

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameImage = (Bitmap) DZPgame.get(110);
        map = gameImage;

        this.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                zoomIn();
            }

        });
        this.setOnLongClickListener(new OnLongClickListener() {

            public boolean onLongClick(View view) {
                zoomOut();
                return true;
            }

        });

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
        canvas.drawBitmap(gameImage, 0, 0, paint);
        menu.draw(canvas);
    }

    private void zoomOut() {
        gameImage = DZPgame.getResizedBitmap(map, gameImage.getWidth() / 1.5f, gameImage.getHeight() / 1.5f);
    }

    private void zoomIn() {
        gameImage = DZPgame.getResizedBitmap(map, gameImage.getWidth() * 1.5f, gameImage.getHeight() * 1.5f);
    }

    public void showExitBox() {
        System.out.println("exit?");
    }

}
