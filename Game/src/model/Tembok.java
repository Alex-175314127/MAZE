
package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;


public class Tembok extends Sel {// kelas bawaan dari kelas Sel
    /**
     * constructor Tembok Pada saat objek Tembok dibuat, kita memberikan 2 nilai untuk
     * konstruktor yang nantinya akan digunakan untuk memberi nilai pada
     * attribut x dan y di class. Kesimpulannya, pada saat objek sel dibuat, objek
     * tersebut sudah memiliki nilai x dan y.
     * @param x
     * @param y 
     */
    public Tembok(int x, int y){
        super(x, y);
        URL location = this.getClass().getResource("tembok2.jpg");
        ImageIcon gambar = new ImageIcon(location);        
        this.setImage(gambar.getImage());
    }
}
