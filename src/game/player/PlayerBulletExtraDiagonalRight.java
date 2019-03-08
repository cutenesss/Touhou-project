package game.player;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.enemy.Enemy;
import tklibs.SpriteUtils;

public class PlayerBulletExtraDiagonalRight extends PlayerBullet {
    int count;

    public PlayerBulletExtraDiagonalRight() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/2.png");
        velocity.setAngle(-Math.PI / 4);
    }

    @Override
    public void run() {
        super.run();
        count++;
        if (count > 120) {
            Enemy enemy = GameObject.find(Enemy.class);
            if (enemy != null) {
                Vector2D bulletToEnemy = enemy.position.clone();
                bulletToEnemy.substract(this.position);
                bulletToEnemy.setLength(Setting.BULLET_SPEED);
                this.velocity.set(bulletToEnemy);
            }
        }
    }
}
