package com.dzp.game;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DZPgame extends Activity {

    /**
     * Called when the activity is first created.
     */
    
    private ImageView image;
    private int current = 0;
    private final BitmapDrawable[] backgrounds = new BitmapDrawable[2];

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        backgrounds[0] = ((BitmapDrawable)getResources().getDrawable(R.drawable.screen1));
        backgrounds[1] = ((BitmapDrawable)getResources().getDrawable(R.drawable.screen2));
        
        /*image = (ImageView) findViewById(R.id.background);
        image.setImageBitmap(backgrounds[current]);
        image.bringToFront();*/
        
    }

    public void change(View view) {
        LinearLayout l = (LinearLayout) findViewById(R.id.lay);
        current=1-current;
        l.setBackgroundDrawable(backgrounds[current].getCurrent());
    }
}
