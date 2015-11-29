package com.example.jakob.gametest;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by Jakob on 04-Nov-15.
 */
public class MainThread extends Thread {

    private static final int FPS_CAP = 30;
    private float avgFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public Canvas canvas;

    public MainThread (SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS_CAP;

        while(running)
        {
            startTime = System.nanoTime();

            canvas = null;

            //Try locking the canvas for editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    //Update the game every frame
                    this.gamePanel.update();
                    //Draw the game every frame
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally {
                if (canvas != null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            /*********************************************************
             ***********         LOCK FPS          *******************
             **********************************************************/

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(waitTime);
            }catch (Exception e){}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS_CAP)
            {
                avgFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgFPS);
            }
        }
    }

    public void setRunning(boolean b)
    {
        running = b;
    }
}
