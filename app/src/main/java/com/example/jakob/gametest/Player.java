package com.example.jakob.gametest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.animation.Animation;

/**
 * Created by Jakob on 11-Nov-15.
 */
public class Player extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private double dx;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean playing;
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames)
    {
        x = 100;
        y = 50;
        dx = 0;
        score = 0;
        height = h;
        width = w;
        playing = true;

        spritesheet = res;
        startTime = System.nanoTime();
    }
    public Player(Bitmap res, int w, int h)
    {
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT-140;
        dx = 10;
        height = h;
        width = w;
        score = 0;

        spritesheet = res;
        startTime = System.nanoTime();
    }
    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }

        if(moveLeft)
            x -= dx;
        if(moveRight)
            x += dx;

        if(x>GamePanel.WIDTH-150)
            x = GamePanel.WIDTH-150;
        else if(x<10)
            x = 10;
    }
    public void updateMovement(int x)
    {
        if(x < GamePanel.WIDTH/2)
            moveLeft = true;
        else if(x > GamePanel.WIDTH/2)
            moveRight = true;
    }
    public void noMovement()
    {
            moveLeft = false;
            moveRight = false;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(spritesheet,x,y,null);
    }
    public boolean getPlaying()
    {
        return playing;
    }




}
