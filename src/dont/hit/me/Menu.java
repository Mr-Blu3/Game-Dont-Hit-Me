package dont.hit.me;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by pontu on 2017-09-28.
 */
public class Menu extends MouseAdapter {

    private Main game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Main main, Handler handler, HUD hud){
        this.hud = hud;
        this.game = main;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        int mX = e.getX();
        int mY = e.getY();

        if (game.gameState == Main.STATE.Menu) {
            //Play Button
            if(mouseOver(mX, mY, 210, 150, 200, 64)){
                game.gameState = Main.STATE.Game;
                handler.clearEnemys();
                handler.addObject(new Player(game.WIDTH / 2-32, game.HEIGHT/ 2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }


            //Help Button
            if(mouseOver(mX, mY, 210, 250, 200, 64)){
                game.gameState = Main.STATE.Help;
            }

            //Quit Button
            if(mouseOver(mX, mY, 210, 350, 200, 64)){
                System.exit(1);
            }
        }

        //Back Button
        if(game.gameState == Main.STATE.Help){
            if(mouseOver(mX, mY, 210, 350, 200, 64)){
                game.gameState = Main.STATE.Menu;
                return;
            }
        }
        if(game.gameState == Main.STATE.End){
            if(mouseOver(mX, mY, 210, 150, 200, 64)) {
                game.gameState = Main.STATE.Game;
                handler.addObject(new Player(game.WIDTH / 2-32, game.HEIGHT/ 2-32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }
        }
    }
    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mX, int mY, int x, int y, int width, int height){
            if (mX > x && mX < x + width) {
            if (mY > y && mY < y + height) {
                    return true;
                } else{
                    return false;
                }
            } else {
                return false;
            }
    }

    public void tick(){

    }

    public void render(Graphics g){
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 20);

        if(game.gameState == Main.STATE.Menu){
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Hit me", 240 , 100);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 190);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 280, 390);
        } else if(game.gameState == Main.STATE.Help) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240 , 70);

            g.setFont(fnt3);
            g.drawString("Use W: A: S: D: to move player and dodge enemies! =)", 70, 210);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 280, 390);
        } else if(game.gameState == Main.STATE.End) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180 , 70);

            g.setFont(fnt3);
            g.drawString("You reached level: ("+ hud.getLevel() + ") with a final score of: " + hud.getScore(), 115, 210);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }
    }

}
