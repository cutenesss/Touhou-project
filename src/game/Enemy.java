package game;

import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy {
    Vector2D enemyPosition;
    BufferedImage enemyImage;

    public Enemy(int imageWidth) {
        this.enemyImage = SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png");
        this.enemyPosition = generateRandomPosition(imageWidth, enemyImage.getHeight(), enemyImage.getWidth());
    }

    public BufferedImage getEnemyImage(){
        return this.enemyImage;
    }

    private static Vector2D generateRandomPosition(int imageWidth, int a, int b) {
        Random r = new Random();
        int r1 = r.nextInt(imageWidth - a);
        int r2 = r.nextInt(600 - b);
        return new Vector2D(r1, r2);
    }
}
