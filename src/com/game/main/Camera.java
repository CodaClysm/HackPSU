package com.game.main;

public class Camera
{

    private float x, y;


    public Camera(float x, float y)
    {
        this.x = x;
        this.y = y;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(GameObject player)
    {
        float xTarg = -player.getX() + Game.WIDTH/2;
        x += (xTarg - x) * .05f;
        float yTarg = -player.getY() + Game.HEIGHT/2;
        y += (yTarg - y) * .05f;
    }





}
