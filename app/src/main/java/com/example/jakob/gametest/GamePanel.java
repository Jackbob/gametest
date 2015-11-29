package com.example.jakob.gametest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Jakob on 04-Nov-15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread thread;
    private Background bg;
    private Player player;
    private EnemyCircle circle;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;


    public GamePanel(Context context)
    {
        super(context);

        //Add callback to surfaceholder to handle events
        getHolder().addCallback(this);

        //Create a thread which the game runs on
        thread = new MainThread(getHolder(), this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;

        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException i){i.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){


        bg = new Background (BitmapFactory.decodeResource(getResources(), R.drawable.background));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.playermodel), 50, 50);
        circle = new EnemyCircle(BitmapFactory.decodeResource(getResources(), R.drawable.bluguy), BitmapFactory.decodeResource(getResources(), R.drawable.bluguy), 200, 200);
        //Safely start the game loop
        thread.setRunning(true);
        thread.start();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //If screen is pressed then move character
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            player.updateMovement((int)event.getX());
            return true;
        }
        //If screen is released then stop character movement
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.noMovement();
            return true;
        }

        return super.onTouchEvent(event);
    }

    //Update everything every frame
    public void update()
    {
        bg.update();
        player.update();
        circle.update();
    }

    //Draw everything every frame
    @Override
    public void draw(Canvas canvas)
    {
        bg.draw(canvas);
        player.draw(canvas);
        circle.draw(canvas);
    }

}
