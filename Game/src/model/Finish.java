

package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Finish extends Sel{
    /**
     * berfungsi menentukan finish dan membuat gambarnya berubah sesuai file
     * ynag telah di simpan
     * @param x
     * @param y 
     */
    public Finish(int x, int y){
        super(x, y);
        
        URL location = this.getClass().getResource("finish.png");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }
}
