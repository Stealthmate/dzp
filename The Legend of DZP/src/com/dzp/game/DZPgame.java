package com.dzp.game;

import com.dzp.game.gui.TitleScreen;
import com.dzp.game.gui.GameView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import java.util.HashMap;

public class DZPgame extends Activity {

    //Resource IDs
    public static final int TITLE_BACKGROUND = 1;
    public static final int START_BTN_IMG = 2;
    public static final int OPTIONS_BTN_IMG = 3;
    public static final int ABOUT_BTN_IMG = 4;
    
    private ImageView image;
    private final BitmapDrawable[] backgrounds = new BitmapDrawable[2];
    private static final HashMap<Integer, Object> resources = new HashMap();
    private GameView gameScreen;
    private Bitmap stfu;
    public static int screenWidth, screenHeight;

    public static Object get(int id) {
        return resources.get(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadResources();
        DisplayMetrics dimension = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dimension);
        screenWidth = dimension.widthPixels;
        screenHeight = dimension.heightPixels;

        Bitmap bg = (Bitmap) get(TITLE_BACKGROUND);
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
                TITLE_BACKGROUND,
                ((BitmapDrawable) getResources().getDrawable(R.drawable.title_background)).getBitmap());
        resources.put(START_BTN_IMG,
                ((BitmapDrawable) getResources().getDrawable(R.drawable.start_btn_img)).getBitmap());
        resources.put(OPTIONS_BTN_IMG,
                ((BitmapDrawable) getResources().getDrawable(R.drawable.options_btn_img)).getBitmap());
        resources.put(ABOUT_BTN_IMG,
                ((BitmapDrawable) getResources().getDrawable(R.drawable.about_btn_img)).getBitmap());
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

}
