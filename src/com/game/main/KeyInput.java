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

            if(tempObject.getId() == ID.Weapon)
            {
                Weapon tempWeapon = (Weapon) tempObject;
                if (key == KeyEvent.VK_SPACE)
                {
                    if(!tempWeapon.attacking) {
                        if (tempWeapon.isPointingLeft()) {
                            tempWeapon.x += 16;
                        } else {
                            tempWeapon.x -= 16;
                        }
                        tempWeapon.attacking = true;
                    }
                }
            }
            if(tempObject.getId() == ID.Player)
            {

                if (key == KeyEvent.VK_D)
                {
                    tempObject.setVelocityX(5);

                }
                if (key == KeyEvent.VK_A)
                {
                    tempObject.setVelocityX(-5);

                }
                if (key == KeyEvent.VK_W && !tempObject.isJumping())
                {

                    tempObject.setVelocityY(-4);
                    tempObject.setJumping(true);
                }
                if(key == KeyEvent.VK_S && tempObject.isClimbing())
                {
                    tempObject.setVelocityY(4);
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
            if(tempObject.getId() == ID.Weapon)
            {
                Weapon tempWeapon = (Weapon) tempObject;
                if (key == KeyEvent.VK_SPACE)
                {
                    System.out.println("End Attack!");
                    if(tempWeapon.isPointingLeft())
                    {
                        tempWeapon.x -= 16;
                    }
                    else
                    {
                        tempWeapon.x += 16;
                    }
                    tempWeapon.attacking = false;
                }
            }
        }

    }
}
