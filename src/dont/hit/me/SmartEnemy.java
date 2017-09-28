package dont.hit.me;

import java.awt.*;

/**
 * Created by pontu on 2017-09-23.
 */
public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;

    public SmartEnemy(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick(){

        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        double distance = Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velX = (float) ((-2.0/distance)* diffX);
        velY = (float) ((-2.0/distance)* diffY);

        if(y <= 0 || y >= Main.HEIGHT - 16) velY *= -1;
        if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.MAGENTA, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.drawRoundRect((int)x, (int) y, 16, 16, 50, 50);
    }
}
