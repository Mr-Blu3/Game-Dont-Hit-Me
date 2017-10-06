package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-23.
 */
public class EnemyBossBullet extends GameObject{

    private Handler handler;
    private int timer = 80, timer2 = 50;
    private Main game;
    private Random r = new Random();


    public EnemyBossBullet(float x, float y, ID id, Handler handler, Main game){
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        velX = r.nextInt((5 - - 5) + 5);
        velY = 3;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){

        x += velX;
        y += velY;

        //if(y <= 0 || y >= Main.HEIGHT - 16) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;
        if(y >= Main.HEIGHT) handler.removeObject(this);
        if(game.diffMode != 0){
            handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.02f,3,1, handler));
        } else {
            handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, 3,1, handler));
        }
    }

    public void render(Graphics g){

        if(game.diffMode != 0){
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.red);
        }

        g.drawRect((int) x, (int) y, 16, 16);
    }
}
