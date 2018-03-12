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
 * Basicly we have 640x480 sized window in pixels and we can add objects to coordinates like 244x100
 * Main game loop handles all game-technic stuff. It's updates events 60 times/s (tick-rate) and renders out
 * something like 100+ fps
 * Handler class iterates through list of all game objects and makes them tick and render with their own methods
 */

/**
 * Entry point of the game
 *
 * @author Maks Turtiainen, Vili Ahava
 */
public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private final FontLoader fl;
    
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private final GameCamera cam;

    private final SpriteSheet blocksheet;
    private final BufferedImage blocksheetImg;
    private final SpriteSheet playersheet;
    private final BufferedImage playersheetImg;

    private final BufferedImage road;
    private final BufferedImage bus;

    private STATE state;
    private final Menu menu;
    private final PauseMenu pmenu;
    private final AboutMenu amenu;
    private final StartScreen startscreen;
    private final GoalScreen goalscreen;
    Font font1;
    Font font2;

    private final AudioPlayer bgmusic;
    private final KeyInput in;
    
    /**
     * Game constructor
     * @param x
     * @param y
     */
    public Game(int x, int y) {
        System.setProperty("sun.java2d.opengl", "true");
        state = STATE.MENU;
        bgmusic = new AudioPlayer("/sounds/detective.wav");
        try {
            bgmusic.play();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        menu = new Menu();
        pmenu = new PauseMenu();
        amenu = new AboutMenu();
        startscreen = new StartScreen();
        goalscreen = new GoalScreen();
        this.fl = new FontLoader();
        final Window w = new Window(x, y, "Dungeons of Räntämäki", this);
        cam = new GameCamera(x, y, w.getWidth(), w.getHeigth());
        start();
        handler = new Handler(w.getFrame());
        this.in = new KeyInput(this.handler, this);
        this.addKeyListener(in);
        this.addMouseListener(new MouseInput(this));

        final ImageLoader loader = new ImageLoader();
        blocksheetImg = loader.loadImage("/images/blocksheet.png");
        blocksheet = new SpriteSheet(blocksheetImg);
        playersheetImg = loader.loadImage("/images/playersheet.png");
        playersheet = new SpriteSheet(playersheetImg);
        bus = loader.loadImage("/images/bus.png");

        road = blocksheet.grabImage(2, 2, 64, 64);
        loadLevel();
        this.font1 = fl.loadFont("/fonts/spaceranger.ttf", 14);
        this.font2 = fl.loadFont("/fonts/spaceranger.ttf", 18);
        this.requestFocus();
    }

    /**
     * Starts thread
     */
    protected void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stop method that joins threads
     */
    protected void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main game loop
     */
    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        final double ticks = 60.0;
        final double ns = 1000000000 / ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            final long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            try {
                render();
            } catch (final NullPointerException e) {
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

    /**
     * Updates all game events
     */
    public void tick() {
        if (state == STATE.GAME) {
            this.handler.tick();
            this.requestFocus();
            for (int i = 0; i < handler.objects.size(); i++) {
                if (handler.objects.get(i).getId() == ID.Player) {
                    cam.tick(handler.objects.get(i));
                }
            }
        } else if (state == STATE.MENU || state == STATE.PAUSE || state == STATE.START || state == STATE.ABOUT) {
            while (this.state == STATE.PAUSE) {
                try {
                    Thread.sleep(100);
                } catch (final Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Renders out all events to the screen
     */
    public void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        Toolkit.getDefaultToolkit().sync();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        final Graphics2D g2d = (Graphics2D) g;
        /*
         * //////////////////////////////////// Draw here
         *////////////////////////////////////

        if (state == STATE.GAME) {
            g2d.translate(-cam.getX(), -cam.getY());

            for (int x = 0; x < 41 * 64; x += 64) {
                for (int y = 0; y < 41 * 64; y += 64) {
                    g2d.drawImage(road, x, y, null);
                }
            }
            try {
                handler.render(g2d);
            } catch (final NullPointerException npe) {
                throw new IllegalStateException("Something went wrong, most likely trying to render a null value", npe);
            }
            g2d.translate(cam.getX(), cam.getY());

            playerStats(g2d);

        } else if (state == STATE.MENU) {
            this.menu.render(g2d);
        } else if (state == STATE.PAUSE) {
            this.pmenu.render(g2d);
        } else if (state == STATE.ABOUT) {
            this.amenu.render(g2d);
        } else if (state == STATE.START) {
            this.startscreen.render(g2d);
        } else if (state == STATE.GOAL) {
            this.goalscreen.render(g2d);
        }

        //////////////////////////////////////
        g2d.dispose();
        bs.show();
    }
    /**
     * Reloads all game related asset when loading a new game, and refers certain objects to the ones acquired from deserialization.
     */
    public void reloadAssets() {
        for (int i = 0; i < this.handler.objects.size(); i++) {
            final GameObject temp = this.handler.objects.get(i);
            if(temp.id.equals(ID.Block)) {
                temp.reloadAssets(this.blocksheet);
            } else if (temp.id.equals(ID.Player)) {
                temp.reloadAssets(this.playersheet);
                final GuiPlayer p = (GuiPlayer) temp;
                this.handler = p.getHandler();
                this.in.setHandler(this.handler);
                temp.reloadAssets(this.playersheet);
            } else if (temp.id.equals(ID.Goal)) {
                final Goal g = (Goal) temp;
                g.setBus(this.bus);
            }
        }
    }
    
    /**
     * Generates the level based on a recursive back-track maze generator in
     * world.World.
     */
    public void loadLevel() {

        final World world = new World(41, 41);
        final int w = world.getHeight();
        final int h = world.getWidth();
        final int[] start = world.getStart();
        final int[] goal = world.getGoal();

        /*
         * Creates new objects depending on the state of the Tile object in the World
         * array.
         */

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (!world.getTile(y, x).getPassable() && !(goal[0] == y && goal[1] == x)) {
                    handler.addObject(new Block(x * 64, y * 64, ID.Block, blocksheet));
                }
                if (start[0] == y && start[1] == x) {
                    handler.addObject(new GuiPlayer(x * 64, y * 64, ID.Player, handler, playersheet, this));
                }
                if (goal[0] == y && goal[1] == x) {
                    handler.addObject(new Goal(x * 64, y * 64, ID.Goal, bus));
                }
                if (world.getTile(y, x).hasMonster()) {
                    handler.addObject(new GuiMonster(x * 64, y * 64, ID.Enemy));
                }
            }
        }
    }
    
    public void reloadResources() {
        
    }

    /**
     * Main method, initializes Game class
     * @param args
     */
    public static void main(String[] args) {
        new Game(640, 480);
    }


    /* Getters and setters */

    /**
     * @return state
     */
    public STATE getState() {
        return this.state;
    }

    /**
     * @param state
     */
    public void setState(STATE state) {
        this.state = state;
    }

    /**
     * @return handler
     */
    public Handler getHandler() {
        return this.handler;
    }

    /**
     * Draw's player stats or stats-based bars to top of the game screen
     * @param g
     */
    public void playerStats(Graphics2D g) {

        int playerHP = 0;
        int playerMaxHP = 0;
        int playerMana = 0;
        int playerMaxMana = 0;
        int playerLevel = 0;
        GuiPlayer gp = null;
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                gp = (GuiPlayer) handler.objects.get(i);
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
        g.drawRect(30, 6, playerMaxHP * 2, 12);
        g.setColor(Color.green);
        g.drawString("HP", 8, 16);
        g.setColor(Color.gray);
        g.fillRect(30, 6, playerMaxHP * 2, 12);
        g.setColor(Color.green);
        g.fillRect(30, 6, playerHP * 2, 12);

        /* Mana bar */

        g.setColor(Color.black);
        g.fillRect(185, 6, 50, 13);
        g.drawRect(235, 6, playerMaxMana * 2, 12);
        g.setColor(Color.cyan);
        g.drawString("MANA", 188, 16);
        g.setColor(Color.gray);
        g.fillRect(235, 6, playerMaxMana * 2, 12);
        g.setColor(Color.cyan);
        g.fillRect(235, 6, playerMana * 2, 12);

        /* Level indicator */

        g.setFont(font2);
        g.setColor(Color.black);
        g.fillRect(540, 4, 80, 15);
        g.setColor(Color.yellow);
        g.drawString("LVL: " + playerLevel, 545, 16);
    }
}
