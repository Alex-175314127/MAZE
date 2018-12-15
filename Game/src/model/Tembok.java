
package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;


public class Tembok extends Pixel {
    
    public Tembok(int x, int y){
        super(x, y);
        URL location = this.getClass().getResource("tembok2.jpg");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }
}
