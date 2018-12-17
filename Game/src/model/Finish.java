

package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Finish extends Sel{//// kelas bawaan dari kelas Sel
    /**
     * constructor Finish Pada saat objek Finish dibuat, kita memberikan 2 nilai untuk
     * konstruktor yang nantinya akan digunakan untuk memberi nilai pada
     * attribut x dan y di class. Kesimpulannya, pada saat objek Finish dibuat, objek
     * tersebut sudah memiliki nilai x dan y.
     * @param x
     * @param y 
     */
    public Finish(int x, int y){
        super(x, y);
        
        URL location = this.getClass().getResource("finish.png");// memanggil gambar dengan nama file sesuai yang terdapat pada file
        ImageIcon gambar = new ImageIcon(location);      // mendeklarasikan object baru  
        this.setImage(gambar.getImage());//memanggil method getimage milik gambar melalui method setimage
    }
}
