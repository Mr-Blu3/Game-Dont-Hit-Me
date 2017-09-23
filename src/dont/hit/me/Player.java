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
