package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    //Quan li doi tuong
    public static ArrayList<GameObject> objects = new ArrayList<>();

    //find
    public static <E extends GameObject> E find(Class<E> cls) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            if (object.getClass().isAssignableFrom(cls)) return (E) object;
        }
        return null;
    }

    //Dinh nghia doi tuong
    public BufferedImage image;
    public Vector2D position;
    public Vector2D velocity;

    public GameObject() {
        objects.add(this);
        this.position = new Vector2D();
        this.velocity = new Vector2D();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) this.position.x,
                (int) this.position.y, null);
    }

    public void run() {
        position.add(this.velocity);
    }
}
