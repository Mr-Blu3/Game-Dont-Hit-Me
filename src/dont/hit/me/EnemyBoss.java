package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-23.
 */
public class EnemyBoss extends GameObject{

    private Handler handler;
    private int timer = 80, timer2 = 50;
    private Main game;
    private Random r = new Random();


    public EnemyBoss(float x, float y, ID id, Handler handler, Main game){
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    public void tick(){

        x += velX;
        y += velY;

        //if(y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0) timer2--;
        if (timer2 <= 0){

            if (velX == 0) velX = 2;

            if(velX > 0 && velX != 10)
            velX += 0.005f;
            else if(velX < 0 && velX != 10)
            velX -= 0.005f;

            if(velX > 10) velX = 10;

            int spawn = r.nextInt(8);
            if(spawn == 0) handler.addObject(new EnemyBossBullet(x + 48, y + 48, ID.BasicEnemy, handler, game));

        }

        if(x <= 0 || x >= Main.WIDTH - 64) velX *= -1;

    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}
