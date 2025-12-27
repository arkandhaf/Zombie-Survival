package main;

import entity.Bullet;
import entity.Player;
import entity.Zombie;
import inputs.KeyHandler;
import inputs.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    final int screenWidth = 800;
    final int screenHeight = 600;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    int FPS = 60;

    // Objects
    Player player = new Player(100, 100, keyH);
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();

    int spawnCounter = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();

        // LOGIC NEMBAK (Klik Kiri)
        if(mouseH.mousePressed) {
            bullets.add(new Bullet(player.x + 10, player.y + 10, mouseH.mouseX, mouseH.mouseY));
            mouseH.mousePressed = false; // Semi-auto (harus klik lagi)
        }

        // UPDATE PELURU
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if(!bullets.get(i).alive) {
                bullets.remove(i);
                i--;
            }
        }

        // SPAWN ZOMBIE (Setiap 60 frame / 1 detik)
        spawnCounter++;
        if(spawnCounter > 60) {
            double randomX = Math.random() * screenWidth;
            double randomY = Math.random() * screenHeight;
            zombies.add(new Zombie(randomX, randomY, player));
            spawnCounter = 0;
        }

        // UPDATE ZOMBIE & CEK TABRAKAN
        for(int i = 0; i < zombies.size(); i++) {
            Zombie z = zombies.get(i);
            z.update();

            // Cek Kena Peluru
            for(int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                if(z.getBounds().intersects(b.getBounds())) {
                    z.alive = false;
                    b.alive = false;
                }
            }

            // Cek Gigit Player (Game Over Logic sederhana)
            if(z.getBounds().intersects(player.getBounds())) {
                System.out.println("ADUH DIGIGIT! HP BERKURANG");
            }

            if(!z.alive) {
                zombies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        
        for(Bullet b : bullets) b.draw(g2);
        for(Zombie z : zombies) z.draw(g2);

        g2.dispose();
    }
}