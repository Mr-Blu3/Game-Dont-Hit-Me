package dont.hit.me;

import java.awt.*;

/**
 * Created by pontu on 2017-09-23.
 */
public class HUD {

    public float greenValue = 255f;

    public int VMaxHP = 0;

    public static int HEALTH = 100;

    private int score = 0;

    private int level = 1;

    public HUD(){}

    public void tick(){

        HEALTH = Main.clamp(HEALTH, 0, 100 + (VMaxHP / 2));

        greenValue = Main.clamp((int) greenValue, 0, 255);
        greenValue = HEALTH * 2;
        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + VMaxHP, 32);
        if(greenValue < 255) g.setColor(new Color(75, (int) greenValue, 0));
        else g.setColor(new Color(75, 255, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);

        g.setColor(Color.white);
        g.drawRect(15, 15, 200 + VMaxHP, 32);

        g.drawString("Score: "+  score, 15, 64);
        g.drawString("Level: "+  level, 15, 80);
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
