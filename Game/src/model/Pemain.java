package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Pemain extends Sel {
    
     public Pemain(int x, int y) {
        super(x, y);
        URL location = this.getClass().getResource("player.jpg");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }

    public void Gerak(int x,int y) {        
        this.setPosisiX(this.getPosisiX() + x);
        this.setPosisiY(this.getPosisiY() + y);
        //untuk menentuakn titik koordinatnya
}

    int size() {
        throw new UnsupportedOperationException("Failed");
    }
}
