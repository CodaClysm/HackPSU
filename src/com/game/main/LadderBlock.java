package com.game.main;

import java.awt.*;

public class LadderBlock extends GameObject {

    public LadderBlock(int x, int y, ID id) {
        super(x, y, id);
        height = 32.0f;
        width = 32.0f;
    }



    public void tick() {

    }


    public void render(Graphics g) {

        Color c = new Color(216, 192, 168);
        g.setColor(c);
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

