package com.game.main;


import java.awt.*;

public class Block extends GameObject {

    public Block(int x, int y, ID id) {
        super(x, y, id);
         height = 32.0f;
         width = 32.0f;
    }



    public void tick() {

    }


    public void render(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        g.fillRect((int)x, (int)y, 32, 32);
        g.drawRect((int)x, (int)y, 32, 32);
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32 ,32);
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



}
