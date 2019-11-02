package com.game.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();


    public void tick() {

        //Loops through every gameobject in the game.
        for(int i = 0; i< object.size(); i++)
        {
            //tempObject gets the ith object in the list.
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }


    }
    public void render(Graphics g)
    {
        for(int i=0; i< object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);

    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
