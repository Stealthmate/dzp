package com.dzp.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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
    public static int screenWidth, screenHeight;

    public static Object get(String id) {
        return resources.get(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        screenWidth = dimension.widthPixels;
        screenHeight = dimension.heightPixels;

        Bitmap bg = ((BitmapDrawable)getResources().getDrawable(R.drawable.screen2)).getBitmap();
        bg = getResizedBitmap(bg, screenWidth, screenHeight);
        TitleScreen ts = new TitleScreen(this, bg);
        this.setContentView(ts);
        
        //FrameLayout ll = (FrameLayout) View.inflate(this, R.layout.main, null);
        
        /*
        backgrounds[0] = ((BitmapDrawable) getResources().getDrawable(R.drawable.title_background));
        stfu = getResizedBitmap(backgrounds[0].getBitmap());
        backgrounds[1] = ((BitmapDrawable) getResources().getDrawable(R.drawable.title_background));
        loadResources();
        Paint paint = new Paint();
        setContentView(R.layout.title);
        gameScreen = new GameView(this);
        //this.setContentView(ll);

        GameView.gameImage = stfu;
        gameScreen.invalidate();
        gameScreen.setVisibility(View.VISIBLE);
        ll.addView(gameScreen);

        ll.invalidate();*/

    }

    public void change(View view) {
        /*current = 1 - current;
        stfu = getResizedBitmap(backgrounds[current].getBitmap());
        GameView.gameImage = stfu;
        gameScreen.invalidate();*/
        //ImageView img = (ImageView) findViewById(R.id.bg);
        //img.setBackgroundResource(R.drawable.bg_animation);
        //((AnimationDrawable)img.getBackground()).start();
        System.out.println("Go!");
    }

    private boolean loadResources() {
        resources.put(
                "titleBackground",
                ((BitmapDrawable) getResources().getDrawable(R.drawable.title_background)).getBitmap());

        return true;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int w, int h) {
        int bmwidth = bm.getWidth();
        int bmheight = bm.getHeight();
        float scaleWidth = ((float) w) / bmwidth;
        float scaleHeight = ((float) h) / bmheight;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, bmwidth, bmheight, matrix, false);
        return resizedBitmap;
    }
    int move = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        String DEBUG_TAG = "log";
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                move = 0;
                return true;
            case (MotionEvent.ACTION_MOVE): {
                Log.d(DEBUG_TAG, "MOVE!!");
                move++;
            }
            return true;
            case (MotionEvent.ACTION_UP): {
                Log.d(DEBUG_TAG, move + "");
                if (move < 5) {
                    change(null);
                }
                move = 0;

            }
            return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG, "Movement occurred outside bounds "
                        + "of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

}
