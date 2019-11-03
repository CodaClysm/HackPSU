package com.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Player extends GameObject {

    private float gravity = 0.2f;
    private Handler handler;
    private int health;
    private Weapon weapon;
    private float damageMult;
    private int experience;
    private int healthTimer;
    private LabelElements healthLabel;
    private LabelElements expLabel;


    public Player () {}
    public Player(float x, float y, Handler handler, ID id) {
        super(x, y, id);
        this.handler = handler;
        height = 64;
        width = 32;
        weapon = new Weapon(x + width, y - height/2, handler, ID.Weapon, this);
        handler.object.add(weapon);
        damageMult = 1;
        experience = 0;
        health = 100;
        healthTimer = 0;
        healthLabel = new LabelElements((int)x, (int)y , handler, ID.LabelElements, this, "Health:" + Integer.toString(health));
        expLabel = new LabelElements((int)x, (int)y+20 , handler, ID.LabelElements, this, "Exp:" + Integer.toString(experience));
        handler.object.add(healthLabel);
        handler.object.add(expLabel);


    }

    public void tick(){
        x += velocityX;
        y += velocityY;
        healthTimer ++;
        if (healthTimer == 30)
        {
            if(health != 100)
            {
                health++;
            }
            healthTimer = 0;
        }
        if (falling || jumping)
        {
            velocityY += gravity;
        }
        checkCollision();
        if(weapon.attacking) {
            weapon.attacking = false;
            checkWeapCollision();
        }
        checkLevelUp();
        updateLabels();

    }
    private void updateLabels()
    {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.LabelElements) {

                healthLabel.setMessage("Health:" + Integer.toString(this.health));
                expLabel.setMessage("Experience:" + Integer.toString(this.experience) + "/100");
            }

        }
    }
    private void checkCollision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Block)
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
            else if(tempObject.getId() == ID.LadderBlock)
            {
                if(getBoundsTop().intersects(tempObject.getBounds()) ||
                        getBoundsRight().intersects(tempObject.getBounds()) ||
                        getBoundsLeft().intersects(tempObject.getBounds()))
                {
                //getBoundsBottom().intersects(tempObject.getBounds())
                            climbing = true;
                            jumping = false;
                            falling = false;
                            velocityY = 0;
                }
                //if bottom is clipping but not sides, it is resting on top of the ladder.
                //treat as if sitting on a block
                //define a new left and right bounds
                Rectangle right = getBoundsRight();
                Rectangle left = getBoundsLeft();
                right.setLocation((int)right.getX(), (int)right.getY()-5);
                left.setLocation((int)left.getX(), (int)left.getY()-5);
                if(!(right.intersects(tempObject.getBounds()) ||
                        left.intersects(tempObject.getBounds()))
                    && getBoundsBottom().intersects(tempObject.getBounds()))
                {
                    climbing = false;
                    if(!jumping)
                    {
                        velocityY = 0;
                        y = tempObject.getY() - height;
                    }
                    jumping = false;
                }

            }
            else if (tempObject.getId() == ID.Experience)
            {
                Experience expOrb = (Experience) tempObject;
                if(getBounds().intersects(expOrb.getBounds()))
                {
                    handler.removeObject(expOrb);
                    experience += expOrb.getValue();
                    System.out.println("Player has picked up expereience. Current exp: " + experience);
                }
            }
        }
    }

    private void checkWeapCollision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Enemy) {
                if (weapon.getBounds().intersects(tempObject.getBounds())) {
                    Enemy enemy = (Enemy)tempObject;
                    enemy.takeDamage((int)damageMult * weapon.getDamage());
                }
            }
        }
    }

    public void takeDamage(int damage)
    {
        this.health -=damage;
        if(this.health <= 0)
        {
            System.out.println("Player has died.");
            System.exit(1);
        }
        else
        {
            System.out.println("Current Health is: "+getHealth());
        }
    }
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.red);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsTop());

    }

    private void checkLevelUp()
    {
        if ((experience / 100) > 0 )
        {
            damageMult *= 2;
            experience %= 100;
        }
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
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
