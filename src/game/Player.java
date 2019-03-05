package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    BufferedImage image;
    Vector2D position;
    ArrayList<PlayerBulletStraight> bulletStraights;
    ArrayList<PlayerBulletExtraDiagonalRight> bulletExtraDiagonalRights;
    ArrayList<PlayerBulletExtraDiagonalLeft> bulletExtraDiagonalLefts;
    ArrayList<ExtraBullet> bulletExtras1;
    ArrayList<ExtraBullet> bulletExtras2;
    ArrayList<ExtraBullet> bulletExtras3;

    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position = new Vector2D(200, 400);
        bulletStraights = new ArrayList<>();
        bulletExtraDiagonalLefts = new ArrayList<>();
        bulletExtraDiagonalRights = new ArrayList<>();
        bulletExtras1 = new ArrayList<>();
        bulletExtras2 = new ArrayList<>();
        bulletExtras3 = new ArrayList<>();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.x,
                (int) position.y, null);

        for (int i = 0; i < bulletStraights.size(); i++) {
            PlayerBulletStraight bulletStraight = bulletStraights.get(i);
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

    public void run() {
        move();
        limit();
        fire();
       // specialFire();
        bulletsRun();
    }

    int fireCount;

    private void fire() {
        fireCount++;
        if (GameWindow.isFirePress && fireCount > 20) {
            PlayerBulletStraight bullet1 = new PlayerBulletStraight();
            PlayerBulletExtraDiagonalRight bullet2 = new PlayerBulletExtraDiagonalRight();
            PlayerBulletExtraDiagonalLeft bullet3 = new PlayerBulletExtraDiagonalLeft();
            bullet1.position.set(position.x, position.y);
            bullet2.position.set(position.x, position.y);
            bullet3.position.set(position.x, position.y);
//            if(GameWindow.isSpecialPress){
//                ExtraBullet bulletExtra1 = new ExtraBullet();
//                ExtraBullet bulletExtra2 = new ExtraBullet();
//                ExtraBullet bulletExtra3 = new ExtraBullet();
//                bulletExtra1.position.set(bullet1.position.x,bullet1.position.y);
//                bulletExtra2.position.set(bullet2.position.x,bullet2.position.y);
//                bulletExtra3.position.set(bullet3.position.x,bullet3.position.y);
//                bulletExtra1.setPosition();
//                bulletExtra2.setPosition();
//                bulletExtra3.setPosition();
//                bulletExtras1.add(bulletExtra1);
//                bulletExtras2.add(bulletExtra2);
//                bulletExtras3.add(bulletExtra3);
//                fireCount=0;
//            }
            bulletStraights.add(bullet1);
            bulletExtraDiagonalRights.add(bullet2);
            bulletExtraDiagonalLefts.add(bullet3);
            fireCount = 0;
        }
    }

//    public void specialFire(){
//        fireCount++;
//        if(GameWindow.isSpecialPress && fireCount>10){
//            ExtraBullet bulletExtra1 = new ExtraBullet();
//            ExtraBullet bulletExtra2 = new ExtraBullet();
//            ExtraBullet bulletExtra3 = new ExtraBullet();
//            bulletExtra1.position.set(position.x,position.y);
//            bulletExtra2.position.set(position.x,position.y);
//            bulletExtra3.position.set(position.x,position.y);
//            bulletExtra1.setPosition();
//            bulletExtra2.setPosition();
//            bulletExtra3.setPosition();
//            bulletExtras1.add(bulletExtra1);
//            bulletExtras2.add(bulletExtra2);
//            bulletExtras3.add(bulletExtra3);
//            fireCount=0;
//        }
//    }

    //player limitation movement
    private void limit() {
        if (position.y < 0)
            position.set(position.x, 0);                                                               //limit for run up
        if (position.y > 600 - image.getHeight())
            position.set(position.x, (double) 600 - image.getHeight());                          //limit for run down
        if (position.x < 0)
            position.set(0, position.y);                                                               //limit for run left
        if (position.x > 384 - image.getWidth())
            position.set(384 - image.getWidth(), position.y);             //limit for run right
    }

    //player control system
    private void move() {
        int playerSpeed = 2;
        int vx = 0;
        int vy = 0;
        if (GameWindow.isUpPress) {
            vy -= playerSpeed;
        }
        if (GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if (GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }
        if (GameWindow.isRightPress) {
            vx += playerSpeed;
        }
        position.add(vx, vy);
    }

    private void bulletsRun() {
        for (int i = 0; i < bulletStraights.size(); i++) {
            PlayerBulletStraight bulletStraight = bulletStraights.get(i);
            bulletStraight.run();
            PlayerBulletExtraDiagonalLeft bulletExtraDiagonalLeft = bulletExtraDiagonalLefts.get(i);
            bulletExtraDiagonalLeft.run();
            PlayerBulletExtraDiagonalRight bulletExtraDiagonalRight = bulletExtraDiagonalRights.get(i);
            bulletExtraDiagonalRight.run();
            if(GameWindow.isSpecialPress){
                ExtraBullet bulletExtra1 = new ExtraBullet();
                ExtraBullet bulletExtra2 = new ExtraBullet();
                ExtraBullet bulletExtra3 = new ExtraBullet();
                bulletExtra1.position.set(bulletStraight.position.x,bulletStraight.position.y);
                bulletExtra2.position.set(bulletExtraDiagonalLeft.position.x,bulletExtraDiagonalLeft.position.y);
                bulletExtra3.position.set(bulletExtraDiagonalRight.position.x,bulletExtraDiagonalRight.position.y);
                bulletExtra1.setPosition();
                bulletExtra2.setPosition();
                bulletExtra3.setPosition();
                bulletExtras1.add(bulletExtra1);
                bulletExtras2.add(bulletExtra2);
                bulletExtras3.add(bulletExtra3);
                fireCount=0;
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
