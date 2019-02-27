import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    int playerX;
    int playerY;
    BufferedImage backgroundImage;
    int backgroundX;
    int backgroundY;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerX = 100;
        playerY = 100;
        backgroundX = 0;
        backgroundY = 600 - backgroundImage.getHeight();
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastLoop > delay) {
                runAll();  //logic game
                renderAll();  //render game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint();  // goi lai ham paint()
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, backgroundX, backgroundY, null);
        g.drawImage(playerImage, playerX, playerY, null);

    }

    private void runAll() {
        backgroundY ++;  // cho background troi xuong
        if (backgroundY > 0) {
            backgroundY = 0;
        }

        int playerSpeed = 2;
        if (GameWindow.isUpPress) {
            playerY -= playerSpeed;
            if (playerY < 0) playerY = 0;
        }
        if (GameWindow.isDownPress) {
            playerY += 5;
            if (playerY > 600 - 2*playerImage.getHeight()) playerY = 600 - 2*playerImage.getHeight();
        }
        if (GameWindow.isLeftPress) {
            playerX -= playerSpeed;
            if (playerX < 0) playerX = 0;
        }
        if (GameWindow.isRightPress) {
            playerX += playerSpeed;
            if (playerX > backgroundImage.getWidth() - playerImage.getWidth())
                playerX = backgroundImage.getWidth() - playerImage.getWidth();
        }
    }
}
