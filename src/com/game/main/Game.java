package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.awt.Window;
//import java.util.logging.Handler;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 1024, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;

    private BufferedImage level;

    public Game()
    {
        new Window(WIDTH, HEIGHT, "HackPSU Game", this);
        handler = new Handler();

        BufferedImageLoader loader = new BufferedImageLoader();
        handler.addObject(new Player(100, 100, ID.Player));
        handler.addObject(new Player(200, 200, ID.Player));
        level = loader.loadImage("/res/level1.png"); // loading the level
        LoadImageLevel(level);
        handler.createLevel();

    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop()
    {
        try{
            thread.join();
            running = false;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta > 1)
            {
                tick();
                delta--;
            }
            if(running)
            {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer >1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
    private void LoadImageLevel(BufferedImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight();

        System.out.println("width, height: " + w + " " + h);


        for(int i = 0; i < h; i++)
        {
            for(int j = 0; j < w; j++)
            {

                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue  = (pixel) & 0xff;

                if(red == 255 && green == 255 && blue == 255)
                {
                    handler.addObject(new Block(i * 32, j * 32, ID.Block));
                }

            }
        }





    }
    private void tick()
    {
       handler.tick();
    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

       handler.render(g);

        g.dispose();
        bs.show();

    }
    public static void main(String args[])
    {
        new Game();
    }
}
