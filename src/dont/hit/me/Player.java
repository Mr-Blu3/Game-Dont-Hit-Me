package dont.hit.me;

import java.awt.*;
import java.util.Random;

/**
 * Created by pontu on 2017-09-17.
 */
public class Player extends GameObject {

    Random r = new Random();

    public Player(int x, int y, ID id){
        super(x, y, id);
        rLocation(r.nextInt(5) + 1, r.nextInt(5));
    }

    public void tick() {
        x += velX;
        y += velY;
        if(x > 640 - 64) {
            rLocation(velX - 1, velY - 0);
        } else if(x <= 0 + 32) {
            rLocation(velX + 1, velY + 0);
        }
        if(y >= 477) {
            x = 0;
            y = 0;
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.white);

        g.drawRoundRect(x, y, 32, 32, 50, 50);

    }

    private void rLocation(int rX, int rY){
        velX = rX;
        velY = rY;
    }
}
