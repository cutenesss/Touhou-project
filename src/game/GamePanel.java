package game;

import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    Vector2D playerPosition;
    BufferedImage backgroundImage;
    Vector2D backgroundPosition;
    BufferedImage bulletImage;
    ArrayList<Vector2D> bulletPositions;
    ArrayList<Enemy> enemyPositions;


    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerPosition = new Vector2D(100, 100);
        backgroundPosition = new Vector2D(0, 600 - backgroundImage.getHeight());
        bulletImage = SpriteUtils.loadImage("assets/images/player-bullets/a/1.png");
        bulletPositions = new ArrayList<>();
        enemyPositions = new ArrayList<>();
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay) {
                runAll();        //logic game
                renderAll();     //render game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint();  // goi lai ham paint()
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, (int) backgroundPosition.x,
                (int) backgroundPosition.y, null);

        g.drawImage(playerImage, (int) playerPosition.x,
                (int) playerPosition.y, null);

        for (int i = 0; i < bulletPositions.size(); i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            g.drawImage(bulletImage, (int) bulletPosition.x,
                    (int) bulletPosition.y, null);
        }
        for (int i = 0; i < enemyPositions.size(); i++) {
            Vector2D enemyPosition = enemyPositions.get(i).enemyPosition;
            g.drawImage(enemyPositions.get(i).enemyImage, (int) enemyPosition.x,
                    (int) enemyPosition.y, null);
        }

    }

    private void runAll() {
        backgroundMove();
        playerMove();
        playerLimit();
        playerFire();
        bulletsRun();
        enemySpawn();
    }

    int frameCount;

    private void enemySpawn() {
        frameCount++;
        if (frameCount > 30) {
            enemyPositions.add(new Enemy(this.backgroundImage.getWidth()));
            frameCount = 0;
        }
    }

    private void playerFire() {
        frameCount++;
        if (GameWindow.isFirePress && frameCount > 20) {
            Vector2D bulletPosition = playerPosition.clone();
            bulletPositions.add(bulletPosition);
            frameCount = 0;
        }
    }

    private void bulletsRun() {
        for (int i = 0; i < bulletPositions.size(); i++) {
            Vector2D bulletPosition = bulletPositions.get(i);
            bulletPosition.add(0, -3);
        }
    }

    //player limitation movement
    private void playerLimit() {
        if (playerPosition.y < 0)
            playerPosition.set(playerPosition.x, 0);                                                               //limit for move up
        if (playerPosition.y > 600 - playerImage.getHeight())
            playerPosition.set(playerPosition.x, (double) 600 - playerImage.getHeight());                          //limit for move down
        if (playerPosition.x < 0)
            playerPosition.set(0, playerPosition.y);                                                               //limit for move left
        if (playerPosition.x > backgroundImage.getWidth() - playerImage.getWidth())
            playerPosition.set(backgroundImage.getWidth() - playerImage.getWidth(), playerPosition.y);             //limit for move right
    }

    //player control system
    private void playerMove() {
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
        playerPosition.add(vx, vy);
    }

    // cho background troi xuong
    private void backgroundMove() {
        backgroundPosition.add(0, 5);
        if (backgroundPosition.y > 0) {
            backgroundPosition.set(backgroundPosition.x, 0);
        }
    }
}
