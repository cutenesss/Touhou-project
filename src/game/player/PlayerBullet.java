package game.player;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class PlayerBullet extends GameObject {
    //    int count;
    int damage;

    public PlayerBullet() {
        renderer = new Renderer("assets/images/player-bullets/a");
        velocity.set(0, -Setting.BULLET_SPEED);
        hitbox = new BoxCollider(this, 24, 24);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        checkDeactiveIfNeeded();
        checkIntersects();
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

    public void checkIntersects() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this);
        if (enemy != null) {
            this.deactive();
            enemy.takeDamage(damage);
        }
    }

    private void checkDeactiveIfNeeded() {
        if (this.position.y < -30) {
            this.deactive();
        }
    }
}
