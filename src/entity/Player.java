package entity;

import inputs.KeyHandler; // <--- Wajib Import karena beda package
import java.awt.Color;

public class Player extends Entity {
    KeyHandler keyH;

    public Player(int x, int y, KeyHandler keyH) {
        super(x, y, 32, 32, 4, Color.BLUE); // Player = Kotak Biru
        this.keyH = keyH;
    }

    @Override
    public void update() {
        if(keyH.upPressed) y -= speed;
        if(keyH.downPressed) y += speed;
        if(keyH.leftPressed) x -= speed;
        if(keyH.rightPressed) x += speed;
    }
}