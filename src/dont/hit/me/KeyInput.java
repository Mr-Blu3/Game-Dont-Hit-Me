package dont.hit.me;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Main game;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler, Main game){
        this.game = game;
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                // Key Events Player

                if (key == KeyEvent.VK_W) {tempObject.setVelY(-handler.speed); keyDown[0] = true;}
                if (key == KeyEvent.VK_S) {tempObject.setVelY(handler.speed); keyDown[1] = true;}
                if (key == KeyEvent.VK_D) {tempObject.setVelX(handler.speed); keyDown[2] = true;}
                if (key == KeyEvent.VK_A) {tempObject.setVelX(-handler.speed); keyDown[3] = true;}
            }

        }

        if(key == KeyEvent.VK_P) {
            if(game.gameState == Main.STATE.Game || game.gameState == Main.STATE.Shop){
                if(Main.paused) {
                    Main.paused = false;
                } else {
                    Main.paused = true;
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            game.gameState = Main.STATE.Menu;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player){
                // Key Events Player

                if (key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) keyDown[2] = false; //tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) keyDown[3] = false; //tempObject.setVelX(0);

                // Vert movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);

                // Hori movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }

        }
    }


}
