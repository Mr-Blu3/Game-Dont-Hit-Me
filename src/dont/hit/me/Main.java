// https://www.youtube.com/watch?v=5ufOPX8N1Rg (tut)

package dont.hit.me;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private  boolean running = false;

    private Random r;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu theMenu;

    public enum STATE {
        Menu,
        Help,
        Game,
        End
    }

    public STATE gameState = STATE.Menu;

    private Main (){
        new Window(WIDTH, HEIGHT, "Made by Pontus (Width: " + WIDTH + ") (Height: " + HEIGHT + ")", this);

        hud = new HUD();
        handler = new Handler(this);
        theMenu = new Menu(this, handler, hud);

        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(theMenu);

        r = new Random();
        spawner = new Spawn(handler, hud, r);


        if(gameState == STATE.Game){
            //handler.addObject(new Player(WIDTH / 2-32, HEIGHT/ 2-32, ID.Player, handler));
            //handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));

        } else {
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
        handler.tick();

        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(HUD.HEALTH <= 0){
                gameState = STATE.End;
                handler.clearEnemys();
                this.resetGame();
            }

        } else if(gameState == STATE.Menu || gameState == STATE.End){
            theMenu.tick();
        }

    }

    private void resetGame(){
        HUD.HEALTH = 100*2;
        hud.setLevel(1);
        hud.setScore(0);

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

        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
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
