package dont.hit.me;

import java.awt.*;

/**
 * Created by pontu on 2017-09-23.
 */
public class BasicEnemy extends GameObject{

    private Handler handler;

    public BasicEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){

        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, 2, 2, handler));
    }

    public void render(Graphics g){
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
