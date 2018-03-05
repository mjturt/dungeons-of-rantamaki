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
    private GameCamera gc;

    public Game() {
        new Window(800, 640, "Dungeons of Räntämäki", this); 
        start();

        handler = new Handler();
        gc = new GameCamera(0, 0);
        this.addKeyListener(new KeyInput(handler));

        /* Adding objects to game */

        
        loadLevel();
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
                frames = 0;
            }
        }
        stop();
    }

    /* Updating stuff to game */

    public void tick() {

        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                gc.tick(handler.objects.get(i));
            }
        }

        handler.tick(); 
    }

    /* Render method */

    public void render() {
        BufferStrategy bs = this.getBufferStrategy(); 
        Toolkit.getDefaultToolkit().sync(); 
        if (bs == null) {
            /* 2 frames preloaded in buffer and one shown, "Triplebuffering" */
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        /* Rendering stuff to screen */


        /* Background first */
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 800, 640);

        /* And then objects */

        g2d.translate(-gc.getX(), -gc.getY());
        handler.render(g);
        g2d.translate(gc.getX(), gc.getY());

        g.dispose();
        bs.show();
    }

    /* Here we generate level with the world.World */

    public void loadLevel() {

        World world = new World(10, 10);
        int w = world.getHeight();
        int h = world.getWidth();
        int[] start = world.getStart();
        int[] goal = world.getGoal();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (world.getTile(x, y).getPassable() == false) {
                    handler.addObject(new Block(x*64, y*64, ID.Block));
                }
                if(start[0] == y && start[1] == x) {
                    handler.addObject(new Player(x*64, y*64, ID.Player, handler));
                }
                if(goal[0] == y && goal[1] == x) {
                    handler.addObject(new Goal(x*64, y*64, ID.Goal));
                }
                if(world.getTile(x, y).hasMonster() == true) {
                    handler.addObject(new Monster(x*64, y*64, ID.Monster));
                }
            }
        }
    }

    /* Main method */

    public static void main(String[] args) {
        new Game();
    }
}
