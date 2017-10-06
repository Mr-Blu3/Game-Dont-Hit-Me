package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-23.
 */
public class HardEnemy extends GameObject{

    private Handler handler;
    private Random r = new Random();

    public HardEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 7;
        velY = 7;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){

        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.HEIGHT - 32) if(velY < 0) velY = -(r.nextInt(7)+1)*-1; else velY = (r.nextInt(7)+1) *-1;
        if(x <= 0 || x >= Main.WIDTH - 16) if(velX < 0) velX = -(r.nextInt(7)+1) *-1; else velX = (r.nextInt(7)+1) *-1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.02f, 2,2, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
