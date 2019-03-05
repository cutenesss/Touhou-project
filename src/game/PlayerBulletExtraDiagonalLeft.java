package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerBulletExtraDiagonalLeft {
    BufferedImage image;
    Vector2D position;
    Vector2D velocity;

    public PlayerBulletExtraDiagonalLeft() {
        image = SpriteUtils.loadImage("assets/images/player-bullets/a/0.png");
        position = new Vector2D();
        velocity = new Vector2D(-5, -5);
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.x,
                (int) position.y, null);
    }

    public void run() {
        position.add(velocity.x, velocity.y);
    }
}
