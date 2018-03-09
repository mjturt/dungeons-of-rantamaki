package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import world.World;
/* 
 * gui packages main clablocksheet Game
 *
 * Basicly we have 640x480 sized window in pixels and we can add objects to coordinates like 244x100
 * Main game loop handles all game-technic stuff. It's updates stuff 60 times/s (tick-rate) and renders out
 * something like 2k fps
 * Handler clablocksheet iterates through list of all game objects and makes them tick and render with their own methods
 */

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private GameCamera cam;

    private SpriteSheet blocksheet;
    private BufferedImage blocksheetImg = null;
    private SpriteSheet playersheet;
    private BufferedImage playersheetImg = null;

    private BufferedImage road = null;

    public Game(int x, int y) {
        Window w = new Window(x, y, "Dungeons of Räntämäki", this); 
        cam = new GameCamera(x, y, w.getWidth(), w.getHeigth());
        start();
        handler = new Handler(w.getFrame());
        this.addKeyListener(new KeyInput(handler));

        ImageLoader loader = new ImageLoader();
        blocksheetImg = loader.loadImage("/blocksheet.png");
        blocksheet = new SpriteSheet(blocksheetImg);
        playersheetImg = loader.loadImage("/playersheet.png");
        playersheet = new SpriteSheet(playersheetImg);

        road = blocksheet.grabImage(2, 2, 64, 64);
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

    /*
     * Main game loop
     */

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
        this.requestFocus();
        for (int i=0;i<handler.objects.size();i++) {
        	if (handler.objects.get(i).getId() == ID.Player) {
        		cam.tick(handler.objects.get(i));
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
        
        g2d.translate(-cam.getX(), -cam.getY());

        for (int x = 0; x < 41 * 64; x += 64) {
            for (int y = 0; y < 41 * 64; y+=64) {
                g.drawImage(road, x, y, null);
            }
        }
        try {
        	handler.render(g);
        } catch (NullPointerException npe) {
        	throw new IllegalStateException("Something went wrong, most likely trying to render a null value", npe);
        }
        

        //////////////////////////////////////
        g2d.translate(cam.getX(), cam.getY());

        g.dispose();
        bs.show();
    }

    /**
     * Generates the level based on a recursive back-track maze generator in world.World.
     */

    public void loadLevel() {

        World world = new World(41, 41);
        int w = world.getHeight();
        int h = world.getWidth();
        int[] start = world.getStart();
        int[] goal = world.getGoal();
        
        /*
         * Creates new objects depending on the state of the Tile object in the World array.
         */

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (!world.getTile(y, x).getPassable()) {
                    handler.addObject(new Block(x*64, y*64, ID.Block, blocksheet));
                }
                if(start[0] == y && start[1] == x) {
                    handler.addObject(new GuiPlayer(x*64, y*64, ID.Player, handler, playersheet));
                }
                if(goal[0] == y && goal[1] == x) {
                    handler.addObject(new Goal(x*64, y*64, ID.Goal));
                }
                if (world.getTile(y, x).hasMonster()) {
                	handler.addObject(new GuiMonster(x*64, y*64, ID.Enemy));
                }
            }
        }
    }

    /* Main method */

    public static void main(String[] args) {
        new Game(640, 480);
    }
}
