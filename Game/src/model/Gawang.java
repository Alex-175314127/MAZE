

package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Gawang extends Pixel{
    
    public Gawang(int x, int y){
        super(x, y);
        
        URL location = this.getClass().getResource("gawang_1.jpg");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }
}
