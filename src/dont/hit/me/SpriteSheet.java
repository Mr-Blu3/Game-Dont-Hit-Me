package dont.hit.me;

import java.awt.image.BufferedImage;

/**
 * Created by pontu on 2017-10-05.
 */
public class SpriteSheet {

    private BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img  = sprite.getSubimage((row * 32) - 32, (col * 32) - 32, width, height);
        return img;
    }

}
