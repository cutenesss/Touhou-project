package game;

import java.awt.*;

public class ExtraBullet {
    PlayerBulletExtraDiagonalLeft bulletExtraDiagonalLeft;
    PlayerBulletExtraDiagonalRight bulletExtraDiagonalRight;
    PlayerBulletExtraCrossLeft bulletExtraCrossLeft;
    PlayerBulletExtraCrossRight bulletExtraCrossRight;
    Vector2D position;

    public ExtraBullet() {
        bulletExtraDiagonalLeft = new PlayerBulletExtraDiagonalLeft();
        bulletExtraDiagonalRight = new PlayerBulletExtraDiagonalRight();
        bulletExtraCrossLeft = new PlayerBulletExtraCrossLeft();
        bulletExtraCrossRight = new PlayerBulletExtraCrossRight();
        position = new Vector2D();

    }

    public void render(Graphics g) {
        bulletExtraDiagonalLeft.render(g);
        bulletExtraDiagonalRight.render(g);
        bulletExtraCrossLeft.render(g);
        bulletExtraCrossRight.render(g);
    }

    public void setPosition(){
        bulletExtraDiagonalLeft.position.set(position.x,position.y);
        bulletExtraDiagonalRight.position.set(position.x,position.y);
        bulletExtraCrossLeft.position.set(position.x,position.y);
        bulletExtraCrossRight.position.set(position.x,position.y);
    }
    public void run() {
        bulletExtraCrossLeft.position.add(bulletExtraCrossLeft.velocity.x, bulletExtraCrossLeft.velocity.y);
        bulletExtraCrossRight.position.add(bulletExtraCrossRight.velocity.x, bulletExtraCrossRight.velocity.y);
        bulletExtraDiagonalLeft.position.add(bulletExtraDiagonalLeft.velocity.x, bulletExtraDiagonalLeft.velocity.y);
        bulletExtraDiagonalRight.position.add(bulletExtraDiagonalRight.velocity.x, bulletExtraDiagonalRight.velocity.y);
    }
}
