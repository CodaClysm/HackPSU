package com.game.main;

import java.awt.*;

public abstract class GameObject {

    protected float x,y;
    protected float width;
    protected float height;
    protected ID id;

    protected float velocityX, velocityY;

    protected boolean falling = true;
    protected boolean jumping = false;

    public GameObject()
    {
        this.x = 0;
        this.y = 0;
        this.id = null;
    }
    public GameObject(float x, float y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //Abstract functions must be implemented in the classes that inherit from GameObject
    public abstract void tick();
    public abstract void render(Graphics g);

    public float getX() {return x;}
    public void setX(float x) {this.x = x;}

    public float getY() {return y;}
    public void setY(float y) {this.y = y; }

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }

    public float getVelocityX() {return velocityX;}
    public void setVelocityX(float velocityX) {this.velocityX = velocityX; }

    public float getVelocityY() {return velocityY;}
    public void setVelocityY(float velocityY) {this.velocityY = velocityY;}

    public boolean isFalling() { return falling; }
    public void setFalling(boolean falling) {this.falling = falling; }

    public boolean isJumping() {return jumping; }
    public void setJumping(boolean jumping) {this.jumping = jumping; }
    public float getWidth() {return width;}

    public void setWidth(float width) {this.width = width;}

    public float getHeight() {return height;}

    public void setHeight(float height) {this.height = height;}

    public Rectangle getBounds(){return null;}
    public Rectangle getBoundsBottom() {return null;}
    public Rectangle getBoundsLeft(){return null;}
    public Rectangle getBoundsRight(){return null;}
    public Rectangle getBoundsTop(){return null;}




}
