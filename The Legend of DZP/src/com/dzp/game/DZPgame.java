package com.dzp.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.dzp.game.gui.GameView;
import com.dzp.game.resourceHandler.CurrentGame;
import com.dzp.game.resourceHandler.GameLevel;
import com.dzp.game.resourceHandler.GameWorld;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DZPgame extends Activity{

    //Resource IDs
    public static final int DIMENSIONS = 5;
    public static final int LEVELS = 4;
    //up to 600 reserved
    public static final int DZP_ICON = 1001;
    public static final int EXIT_BOX_IMG = 1002;

    private ImageView image;
    public static boolean inGame = false;
    private static final HashMap<Integer, Object> resources = new HashMap();
    private GameView game;
    private static ArrayList<GameWorld> worlds = new ArrayList();
    public static int screenWidth, screenHeight;
    private int dim = 1, lvl = 1;

    public static Object get(int id) {
        return resources.get(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
        loadResources();
        this.setContentView(R.layout.title_screen);
        initializeButtons();
    }

    public void change(View view) {
        System.out.println("Go!");
    }

    private boolean loadResources() {
        int wid, lid;

        for (int i = 0; i <= 0; i++) {
            wid = getResources().getIdentifier("skin" + (i + 1), "drawable", getPackageName());
            resources.put((i + 1), ((BitmapDrawable) getResources().getDrawable(wid)).getBitmap());
            for (int j = 0; j <= LEVELS - 1; j++) {
                System.err.println("dim" + (i + 1) + "_map" + (j + 1));
                lid = getResources()
                        .getIdentifier("dim" + (i + 1) + "_map" + (j + 1), "array", getPackageName());
                String[] mapConfig = getResources().getStringArray(lid);
                resources.put((i + 1) * 100 + j + 1, mapConfig);
                
                lid = getResources()
                        .getIdentifier("dim" + (i + 1) + "_map" + (j + 1), "drawable", getPackageName());
                Bitmap map = ((BitmapDrawable)getResources().getDrawable(lid)).getBitmap();
                resources.put((i+1)*100 + (j+1)*10, map);
            }
        }

        ArrayList<GameLevel> gl = new ArrayList();
        try {
            gl.add(new GameLevel((Bitmap) get(11), (String[]) get(101)));
            worlds.add(new GameWorld((Bitmap) get(1), gl));
        } catch (Exception ex) {
            Logger.getLogger(DZPgame.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, float w, float h) {
        int bmwidth = bm.getWidth();
        int bmheight = bm.getHeight();
        float scaleWidth = w / bmwidth;
        float scaleHeight =  h / bmheight;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, bmwidth, bmheight, matrix, false);
        return resizedBitmap;
    }
    int move = 0;

    private void initializeButtons() {
        Button start = (Button) findViewById(R.id.start_btn);
        start.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                startGame();
                //DZPgame.this.setContentView(R.layout.screen_start);
            }

        });
        Button options = (Button) findViewById(R.id.options_btn);
        options.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                DZPgame.this.setContentView(R.layout.screen_options);
            }

        });
        Button about = (Button) findViewById(R.id.about_btn);
        about.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                System.out.println("About");
                DZPgame.this.setContentView(R.layout.screen_about);
            }

        });
    }

    private void startGame() {
        try {
            CurrentGame.createGame(worlds.get(0), worlds.get(0).getLevel(0));
            game = new GameView(this);
            this.setContentView(game);
            inGame = true;
        } catch (IOException ex) {
            Logger.getLogger(DZPgame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        if (inGame) {
            game.showExitBox();
        }
    }

}
