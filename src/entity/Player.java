package entity;

import inputs.KeyHandler;
import java.awt.Color;
import java.io.IOException;       // <--- Import buat baca file error
import javax.imageio.ImageIO;     // <--- Import buat baca gambar

public class Player extends Entity {
    KeyHandler keyH;

    public Player(int x, int y, KeyHandler keyH) {
        // Ubah ukuran jadi 48x48 (atau sesuaikan sama gambarmu) biar agak gedean
        // Color.BLUE kita biarin aja buat cadangan kalau gambar gagal loading
        super(x, y, 48, 48, 4, Color.BLUE); 
        this.keyH = keyH;

        getPlayerImage(); // <--- Panggil fungsi load gambar
    }

    public void getPlayerImage() {
        try {
            // UBAH DARI .png JADI .jpg DI SINI
            image = ImageIO.read(getClass().getResourceAsStream("/res/player.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(keyH.upPressed) y -= speed;
        if(keyH.downPressed) y += speed;
        if(keyH.leftPressed) x -= speed;
        if(keyH.rightPressed) x += speed;
    }
}