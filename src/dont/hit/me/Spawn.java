package dont.hit.me;

import java.util.Random;

/**
 * Created by pontu on 2017-09-27.
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r;
    private Main game;
    public int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Random r, Main game) {
        this.handler = handler;
        this.game = game;
        this.hud = hud;
        this.r = r;
    }

    public void tick(){
        scoreKeep++;

        if (scoreKeep >= 100){

            hud.setLevel(hud.getLevel() + 1);

            if (game.diffMode == 0) {

               if (hud.getLevel() == 2){
                    handler.addObject(new BasicEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.BasicEnemy, handler));
                }
                else if (hud.getLevel() == 4){
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
               }
                else if (hud.getLevel() == 6) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if (hud.getLevel() == 7) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if (hud.getLevel() == 8) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if(hud.getLevel() == 9) {
                   handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                   handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
               }
                else if(hud.getLevel() == 12) {
                    handler.clearEnemys();
                    handler.addObject(new EnemyBoss((Main.WIDTH / 2)-28, -120, ID.EnemyBoss, handler, game));
                    handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
               }

            } else if(game.diffMode == 2){
                if (hud.getLevel() == 2){
                    handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HardEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HardEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HardEnemy, handler));
                }
                else if (hud.getLevel() == 4){
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                }
                else if (hud.getLevel() == 6) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if (hud.getLevel() == 7) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if (hud.getLevel() == 8) handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                else if(hud.getLevel() == 9) {
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                }
                else if(hud.getLevel() == 12) {
                    handler.clearEnemys();
                    handler.addObject(new EnemyBoss((Main.WIDTH / 2)-28, -120, ID.EnemyBoss, handler, game));
                    handler.addObject(new SmartEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.SmartEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.FastEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HardEnemy, handler));
                    handler.addObject(new HardEnemy(r.nextInt(Main.WIDTH - 50), r.nextInt(Main.HEIGHT - 50), ID.HardEnemy, handler));
                }
            }
                scoreKeep = 0;
        }
    }



}
