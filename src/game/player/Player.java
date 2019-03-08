package game.player;

import game.*;
import tklibs.SpriteUtils;

import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject {
    ArrayList<PlayerBullet> bulletStraights;
    ArrayList<PlayerBulletExtraDiagonalRight> bulletExtraDiagonalRights;
    ArrayList<PlayerBulletExtraDiagonalLeft> bulletExtraDiagonalLefts;
    ArrayList<ExtraBullet> bulletExtras1;
    ArrayList<ExtraBullet> bulletExtras2;
    ArrayList<ExtraBullet> bulletExtras3;

    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position.set(200, 400);
        bulletStraights = new ArrayList<>();
        bulletExtraDiagonalLefts = new ArrayList<>();
        bulletExtraDiagonalRights = new ArrayList<>();
        bulletExtras1 = new ArrayList<>();
        bulletExtras2 = new ArrayList<>();
        bulletExtras3 = new ArrayList<>();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        for (int i = 0; i < bulletStraights.size(); i++) {
            PlayerBullet bulletStraight = bulletStraights.get(i);
            bulletStraight.render(g);
            PlayerBulletExtraDiagonalRight bulletExtraDiagonalRight = bulletExtraDiagonalRights.get(i);
            bulletExtraDiagonalRight.render(g);
            PlayerBulletExtraDiagonalLeft bulletExtraDiagonalLeft = bulletExtraDiagonalLefts.get(i);
            bulletExtraDiagonalLeft.render(g);
        }

        for (int i = 0; i < bulletExtras1.size(); i++) {
            ExtraBullet bulletExtra1 = bulletExtras1.get(i);
            bulletExtra1.render(g);
            ExtraBullet bulletExtra2 = bulletExtras2.get(i);
            bulletExtra2.render(g);
            ExtraBullet bulletExtra3 = bulletExtras3.get(i);
            bulletExtra3.render(g);
        }
    }

    @Override
    public void run() {
        super.run();
        move();
        limit();
        fire();
        bulletsRun();
    }

    int fireCount;

    private void fire() {
        fireCount++;
        if (GameWindow.isFirePress && fireCount > 20) {
            PlayerBullet bullet1 = new PlayerBullet();
            PlayerBulletExtraDiagonalRight bullet2 = new PlayerBulletExtraDiagonalRight();
            PlayerBulletExtraDiagonalLeft bullet3 = new PlayerBulletExtraDiagonalLeft();
            bullet1.position.set(position);
            bullet2.position.set(position);
            bullet3.position.set(position);
            bulletStraights.add(bullet1);
            bulletExtraDiagonalRights.add(bullet2);
            bulletExtraDiagonalLefts.add(bullet3);
            fireCount = 0;
        }
    }

    //player limitation movement
    private void limit() {
        if (position.y < 0)
            position.set(position.x, 0);                                                               //limit for run up
        if (position.y > Setting.GAME_HEIGHT - image.getHeight())
            position.set(position.x, (double) Setting.GAME_HEIGHT - image.getHeight());                          //limit for run down
        if (position.x < 0)
            position.set(0, position.y);                                                               //limit for run left
        if (position.x > Setting.BACKGROUND_WIDTH - image.getWidth())
            position.set(Setting.BACKGROUND_WIDTH - image.getWidth(), position.y);             //limit for run right
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

    int fireCount1;

    private void bulletsRun() {
        for (int i = 0; i < bulletStraights.size(); i++) {
            fireCount1++;
            PlayerBullet bulletStraight = bulletStraights.get(i);
            bulletStraight.run();
            PlayerBulletExtraDiagonalLeft bulletExtraDiagonalLeft = bulletExtraDiagonalLefts.get(i);
            bulletExtraDiagonalLeft.run();
            PlayerBulletExtraDiagonalRight bulletExtraDiagonalRight = bulletExtraDiagonalRights.get(i);
            bulletExtraDiagonalRight.run();
            if (GameWindow.isSpecialPress && fireCount1 > 10) {
                ExtraBullet bulletExtra1 = new ExtraBullet();
                ExtraBullet bulletExtra2 = new ExtraBullet();
                ExtraBullet bulletExtra3 = new ExtraBullet();
                bulletExtra1.position.set(bulletStraight.position);
                bulletExtra2.position.set(bulletExtraDiagonalLeft.position);
                bulletExtra3.position.set(bulletExtraDiagonalRight.position);
                bulletExtra1.setPosition();
                bulletExtra2.setPosition();
                bulletExtra3.setPosition();
                bulletExtras1.add(bulletExtra1);
                bulletExtras2.add(bulletExtra2);
                bulletExtras3.add(bulletExtra3);
                fireCount1 = 0;
            }
        }

        for (int i = 0; i < bulletExtras1.size(); i++) {
            ExtraBullet bulletExtra1 = bulletExtras1.get(i);
            bulletExtra1.run();
            ExtraBullet bulletExtra2 = bulletExtras2.get(i);
            bulletExtra2.run();
            ExtraBullet bulletExtra3 = bulletExtras3.get(i);
            bulletExtra3.run();
        }
    }
}
