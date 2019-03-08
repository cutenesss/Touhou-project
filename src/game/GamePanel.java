package game;

import game.enemy.Enemy;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    Player player;
    Background background;
    ArrayList<Enemy> enemies;

    public GamePanel() {
        player = new Player();
        background = new Background();
        enemies = new ArrayList<>();
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
        background.render(g);
        player.render(g);

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.render(g);
        }
    }

    private void runAll() {
        background.run();
        player.run();
        summonEnemies();
        enemiesRun();
    }

    private void enemiesRun() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.run();
        }
    }

    int summonCount;
    int waveCount;
    int enemyCount;
    Random random = new Random();
    int enemyX = 100 + random.nextInt(200);

    private void summonEnemies() {
        waveCount++;
        if (waveCount > 120) {
            summonCount++;
            if (summonCount > 15) {
                Enemy enemy = new Enemy();
                enemy.position.set(enemyX, -100);
                enemy.velocity.setAngle(Math.PI / 9);
                enemy.run();
                enemies.add(enemy);
                enemyCount++;
                summonCount = 0;
                if (enemyCount > 4) {
                    waveCount = 0;
                    enemyCount = 0;
                    enemyX = 100 + random.nextInt(200);
                }
            }
        }
    }
}