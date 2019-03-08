package game.player;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.enemy.Enemy;
import tklibs.SpriteUtils;

public class PlayerBullet extends GameObject {
//    int count;

    public PlayerBullet() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        velocity.set(0, -Setting.BULLET_SPEED);
    }

    @Override
    public void run() {
        super.run();
//        count++;
//        if (count > 120) {
//            Enemy enemy = GameObject.find(Enemy.class);
//            if (enemy != null) {
//                Vector2D bulletToEnemy = enemy.position.clone();
//                bulletToEnemy.substract(this.position);
//                bulletToEnemy.setLength(Setting.BULLET_SPEED);
//                this.velocity.set(bulletToEnemy);
//            }
//        }
    }
}
