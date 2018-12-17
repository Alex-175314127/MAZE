package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class Peta extends JPanel {

    private final ArrayList tembok = new ArrayList();// deklarasi atribut tembok menngunakan arraylist
    private final ArrayList finish = new ArrayList();// deklarasi attribut finish menggunakan arraylist
    private final ArrayList map = new ArrayList(); // deklarasi atrribut map menggunakan arraylist
    private Pemain pemain; // deklarasi atribut pemain dengan tipe Pemain, dan bersifat private
    private int lebar = 0; // deklarasi atribut lebar dengan tipe integer, dan bersifat private
    private int tinggi = 0; // deklarasi atribut tinggi dengan tipe  integer, dan bersifat private
    private final int jarak = 20; // deklarasi atribut jarak dengan tipe integer, bernilai final dan bersifat private
    private final String temp = "";// deklarasi atribut temp dengan tipe String,bernilai final dan bersifat private
    private File Awal; // deklarasi atribut awal dengan tipe File dan bersifat private. 
    private final ArrayList Allperintah = new ArrayList(); // deklarasi atribut Allperintah mengunakan arraylist

    /**
     * 
     * constructor Peta Pada saat objek peta dibuat, kita memberikan nilai untuk
     * konstruktor yang nantinya akan digunakan untuk memberi nilai pada
     * attribut file di class. Kesimpulannya, pada saat objek peta dibuat, objek
     * tersebut sudah memiliki nilai file.
     *
     * @param file
     */
    public Peta(File file) {
        this.bacaPeta(file);
    }

    /**
     * method bacapeta method ini berfungsi untuk membaca data dari lokasi file
     * disimpan. method ini memiliki parameter file dengan tipe data File. pada
     * method ini user mendeklarasikan perintah yang akan digunkan untuk membaca
     * file.
     *
     * @param file
     */
    public void bacaPeta(File file) {
        try {
            if (file != null) { //fungsi ini terpanggil apabila file tidak sama kosong, dan dapat dibaca oleh fileinputstream
                FileInputStream input = new FileInputStream(file); // digunakan untuk membaca secara stream file dari index yang telah disimpan pada file.
                Awal = file; //deklarasi variabel awal yang memiliki nilai sama dengan atribut file
                int x = 0; //deklarasi nilai dari variabel x dengan type integer yang bernilai nol
                int y = 0;//deklarasi nilai dari variabel y dengan type integer yang bernilai nol

                Tembok tmbk; //deklarasi variabel tmbk dengan tipe Tembok yang mana mempunyai hubungan dengan kelas Tembok
                Finish a; //deklarasi variabel a dengan tipe data Finish yang mana mempunyai hubungan dengan kelas Finish
                int data; //deklarasi variabel data dengan tipe integer 
                while ((data = input.read()) != -1) {
                    //mendeklarasikan perintah untuk membaca inputan titik koordinat dari peta yang ada
                    //setiap item akan dapat dibaca sejumlah atau sebanyak item yang ada.
                    char item = (char) data;
                    //mendeklarasikan variabel item dengan tipe char mempunyai nilai yang sama dengan variabel data yang memiliki tipe data char juga
                    switch (item) {
                        case '\n': //nilai yang akan dicocokkan dengan isi value.
                            y += jarak; // mendeklarasikan nilai y yang disimpan pada y+jarak
                            if (this.lebar < x) {
                                this.lebar = x;
                                //jika nilai lebar lebih besar  dari x maka nilai dari lebar sama dengan nilai x
                            }
                            x = 0; // nilai x nol
                            break; //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                        case '#': //nilai yang akan dicocokkan dengan isi value.
                            tmbk = new Tembok(x, y);
                            tembok.add(tmbk);
                            x += jarak;
                            break; //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                        case 'o'://nilai yang akan dicocokkan dengan isi value.
                            a = new Finish(x, y);
                            finish.add(a);
                            x += jarak;
                            break; //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                        case '@'://nilai yang akan dicocokkan dengan isi value.
                            pemain = new Pemain(x, y);
                            x += jarak;
                            break; //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                        case '.'://nilai yang akan dicocokkan dengan isi value.
                            x += jarak;
                            break; //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                        default: //bersifat optional. dieksekusi jika value tidak cocok dengan salah satu constanta yang tersedia.
                            break;
                        //perintah untuk mengakhiri statement. 
                        //Apabila tidak ada break, komputer akan mengeksekusi intruksi yang berada di bawahnya walaupun berada di case yang berbeda.
                    }
                    tinggi = y;

                    /**
                     * Setiap cabang akan dijalankan jika syarat nilai case
                     * tersebut dipenuhi dan default akan dijalankan jika semua
                     * cabang diatasnya tidak terpenuh. Pernyataan break
                     * menunjukan bahwa perintah siap keluar dari switch. Jika
                     * pernyataan ini tidak ada, maka program akan diteruskan
                     * kecabang – cabang yang lainnya.
                     *
                     */
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Peta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method ini berfungsi untuk meletakkan proses penggambaran pada method
     * paintComponent (melakukan override). Method ini akan dipanggil secara
     * otomatis ketika sebuah frame (window) menjadi visible atau di-resize.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // buat hapus background
        //tempat gmbar
        g.setColor(new Color(255, 255, 255)); // set panel warna putih
        g.fillRect(0, 0, this.getLebar(), this.getTinggi()); // set tinggi
        map.addAll(tembok);
        map.addAll(finish);
        map.add(pemain);
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) != null) {
                Sel item = (Sel) map.get(i);
                g.drawImage(item.getImage(), item.getPosisiX(), item.getPosisiY(), this);// untuk buat gambarnya
            }
        }
    }

    /**
     * mengabil data dari variabel lebar
     *
     * @return
     */
    public int getLebar() {// method getLebar
        return lebar;// mengembalikan nilai dari variabel lebar yang mana merupakan variabel local 
    }

    /**
     * megambil data dari Variable tinggi
     *
     * @return
     */
    public int getTinggi() {// method getTinggi
        return tinggi;// mengembalikan nilai dari variabel tinggi yang mana merupakan variabel local
    }

    /**
     *  method gerakpemain berfungsi untuk menampung perintah yang akan
         * digunakan untuk menggerapkan player sesuai dengan perintah yang
         * diinput user. perintah-perintah tersebut akan ditentukan seperti udlr
         * u = up (perintah untuk gerak keatas) l =left ( perintah untuk gerak
         * ke kiri) d = down (perintah untuk gerak kekbawah) r= right (perintah
         * untuk gerak ke kanan)
     *
     * @param input
     */
    public void GerakPemain(String input) {
        String in[] = input.split(" ");
        if (in.length > 2) {
            JOptionPane.showMessageDialog(null, "Perintah harus berupa huruf u ,d ,r ,l , "
                    + "spasi dan diikuti langkah");
        } else if (in.length == 2) {
            if (in[0].matches("[udrlz]")) {
                Allperintah.add(input);
                if (in[0].equalsIgnoreCase("u")) {
                     /**
                     * jika perintah pada index nol adalah u ,maka objek string
                     * yang bersangkutan akan dibandingkan dengan objek string,
                     * pada parameter fungsi ini, dengan tanpa memperdulikan
                     * perbedaan antara huruf besar dengan huruf kecil.
                     */
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        /*
                        * kita dapat menggunakan static method valueOf() atau ParseInt()
                        * untuk konversi dari angka ke String dapat gunakan static method
                        * valueOf() milik String
                         */
                        if (pemainTembok(pemain, "u")) {
                            // jika pemain adalah u, maka akan mengembalikan nilainya ,
                            //kemudian pemaindapat bergerak sesuai dengan perintah u uang diinput
                            return;
                        } else {
                            pemain.Gerak(0, -jarak);// memanggil method gerak milik pemain
                            repaint();// memangil method repaint untuk menggambar ulang panel
                            //buat loopin untuk menggerakan player sebanyak yang di inputkan
                        }
                    }
                    isCompleted();
                    /**
                     * untuk menerjemahkan input yang di masukan agar pemain
                     * dapat bergerak
                     */
                } else if (in[0].equalsIgnoreCase("d")) {
                    /**
                     * jika perintah pada index nol adalah d ,maka objek string
                     * yang bersangkutan akan dibandingkan dengan objek string,
                     * pada parameter fungsi ini, dengan tanpa memperdulikan
                     * perbedaan antara huruf besar dengan huruf kecil.
                     */
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                         /*
                        * kita dapat menggunakan static method valueOf() atau ParseInt()
                        * untuk konversi dari angka ke String dapat gunakan static method
                        * valueOf() milik String
                         */

                        if (pemainTembok(pemain, "d")) {
                            // jika pemain adalah u, maka akan mengembalikan nilainya ,
                            //kemudian pemaindapat bergerak sesuai dengan perintah d yang diinput
                            return;
                        } else {
                            pemain.Gerak(0, jarak);// memanggil method gerak milik pemain
                            repaint();// memangil method repaint untuk menggambar ulang panel
                        }

                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("r")) {
                    /**
                     * jika perintah pada index nol adalah r ,maka objek string
                     * yang bersangkutan akan dibandingkan dengan objek string,
                     * pada parameter fungsi ini, dengan tanpa memperdulikan
                     * perbedaan antara huruf besar dengan huruf kecil.
                     */
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                         /*
                        * kita dapat menggunakan static method valueOf() atau ParseInt()
                        * untuk konversi dari angka ke String dapat gunakan static method
                        * valueOf() milik String
                         */
                        if (pemainTembok(pemain, "r")) {
                            // jika pemain adalah r, maka akan mengembalikan nilainya ,
                            //kemudian pemain dapat bergerak sesuai dengan perintah r yang diinput
                            return;

                        } else {
                            pemain.Gerak(jarak, 0);// memanggil method gerak milik pemain
                            repaint();// memangil method repaint untuk menggambar ulang panel
                        }
                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("l")) {
                     /**
                     * jika perintah pada index nol adalah l ,maka objek string
                     * yang bersangkutan akan dibandingkan dengan objek string,
                     * pada parameter fungsi ini, dengan tanpa memperdulikan
                     * perbedaan antara huruf besar dengan huruf kecil.
                     */
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        /*
                        * kita dapat menggunakan static method valueOf() atau ParseInt()
                        * untuk konversi dari angka ke String dapat gunakan static method
                        * valueOf() milik String
                         */
                        if (pemainTembok(pemain, "l")) {
                            // jika pemain adalah l, maka akan mengembalikan nilainya ,
                            //kemudian pemain dapat bergerak sesuai dengan perintah r yang diinput
                            return;
                        } else {
                            pemain.Gerak(-jarak, 0);// memanggil method gerak milik pemain
                            repaint();// memangil method repaint untuk menggambar ulang panel
                        }

                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("z")) {
/**
                     * jika perintah pada index nol adalah z ,maka objek string
                     * yang bersangkutan akan dibandingkan dengan objek string,
                     * pada parameter fungsi ini, dengan tanpa memperdulikan
                     * perbedaan antara huruf besar dengan huruf kecil.
                     */
                }
            } else {
                JOptionPane.showMessageDialog(null, "Perintah tidak Dikenal");
                 /**
                     * menampilkan pesan ketika salah memasukan perintah
                     */             
            }
        } else {
            JOptionPane.showMessageDialog(null, "Perintah Gagal");
            /**
                     * menampilkan pesan ketika gagal memasukan perintah
                     */
        }
    }

    /**
     * * method ini berfungsi untuk mengecek posisi tembok
          * dengan perintah yang diinput 
          * apabila saat perintah dijalankan maka player
          * akan berhenti dengan perintah break.
     *
     * @param pemain
     * @param input
     * @return
     */
    private boolean pemainTembok(Sel pemain, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//Cek posisi Tembok
                if (pemain.cekKiriObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//Cek posisi Tembok
                if (pemain.cekKananObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//Cek posisi Tembok
                if (pemain.cekAtasObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//Cek posisi Tembok
                if (pemain.cekBawakObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;//default return false
    }

    /**
     * menampilakan pesan jika pemain selesai sampai di finish
     */
    public void isCompleted() {
        int O = 0;
        Pemain s = (Pemain) pemain;
       
        for (int i = 0; i < finish.size(); i++) {
            Finish f = (Finish) finish.get(i);
            if (pemain.getPosisiX() == f.getPosisiX() && pemain.getPosisiY() == f.getPosisiY()) {
                O += 1;
            } if (O == finish.size()) {
                JOptionPane.showMessageDialog(null, "Selesai");
            }
        }
    }

    /**
     * berfungsi mengambil data dari perintah-perintah yang di inputkan
     *
     * @return
     */
    public String getTeksPerintah() {
        return Integer.toString(Allperintah.size());
        //untuk GET Perintah dari teks yang di input
    }

    /**
     * befungsi untuk restar game dan mengembalikan semua komponen ke posisi
     * semula
     */
    public void restartLevel() {
        Allperintah.clear();//restart jejak perintah
        finish.clear();//menngembalikan pemain ke posisi semula
        tembok.clear();//hapus tembok
        map.clear();//hapus peta 
        bacaPeta(Awal);//mengatur ulang peta
        repaint();//gambar ulang
    }
}
