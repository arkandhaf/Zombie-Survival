package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage; // <--- INI BARU (Buat nampung gambar)

public abstract class Entity {
    public double x, y;
    public int width, height;
    public double speed;
    public Color color;
    public boolean alive = true;
    
    public BufferedImage image; // <--- INI VARIABEL BARU

    public Entity(double x, double y, int width, int height, double speed, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public abstract void update();
    
    public void draw(Graphics2D g2) {
        // LOGIKA GAMBAR:
        // Kalau ada gambar (image != null), kita gambar pakai image.
        // Kalau gak ada (null), kita gambar kotak biasa (misal buat peluru).
        if (image != null) {
            g2.drawImage(image, (int)x, (int)y, width, height, null);
        } else {
            g2.setColor(color);
            g2.fillRect((int)x, (int)y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}