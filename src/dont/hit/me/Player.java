package dont.hit.me;

import java.awt.*;

/**
 * Created by pontu on 2017-09-17.
 */
public class Player extends GameObject {

    public Player(int x, int y, ID id){
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;
    }


    public void render(Graphics g) {

        if(id == ID.Player) g.setColor(Color.white);
            else if(id == ID.Player2) g.setColor(Color.green);

        g.drawRoundRect(x, y, 32, 32, 50, 50);

    }
}
