package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
    
    
    private FontLoader fl;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private GameCamera cam;

    private SpriteSheet blocksheet;
    private BufferedImage blocksheetImg = null;
    private SpriteSheet playersheet;
    private BufferedImage playersheetImg = null;

    private BufferedImage road = null;

    private STATE state;
    private Menu menu;
    private PauseMenu pmenu;
    private AboutMenu amenu;
    private StartScreen startscreen;
    Font font1;
    Font font2;

    private AudioPlayer bgmusic;

    public Game(int x, int y) {
    	System.setProperty("sun.java2d.opengl", "true");
    	state = STATE.MENU;
        bgmusic = new AudioPlayer("/sounds/detective.wav");
        try {
            bgmusic.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        menu = new Menu();
        pmenu = new PauseMenu();
        amenu = new AboutMenu();
        startscreen = new StartScreen();
    	this.fl = new FontLoader();
        Window w = new Window(x, y, "Dungeons of Räntämäki", this); 
        cam = new GameCamera(x, y, w.getWidth(), w.getHeigth());
        start();
        handler = new Handler(w.getFrame());
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput(this));
        

        ImageLoader loader = new ImageLoader();
        blocksheetImg = loader.loadImage("/images/blocksheet.png");
        blocksheet = new SpriteSheet(blocksheetImg);
        playersheetImg = loader.loadImage("/images/playersheet.png");
        playersheet = new SpriteSheet(playersheetImg);

        road = blocksheet.grabImage(2, 2, 64, 64);
        loadLevel();
        this.font1 = fl.loadFont("/fonts/spaceranger.ttf", 14);
        this.font2 = fl.loadFont("/fonts/spaceranger.ttf", 18);
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
            try {
            	render();
            } catch (NullPointerException e) {
            	throw new IllegalStateException("Something went terribly wrong", e);
            }
 
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
        if (state == STATE.GAME) {
            handler.tick();
            this.requestFocus();
            for (int i=0;i<handler.objects.size();i++) {
                if (handler.objects.get(i).getId() == ID.Player) {
                    cam.tick(handler.objects.get(i));
                }
            }
        } else if (state == STATE.MENU || state == STATE.PAUSE || state == STATE.START || state == STATE.ABOUT) {
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
        
        if (state == STATE.GAME) {
        g2d.translate(-cam.getX(), -cam.getY());

            for (int x = 0; x < 41 * 64; x += 64) {
                for (int y = 0; y < 41 * 64; y+=64) {
                    g2d.drawImage(road, x, y, null);
                }
            }
            try {
                handler.render(g2d);
            } catch (NullPointerException npe) {
                throw new IllegalStateException("Something went wrong, most likely trying to render a null value", npe);
            }
        g2d.translate(cam.getX(), cam.getY());

        playerStats(g2d);
        
        } else if (state == STATE.MENU) {
            menu.render(g2d);
        } else if (state == STATE.PAUSE) {
            pmenu.render(g2d);
        } else if (state == STATE.ABOUT) {
            amenu.render(g2d);
        } else if (state == STATE.START) {
            startscreen.render(g2d);
        }


        

        //////////////////////////////////////
        g2d.dispose();
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

    /* Getters and setters for state */

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    /*
     * Draw's player stats or stats-based bars to top of the game screen
     */

    public void playerStats(Graphics2D g) {

        int playerHP = 0;
        int playerMaxHP = 0;
        int playerMana = 0;
        int playerMaxMana = 0;
        int playerLevel = 0;
        GuiPlayer gp = null;
        for (int i=0;i<handler.objects.size();i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                gp = (GuiPlayer)handler.objects.get(i);
                playerHP = gp.getHP();
                playerMaxHP = gp.getMaxHP();
                playerMana = gp.getMana();
                playerMaxMana = gp.getMaxMana();
                playerLevel = gp.getLevel();
            }
        }
        
        
        g.setFont(font1);

        /* HP bar */

        g.setColor(Color.black);
        g.fillRect(5, 6, 30, 13);
        g.drawRect(30, 6, playerMaxHP*2, 12);
        g.setColor(Color.green);
        g.drawString("HP", 8, 16);
        g.setColor(Color.gray);
        g.fillRect(30, 6, playerMaxHP*2, 12);
        g.setColor(Color.green);
        g.fillRect(30, 6, playerHP*2, 12);

        /* Mana bar */

        g.setColor(Color.black);
        g.fillRect(185, 6, 50, 13);
        g.drawRect(235, 6, playerMaxMana*2, 12);
        g.setColor(Color.cyan);
        g.drawString("MANA", 188, 16);
        g.setColor(Color.gray);
        g.fillRect(235, 6, playerMaxMana*2, 12);
        g.setColor(Color.cyan);
        g.fillRect(235, 6, playerMana*2, 12);

        /* Level indicator */

        g.setFont(font2);
        g.setColor(Color.black);
        g.fillRect(540, 4, 80, 15);
        g.setColor(Color.yellow);
        g.drawString("LVL: " + playerLevel, 545, 16);
    }
}
