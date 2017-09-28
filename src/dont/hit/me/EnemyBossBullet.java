package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-23.
 */
public class EnemyBossBullet extends GameObject{

    private Handler handler;
    private int timer = 80, timer2 = 50;
    private Random r = new Random();


    public EnemyBossBullet(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
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

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));


    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int) x, (int) y, 16, 16);
    }
}
