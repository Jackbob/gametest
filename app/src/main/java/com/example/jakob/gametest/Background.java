package com.example.jakob.gametest;

/**
 * Created by Jakob on 11-Nov-15.
 */


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

public class Background {

    private Bitmap image;
    private int x,y;

    public Background(Bitmap res)
    {
        image = Bitmap.createScaledBitmap(res, GamePanel.WIDTH, GamePanel.HEIGHT, true);
    }

    public void update()
    {
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image,x,y,null);
    }


}
