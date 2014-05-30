package com.dzp.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.HashMap;

public class DZPgame extends Activity {

    /**
     * Called when the activity is first created.
     */
    private ImageView image;
    private int current = 0;
    private final BitmapDrawable[] backgrounds = new BitmapDrawable[2];
    private static final HashMap<String, Object> resources = new HashMap();
    private GameView gameScreen;
    private Bitmap stfu;
    private int width, height;

    public static Object get(String id) {
        return resources.get(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameScreen = new GameView(this);
        this.setContentView(gameScreen);

        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        width = dimension.widthPixels;
        height = dimension.heightPixels;
        
        backgrounds[0] = ((BitmapDrawable) getResources().getDrawable(R.drawable.screen1));
        stfu = getResizedBitmap(backgrounds[0].getBitmap());
        backgrounds[1] = ((BitmapDrawable) getResources().getDrawable(R.drawable.screen2));
        loadResources();
        Paint paint = new Paint();

        GameView.gameImage = stfu;
        gameScreen.invalidate();

        

    }

    public void change(View view) {
        current = 1 - current;
        stfu = getResizedBitmap(backgrounds[current].getBitmap());
        GameView.gameImage = stfu;
        gameScreen.invalidate();
    }

    private boolean loadResources() {
        resources.put(
                "titleBackground",
                ((BitmapDrawable) getResources().getDrawable(R.drawable.title_background)).getBitmap());

        return true;
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        int bmwidth = bm.getWidth();
        int bmheight = bm.getHeight();
        float scaleWidth = ((float) width) / bmwidth;
        float scaleHeight = ((float) height) / bmheight;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        System.out.println(bmwidth);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, bmwidth, bmheight, matrix, false);
        return resizedBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        change(null);
        return super.onTouchEvent(event);
    }

}
