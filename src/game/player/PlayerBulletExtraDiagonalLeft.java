package game.player;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.enemy.Enemy;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerBulletExtraDiagonalLeft extends PlayerBullet {
    public PlayerBulletExtraDiagonalLeft() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/0.png");
        velocity.setAngle(-3 * Math.PI / 4);
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
