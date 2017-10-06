package dont.hit.me;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private Handler handler;

    private BufferedImage player_image;

    public Player(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        SpriteSheet ss = new SpriteSheet(Main.sprite_sheet);

        player_image = ss.grabImage(1,1,32,32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Main.clamp((int) x, 0, Main.WIDTH-40);
        y = Main.clamp((int) y, 0, Main.HEIGHT-64);
        handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, 1, 1, handler));

        collision();
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HardEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss){
                if (getBounds().intersects(tempObject.getBounds())){
                    // Collision
                    HUD.HEALTH -= 2;

                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(player_image, (int) x, (int) y, null);
    }
}
