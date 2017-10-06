// https://www.youtube.com/watch?v=RrahDyZXAv0 (tut)

package dont.hit.me;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    public static boolean paused = false;
    public static boolean shop = false;

    // 0 = Easy, 1 = normal, 2 = Hard
    public int diffMode = 0;


    private Thread thread;
    private  boolean running = false;

    private Random r = new Random();

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu theMenu;

    public enum STATE {
        Menu,
        Help,
        Game,
        PlayReset,
        End,
        mode,
        shop
    }

    public STATE gameState = STATE.Menu;

    public static BufferedImage sprite_sheet;

    private Main (){
        new Window(WIDTH, HEIGHT, "Made by Pontus (Width: " + WIDTH + ") (Height: " + HEIGHT + ")", this);


        hud = new HUD();
        handler = new Handler(this);
        spawner = new Spawn(handler, hud, r, this);
        theMenu = new Menu(this, handler, hud, spawner);

        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(theMenu);

        BufferedImgLoader loader = new BufferedImgLoader();

        sprite_sheet = loader.loadImage("/player_board.png");

        if(gameState != STATE.Game){
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuPartical(r.nextInt(WIDTH - 42), r.nextInt(HEIGHT - 25), ID.MenuPartical, handler));
            }
        }

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
                render();
                frames++;
            }
            if(running)
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
        }

        stop();
    }

    /*
    * A "tick" is just updating the game logic. Ex update position of the squares
    *
    * Rendering: is updating the stuff shown on screen.
    * */

    private void tick() {
        if(gameState == STATE.Game){
            if(!paused || !shop){
                handler.tick();
                hud.tick();
                spawner.tick();
                if(HUD.HEALTH <= 0){
                    gameState = STATE.End;
                    handler.clearEnemys();
                    this.resetGame();
                }

            }

        } else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help || gameState == STATE.mode){
            handler.tick();
            theMenu.tick();
        }

    }

    private void resetGame(){
        HUD.HEALTH = 100*2;
        for (int i = 0; i < 15; i++) {
            handler.addObject(new MenuPartical(r.nextInt(WIDTH - 42), r.nextInt(HEIGHT - 25), ID.MenuPartical, handler));
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g  = bs.getDrawGraphics();

        if(hud.greenValue < 100) {
            if(hud.greenValue <= 10) g.setColor(new Color(75, 90, 0)); else g.setColor(new Color(75, hud.greenValue - 10, 0));
        } else {
            g.setColor(new Color(75, 90, 0));
        }

        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        if(paused){
            g.drawString("Paused", 100, 100);
            gameState = STATE.shop;
        }

        if(gameState == STATE.Game){
            hud.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.mode) {
            theMenu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int xVal, int min, int max){
        if(xVal >= max)
            return max;
        else if(xVal <= min)
            return min;
        else
            return xVal;
    }

    public static void main(String[] args) {
	    new Main();
    }
}
