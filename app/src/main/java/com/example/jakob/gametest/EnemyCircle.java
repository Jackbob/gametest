package com.example.jakob.gametest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Jakob on 18-Nov-15.
 */
public class EnemyCircle extends GameObject{

    Bitmap spritesheet, spritesheetmove;

    private static final double time = System.currentTimeMillis();

    public EnemyCircle(Bitmap res, Bitmap res2, int w, int h)
    {
        spritesheetmove = Bitmap.createScaledBitmap(res, 200, 200, true);
        spritesheet = Bitmap.createScaledBitmap(res, 200, 200, true);
        width = w;
        height = h;
        if(Math.random() < 0.5) {
            x = GamePanel.WIDTH - 200;
            dx = -10;
        }
        else {
            x = 31;
            dx = 10;
        }
        dy = -15;
        y = (int)(Math.random()*(GamePanel.HEIGHT - 250));



    }
    public void update()
    {
        if(System.currentTimeMillis() - time >  500)
        {
            spritesheet = spritesheetmove;
            if(x < 30)
                dx = -dx;
            if(x > GamePanel.WIDTH - 200)
                dx = -dx;
            if(y < 0)
                dy = -dy;
            if(y > GamePanel.HEIGHT - 250)
                dy = -dy;

            x += dx;
            y += dy;
        }
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(spritesheet,x,y,null);
    }

}
