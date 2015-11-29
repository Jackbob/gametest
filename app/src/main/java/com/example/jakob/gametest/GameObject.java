package com.example.jakob.gametest;

import android.graphics.Rect;

/**
 * Basic coordinates and hitboxes for every gameobject
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    public void setX(int newX)
    {
        x = newX;
    }
    public void setY(int newY)
    {
        y = newY;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }


}
