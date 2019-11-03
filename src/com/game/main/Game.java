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
    Camera cam;

    private BufferedImage level;
    BufferedImageLoader loader = new BufferedImageLoader();



    public Game()
    {
        new Window(WIDTH, HEIGHT, "HackPSU Game", this);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        cam = new Camera(0,0);


        level = loader.loadImage("/res/level1.png"); // loading the level
        LoadImageLevel(level);
        handler.createLevel();
        BufferedImage lightRad = loader.loadImage("/res/light-radius.png");

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
                if(red == 0 && green == 0 && blue == 255)
                {
                    handler.addObject(new Player(i * 32, j *32 , handler, ID.Player));
                }
                if(red == 0 && green == 255 && blue == 0)
                {
                    handler.addObject(new LadderBlock( i * 32, j * 32, ID.LadderBlock));
                }
                if(red == 255 & green == 0 && blue == 0)
                {
                    handler.addObject(new Enemy(i*32, j*32, handler, ID.Enemy));
                }
                if(red == 255 & green == 0 && blue == 255)
                {
                    handler.addObject(new FlyingEnemy(i*32, j*32, handler, ID.FlyingEnemy));
                }
            }
        }
    }
    private void tick()
    {
       handler.tick();
       for(int i = 0; i<handler.object.size(); i++)
       {
           if(handler.object.get(i).getId() == ID.Player)
           {
               cam.tick(handler.object.get(i));
           }
       }
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

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);
       // g2d.drawImage(lightRad, null,(int)+512, (int)y-256);

        //Beginning of camera
        g2d.scale(1.5, 1.5);
        g2d.translate(cam.getX()-180, cam.getY()-180);
        handler.render(g);
        //End of camera

        g2d.translate(-cam.getX(), -cam.getY());

        g.dispose();
        bs.show();
    }

    public static void main(String args[])
    {
        new Game();
    }

}
