package dont.hit.me;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Main extends Canvas implements Runnable {

    public static final int Width = 640, Height = Width / 12 * 9;
    private Thread thread;
    private  boolean running = false;

    private Random r;

    private Handler handler;

    private Main (){
        new Window(Width, Height, "Made by Pontus (Width: " + Width + ") (Height: " + Height + ")", this);

        handler = new Handler();
        r = new Random();

        for(int i = 0; i < 100; i++){
            handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
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
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g  = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, Width, Height);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
	    new Main();
    }
}
