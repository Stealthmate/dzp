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
import android.view.View;

public class GameView extends View {

    public static Bitmap gameImage = Bitmap.createBitmap(480, 320, Bitmap.Config.ALPHA_8);

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(gameImage, 0, 0, new Paint());
    }

    
}
