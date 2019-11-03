package com.game.main;

import java.awt.*;

public class Weapon extends GameObject
{
    private Handler handler;;
    private float height, width;
    private int damage;


    private Player player;
    private boolean pointingLeft;
    public boolean attacking;

    Weapon(float x, float y, Handler handler, ID id, Player player){
        super(x, y, id);
        this.handler = handler;
        height = 4;
        width = 32;
        damage = 25;
        this.player = player;
        pointingLeft = true;
        attacking = false;
    }

    public void tick(){
        //set new positions
        //if player is moving right
        y = player.getY()+(player.getHeight()/2);
        if(player.getVelocityX() > 0)
        {
            x = player.getX() + player.getWidth();
            pointingLeft = true;
        }
        else if(player.getVelocityX() < 0)
        {
            x = player.getX() - player.getWidth();
            pointingLeft = false;
        }
    }
    public void render(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width ,(int)height);
    }
    public Rectangle getBoundsBottom()
    {
        return null;
    }
    public Rectangle getBoundsTop()
    {
        return null;
    }
    public Rectangle getBoundsRight()
    {
        return null;
    }
    public Rectangle getBoundsLeft()
    {
        return null;
    }

    public boolean isPointingLeft()
    {
        return pointingLeft;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }









}
