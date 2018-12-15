package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Pemain extends Pixel {
    
     public Pemain(int x, int y) {
        super(x, y);
        URL location = this.getClass().getResource("mario.jpg");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }

    public void Gerak(int x,int y) {        
        this.setPosisiX(this.getPosisiX() + x);
        this.setPosisiY(this.getPosisiY() + y);
        //untuk menentuakn titik koordinatnya
}

    int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
