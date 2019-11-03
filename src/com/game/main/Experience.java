package com.game.main;

import java.awt.*;

public class Experience extends GameObject {
    private int value;
    private Handler handler;
    private float gravity;
    Experience(float x, float y, Handler handler, ID id, int value){
        super(x, y, id);
        this.handler = handler;
        height = 4;
        width = 4;
        this.value = value;
        gravity = 0.01f;
    }
    public void tick(){
        //todo add gavity at some point
        y += velocityY;
        if (falling)
        {
            velocityY += gravity;
        }
        checkCollision();
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
    public void checkCollision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block || tempObject.getId() == ID.LadderBlock)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    velocityY = 0;
                    y = tempObject.getY() - height;
                    climbing = false;
                }
            }
        }
    }

    public int getValue(){
        return value;
    }
}
