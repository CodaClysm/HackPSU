package com.game.main;

import java.awt.*;

public class LabelElements extends GameObject
{
    private Handler handler;
    private Player player;
    private String message;


    LabelElements(float x, float y, Handler handler, ID id, Player player, String string){
        super(x, y, id);
        this.handler = handler;
        this.player = player;
        this.message = string;
    }

    public void tick(){
        //set new positions
        //if player is moving right
        y = player.getY();
        x = player.getX();

    }
    public void render(Graphics g)
    {
        if( message.charAt(0) == 'H')
        {

            g.drawString(message, (int)x-10,(int) y-10);
        }
        else
        {
            g.drawString(message, (int)x-25, (int)y-25);
        }


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
    public void setMessage(String message) {
        this.message = message;
    }











}
