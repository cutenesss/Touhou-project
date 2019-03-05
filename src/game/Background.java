package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    BufferedImage image;
    Vector2D position;

    public Background(){
        image = SpriteUtils.loadImage("assets/images/background/0.png");
        position = new Vector2D(0, 600 - image.getHeight());
    }

    public void render(Graphics g){
        g.drawImage(image, (int) position.x,
                (int) position.y, null);
    }

    public void run() {
        position.add(0, 5);
        if (position.y > 0) {
            position.set(position.x, 0);
        }
    }
}
