package dont.hit.me;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Trail extends GameObject {

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width, height;
    private float life;
    private BufferedImage player_image;

    public Trail(float x, float y, ID id, Color color, int width, int height, float life, int row, int col, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        SpriteSheet ss = new SpriteSheet(Main.sprite_sheet);
        player_image = ss.grabImage(row,col,width,height);
    }

    public void tick() {
        if(alpha > life) {
            alpha -= life  - 0.0001f;
        } else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        //g.drawRoundRect((int) x, (int) y, width, height, width, height);
        g.drawImage(player_image, (int) x, (int) y, null);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    public Rectangle getBounds() {
        return null;
    }
}
