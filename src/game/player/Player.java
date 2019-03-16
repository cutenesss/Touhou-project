package game.player;

import game.*;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {
    ArrayList<PlayerBullet> bulletStraights;
    int hp;
    int damage;

    public Player() {
        renderer = new Renderer("assets/images/players/straight");
        position.set(200, 400);
        bulletStraights = new ArrayList<>();
        hitbox = new BoxCollider(this, 30,45);
        hp = 10;
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        move();
        limit();
        fire();
        checkIntersects();
    }

    private void checkIntersects() {
        Enemy enemy = GameObject.findIntersects(Enemy.class, this);
        if(enemy != null){
            enemy.takeDamage(damage);
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    int fireCount;

    private void fire() {
        fireCount++;
        if (GameWindow.isFirePress && fireCount > 20) {
            for (int i = 0; i < 5; i++) {
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(position);
                bullet.velocity.setAngle(-Math.PI / 3 - i * Math.PI / 12);
                bulletStraights.add(bullet);
            }
            fireCount = 0;
        }
    }

    //player limitation movement
    private void limit() {
        if (position.y < Setting.PLAYER_HEIGHT * this.anchor.y)
            position.set(position.x, Setting.PLAYER_HEIGHT * this.anchor.y);                                                               //limit for run up
        if (position.y > Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT * this.anchor.y)
            position.set(position.x, (double) Setting.GAME_HEIGHT - Setting.PLAYER_HEIGHT * this.anchor.y);                          //limit for run down
        if (position.x < Setting.PLAYER_WIDTH * this.anchor.x)
            position.set(Setting.PLAYER_WIDTH * this.anchor.x, position.y);                                                               //limit for run left
        if (position.x > Setting.BACKGROUND_WIDTH - Setting.PLAYER_WIDTH * this.anchor.x)
            position.set(Setting.BACKGROUND_WIDTH - Setting.PLAYER_WIDTH * this.anchor.x, position.y);             //limit for run right
    }

    //player control system
    private void move() {
        int vx = 0;
        int vy = 0;
        if (GameWindow.isUpPress) {
            vy -= Setting.PLAYER_SPEED;
        }
        if (GameWindow.isDownPress) {
            vy += Setting.PLAYER_SPEED;
        }
        if (GameWindow.isLeftPress) {
            vx -= Setting.PLAYER_SPEED;
        }
        if (GameWindow.isRightPress) {
            vx += Setting.PLAYER_SPEED;
        }
        velocity.set(vx, vy);
        velocity.setLength(Setting.PLAYER_SPEED);
    }


}
