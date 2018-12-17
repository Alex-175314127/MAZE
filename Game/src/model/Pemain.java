package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Pemain extends Sel {// kelas bawaan dari kelas Sel
    
     public Pemain(int x, int y) {
         /**
     * constructor Pemain Pada saat objek pemain dibuat, kita memberikan 2 nilai untuk
     * konstruktor yang nantinya akan digunakan untuk memberi nilai pada
     * attribut x dan y di class. Kesimpulannya, pada saat objek pemain dibuat, objek
     * tersebut sudah memiliki nilai x dan y.
     *
     */
        super(x, y);
        URL location = this.getClass().getResource("player.jpg");// memanggil gambar dengan nama file sesuai yang terdapat pada file
        ImageIcon gambar = new ImageIcon(location);       // mendeklarasikan object baru     
        this.setImage(gambar.getImage());//memanggil method getimage milik gambar melalui method setimage
    }

    public void Gerak(int x,int y) {        
        this.setPosisiX(this.getPosisiX() + x);
        this.setPosisiY(this.getPosisiY() + y);
        //untuk menentuakn titik koordinatnya
}

    int size() {
        throw new UnsupportedOperationException("Failed");// menampilkan pesan Failed
    }
}
