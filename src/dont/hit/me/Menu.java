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
    private Spawn spawn;

    public Menu(Main main, Handler handler, HUD hud, Spawn spawn){
        this.hud = hud;
        this.game = main;
        this.handler = handler;
        this.spawn = spawn;
    }

    public void mousePressed(MouseEvent e){
        int mX = e.getX();
        int mY = e.getY();

        if (game.gameState == Main.STATE.Menu) {
            //Play Button
            if(mouseOver(mX, mY, 210, 150, 200, 64)) {
                game.gameState = Main.STATE.mode;
                return;
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

        if(Main.paused) {
            // Upgrade Health
            if(hud.getScore() >= 1){
                if(mouseOver(mX, mY, 150, 150, 300, 64)) {
                    if(hud.VMaxHP < (100 + (hud.VMaxHP / 2))){
                        hud.VMaxHP += 20;
                        hud.HEALTH = (100 + (hud.VMaxHP / 2));
                        hud.setScore(hud.getScore() - 100);
                        Main.paused = false;
                    }
                }

                // Upgrade Speed
                if(mouseOver(mX, mY, 150, 250, 300, 64)) {
                    handler.speed++;
                    hud.setScore(hud.getScore() - 100);
                    Main.paused = false;
                }

                // Refill Health
                if(mouseOver(mX, mY, 150, 350, 300, 64)) {
                    hud.HEALTH = (50 + (hud.VMaxHP / 2));
                    hud.setScore(hud.getScore() - 100);
                    Main.paused = false;
                }
            }
        }

        if (game.gameState == Main.STATE.mode) {
            // Easy
            if(mouseOver(mX, mY, 210, 150, 200, 64)) {
                game.diffMode = 0;
                this.resetLevel(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }


            // Normal Button
            if(mouseOver(mX, mY, 210, 250, 200, 64)){
                game.diffMode = 1;
                this.resetLevel(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
            }

            // Hard Button
            if(mouseOver(mX, mY, 210, 350, 200, 64)){
                game.diffMode = 2;
                this.resetLevel(new HardEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.HardEnemy, handler));
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
            if(mouseOver(mX, mY, 210, 350, 200, 64)) game.gameState = Main.STATE.Menu;
        }
    }
    public void mouseReleased(MouseEvent e){

    }

    private void resetLevel(GameObject Enemy){
        game.gameState = Main.STATE.PlayReset;
        handler.speed = 3;
        hud.VMaxHP = 50;
        HUD.HEALTH = 200;
        hud.setLevel(1);
        hud.setScore(0);
        spawn.scoreKeep = 0;
        handler.clearEnemys();
        game.gameState = Main.STATE.Game;
        handler.addObject(new Player(game.WIDTH / 2-32, game.HEIGHT/ 2-32, ID.Player, handler));
        handler.addObject(Enemy);
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
            g.drawString("Press esc to get back to menu!", 70, 240);

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
        } else if(game.gameState == Main.STATE.mode) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Mode", 240 , 100);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Easy", 280, 190);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Normal", 260, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Hard", 280, 390);
        }

        if(Main.paused) {
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Shop", 240 , 100);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(150, 150, 300, 64);
            g.drawString("Upgrade Health", 200, 190);

            g.setColor(Color.white);
            g.drawRect(150, 250, 300, 64);
            g.drawString("Upgrade Speed", 200, 290);

            g.setColor(Color.white);
            g.drawRect(150, 350, 300, 64);
            g.drawString("Refill Health", 220, 390);
        }
    }

}
