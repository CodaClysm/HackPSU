package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectInputStream;

public class KeyInput extends KeyAdapter {
    Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i<handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelocityX(5);
                }
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelocityX(-5);
                }
                if(key == KeyEvent.VK_SPACE && !tempObject.isJumping())
                {
                    System.out.println("JUMP");
                    tempObject.setVelocityY(-4);
                    tempObject.setJumping(true);
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE)
        {
            System.exit(1);
        }
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i<handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player)
            {
                if(key == KeyEvent.VK_D) tempObject.setVelocityX(0);
                if(key == KeyEvent.VK_A) tempObject.setVelocityX(0);

            }
        }
    }
}
