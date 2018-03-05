package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import world.World;

/* 
 * gui packages main class Game
 *
 * Basicly we have 640x480 sized window in pixels and we can add objects to coordinates like 244x100
 * Main game loop handles all game-technic stuff. It's updates stuff 60 times/s (tick-rate) and renders out
 * something like 2k fps
 * Handler class iterates through list of all game objects and makes them tick and render with their own methods
 *
 * TODO game "camera", collision detection, collision with enemy triggers combat window, pictures for objects,
 * menu, high scores, saving/loadig, animations?
 * */

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    GameCamera cam;
    

    public Game() {
        new Window(1920, 1080, "Dungeons of Räntämäki", this); 
        cam = new GameCamera(this.HEIGHT, this.WIDTH);
        start();
        handler = new Handler();
        handler.loadLevel();
        this.addKeyListener(new KeyInput(handler));
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* Main game loop
     * It's pretty advanced, made with help of lots gamemaking-tutorials, so that game speed is 
     * good and not varying with different machines with different specs
     * More info about how it work's: http://www.java-gaming.org/index.php?topic=24220.0
     * */

    public void run(){
        this.requestFocus();

        long lastTime = System.nanoTime();
        double ticks = 60.0;
        double ns = 1000000000 / ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + ticks);
                frames = 0;
            }
        }
        stop();
    }

    /* Updating stuff to game */

    public void tick() {
        handler.tick();
        for (GameObject go : handler.objects) {
        	if (go.getClass() == Player.class) {
        		cam.tick(go);
        	}
        }
    }

    /* Render method */

    public void render() {
        BufferStrategy bs = this.getBufferStrategy(); 
        Toolkit.getDefaultToolkit().sync(); 
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /*////////////////////////////////////
         *Draw here 
         *////////////////////////////////////
        g.translate(cam.getX(), cam.getY());
        g.setColor(Color.white);
        g.fillRect(0, 0, 1920, 1080);
        handler.render(g);
        //////////////////////////////////////
        g.dispose();
        bs.show();
    }

    /* Here we generate level with the world.World */

    

    /* Main method */

    public static void main(String[] args) {
        new Game();
    }
}
