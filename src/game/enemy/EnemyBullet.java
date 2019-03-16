package game.enemy;

import game.GameObject;
import game.Setting;
import game.Vector2D;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject {
    int count;
    int damage;

    public EnemyBullet() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        renderer = new Renderer(image);
        velocity.set(0, Setting.BULLET_SPEED);
        hitbox = new BoxCollider(this,16,16);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
        checkIntersects();
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

    private void checkIntersects() {
        Player player = GameObject.findIntersects(Player.class,this);
        if(player != null){
            this.deactive();
            player.takeDamage(damage);
        }
    }

    private void deactiveIfNeeded() {
        if(this.position.y>Setting.GAME_HEIGHT+50){
            this.deactive();
        }
    }
}
