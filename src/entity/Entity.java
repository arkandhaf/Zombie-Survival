package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
    public double x, y;
    public int width, height;
    public double speed;
    public Color color;
    public boolean alive = true; // Status hidup/mati

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
        g2.setColor(color);
        g2.fillRect((int)x, (int)y, width, height);
    }

    // Untuk Cek Tabrakan (Hitbox)
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}