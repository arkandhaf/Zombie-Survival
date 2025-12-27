package entity;

import java.awt.Color;

public class Zombie extends Entity {
    Player target;

    public Zombie(double x, double y, Player target) {
        super(x, y, 32, 32, 2, Color.GREEN); // Zombie = Kotak Hijau, Lambat
        this.target = target;
    }

    @Override
    public void update() {
        // Logika Kejar Player
        double angle = Math.atan2(target.y - y, target.x - x);
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }
}