package entity;

import java.awt.Color;

public class Bullet extends Entity {
    double dx, dy;

    public Bullet(double startX, double startY, double targetX, double targetY) {
        super(startX, startY, 10, 10, 8, Color.YELLOW); // Peluru = Kotak Kecil Kuning
        
        // Hitung sudut tembak ke arah mouse
        double angle = Math.atan2(targetY - startY, targetX - startX);
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        
        // Hapus kalau keluar layar (biar memori gak penuh)
        if(x < 0 || x > 1000 || y < 0 || y > 1000) {
            alive = false;
        }
    }
}