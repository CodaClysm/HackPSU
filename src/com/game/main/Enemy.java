package com.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Enemy extends GameObject {

    private float gravity = 0.2f;
    private Handler handler;
    int health;
    int swingTimer;

    public Enemy () {}
    public Enemy(float x, float y, Handler handler, ID id) {
        super(x, y, id);
        this.handler = handler;
        height = 64;
        width = 32;
        health = 100;
        swingTimer = 0;
    }

    public void tick(){
        x += velocityX;
        y += velocityY;
        if (falling || jumping)
        {
            velocityY += gravity;
        }
        checkCollision();
        pursuePlayer();
        swingTimer++;
    }
    public void dealDamage(int damage)
    {
        health = health - damage;
        if(health < 0)
        {
            handler.removeObject(this);
        }
    }
    private void pursuePlayer()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player)
            {
                if(this.x < tempObject.getX())
                {
                    this.setVelocityX(1);
                }
                else if(this.x > tempObject.getX())
                {
                    this.setVelocityX(-1);
                }
            }
        }

    }


    private void checkCollision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block || tempObject.getId() == ID.LadderBlock)
            {
                if(getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    climbing = false;
                    if(!jumping)
                    {
                        velocityY = 0;
                        y = tempObject.getY() - height;
                    }
                    jumping = false;
                }
                else
                {
                    falling = true;
                }
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getY() + tempObject.height;
                    velocityY = 0;
                }
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    x = tempObject.getX() - width;
                    velocityX = 0;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    x = tempObject.getX() + width;
                    velocityX = 0;
                }
            }
            else if(tempObject.getId() == ID.Player)
            {

                if(tempObject.getBounds().intersects(this.getBounds()))
                {
                    if(swingTimer > 60)
                    {
                        swingTimer = 0;
                        System.out.println("Damage dealt to player!");
                    }

                }
            }

        }
    }
    public void render(Graphics g)
    {

        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.white);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());
    }

    public Rectangle getBoundsBottom()
    {
        // return new Rectangle((int)((int)x +(width/2)-((width/2)/2)), (int)y + (int)(height/2), (int)width/2, (int)(height/2));
        return new Rectangle((int)((int)x +(width/2)-((width/2)/2)), (int)y + (int)(height/2), (int)width/2, (int)(height/2));
    }
    public Rectangle getBoundsTop()
    {
        return new Rectangle((int)((int)x +(width/2)-((width/2)/2)), (int)y + 5, (int)width/2, (int)(height/2)-10);
    }
    public Rectangle getBoundsRight()
    {
        return new Rectangle((int)x+(int)width-5, (int)y+5, 5, (int)height-10);
    }
    public Rectangle getBoundsLeft()
    {
        return new Rectangle((int)x, (int)y+5, 5, (int)height-10);
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width ,(int)height);
    }
}
