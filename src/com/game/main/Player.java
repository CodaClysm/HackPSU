package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float gravity = 0.05f;
    private Handler handler;

    public Player () {};
    public Player(float x, float y, Handler handler, ID id) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(){
        x += velocityX;
        y += velocityY;
        if (falling || jumping)
        {
            velocityY += gravity;
        }
        collision(handler.object);
    }

    private void collision(LinkedList<GameObject> object)
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block)
            {
                if(getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getY() - height;
                    velocityY = 0;
                    falling = false;
                    jumping = false;
                }
                if(getBoundsTop().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getY() + height;
                    velocityY = 0;
                    falling = false;
                    jumping = false;
                }
                if(getBoundsRight().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getX() - width;
                    velocityX = 0;
                    falling = false;
                    jumping = false;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds()))
                {
                    y = tempObject.getX() + width;
                    velocityX = 0;
                    falling = false;
                    jumping = false;
                }
            }
        }
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, (int)width, (int)height);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());
    }

    public Rectangle getBoundsBottom()
    {
        return new Rectangle((int)((int)x +(width/2)-((width/2)/2)), (int)y + (int)(height/2) + 5, (int)width/2, (int)(height/2)-10);
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



}
