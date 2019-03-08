package game.enemy;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.player.Player;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    int count;

    public EnemyBullet() {
        image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        velocity.set(0, Setting.BULLET_SPEED);
    }

    @Override
    public void run() {
        super.run();
//        count++;
//        if (count > 150) {
//            Player clone = GameObject.find(Player.class);
//            if (clone != null) {
//                Vector2D bulletToPlayer = clone.position.clone();
//                bulletToPlayer.substract(this.position);
//                bulletToPlayer.setLength(Setting.BULLET_SPEED);
//                this.velocity.set(bulletToPlayer);
//            }
//        }
    }
}
