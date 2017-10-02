package dont.hit.me;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by pontu on 2017-09-17.
 */
public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    private Main game;

    private Random r = new Random();

    public Handler(Main game){
        this.game = game;
    }

    public void tick(){

        for (int i = 0; i < object.size(); i++) {
            object.get(i).tick();
        }

    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void clearEnemys(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getId() != ID.Player) {
                this.removeObject(tempObject);
                i--;
            } else if(game.gameState == Main.STATE.End || game.gameState == Main.STATE.PlayReset){
                this.removeObject(tempObject);
                i--;
            }
        }
    }

}
