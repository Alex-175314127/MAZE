package model;

import java.awt.Image;

public class Sel {

    private int posisiX;//deklarasi variabel posisiX dengan tipe data integer dan bersifat private
    private int posisiY;//deklarasi variabel posisiY dengan tipe data integer dan bersifat private
    private Image image;//deklarasi variabel image dengan tipe data Image dan bersifat private
    private int Jarak = 20;//deklarasi variabel jarak dan bernilai 20 serta memiliki tipe data integer dan bersifat private

    public Sel(int x, int y) {
         /**
     * constructor Sel Pada saat objek sel dibuat, kita memberikan 2 nilai untuk
     * konstruktor yang nantinya akan digunakan untuk memberi nilai pada
     * attribut x dan y di class. Kesimpulannya, pada saat objek sel dibuat, objek
     * tersebut sudah memiliki nilai x dan y.
     *
     */
        this.posisiX = x;// variabel lokal posisiX yang sama dengan x
        this.posisiY = y;// variabel lokal posisiY yang sama dengan y
    }

    public int getPosisiX() {//method getPosisiX
        return posisiX;// nilai balik dari posisiX
    }

    public void setPosisiX(int posisiX) {//method setPosisiX dan memiliki parameter posisiX dengan tipe data integer
        this.posisiX = posisiX;// variabel lokal posisiX yang sama dengan posisiX
    }

    public int getPosisiY() {//method getPosisiY
        return posisiY;// nilai balik dari posisiY
    }

    public void setPosisiY(int posisiY) {//method setPosisiX dan memiliki parameter posisiY dengan tipe data integer
        this.posisiY = posisiY;//variabel lokal posisiY yang sama dengan posisiY
    }

    public Image getImage() {//method getimage
        return image;// nilai balik dari image
    }

    public void setImage(Image image) {//method setimage dan memiliki parameter image dengan tipe data image
        this.image = image;//variabel lokal image yang sama dengan image
    }

    public boolean cekKiriObjek(Sel Objek) {// mengecek posisi player sebelah kiri
        if (((this.getPosisiX() - Jarak) == Objek.getPosisiX()) &&
                (this.getPosisiY() == Objek.getPosisiY())) {
            return true;
        } else {
            return false;
             /**
         * jika this.getPosisiX() - Jarak) memiliki nilai yang sama dengan Objek.getPosisiX
         * dan jika this.getPosisiY() memiliki nilai yang sama dengan Objek.getPosisiY
         * maka akan mengembalikan nilai true 
         * tapijika tidak, maka akan mengembalikan nilai false
                     */
        }
    }

    public boolean cekKananObjek(Sel Objek) {
        if (((this.getPosisiX() + Jarak) == Objek.getPosisiX()) && (this.getPosisiY() == Objek.getPosisiY())) {
            return true;
        } else {
            return false;
            /**
         * jika this.getPosisiX() + Jarak) memiliki nilai yang sama dengan Objek.getPosisiX
         * dan jika this.getPosisiY() memiliki nilai yang sama dengan Objek.getPosisiY
         * maka akan mengembalikan nilai true 
         * tapijika tidak, maka akan mengembalikan nilai false
                     */
        }
    }

    public boolean cekAtasObjek(Sel Objek) {
        if (((this.getPosisiY() - Jarak) == Objek.getPosisiY()) && (this.getPosisiX() == Objek.getPosisiX())) {
            return true;
        } else {
            return false;
             /**
         * jika this.getPosisiX() - Jarak) memiliki nilai yang sama dengan Objek.getPosisiX
         * dan jika this.getPosisiY() memiliki nilai yang sama dengan Objek.getPosisiY
         * maka akan mengembalikan nilai true 
         * tapijika tidak, maka akan mengembalikan nilai false
                     */
        }
    }

    public boolean cekBawakObjek(Sel Objek) {
        if (((this.getPosisiY() + Jarak) == Objek.getPosisiY()) && (this.getPosisiX() == Objek.getPosisiX())) {
            return true;
        } else {
            return false;
             /**
         * jika this.getPosisiX() + Jarak) memiliki nilai yang sama dengan Objek.getPosisiX
         * dan jika this.getPosisiY() memiliki nilai yang sama dengan Objek.getPosisiY
         * maka akan mengembalikan nilai true 
         * tapijika tidak, maka akan mengembalikan nilai false
                     */
        }
    }

}
