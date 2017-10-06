package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-23.
 */
public class MenuPartical extends GameObject{

    private Handler handler;
    Random r = new Random();
    private Color col;


    public MenuPartical(float x, float y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = r.nextInt((4 - - 4) + 4);
        velY = r.nextInt((4 - - 4) + 4);
        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int)y, 16, 16);
    }

    public void tick(){

        x += velX;
        y += velY;

        if(y <= 0 || y >= Main.HEIGHT - 42) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 25) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, 2,3, handler));
    }

    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
