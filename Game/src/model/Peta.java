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
     * method Peta untuk menujukan ke method bacaPeta
     *
     * @param file
     */
    public Peta(File file) {
        this.bacaPeta(file);
    }

    /**
     * method ini berfungsi untuk membaca file map untuk di tampilan pada Frame
     * di game
     *
     * @param file
     */
    public void bacaPeta(File file) {

        try {
            if (file != null) {
                FileInputStream input = new FileInputStream(file);
                Awal = file;
                int x = 0;
                int y = 0;
                Tembok tmbk;

                Finish a;
                int data;
                while ((data = input.read()) != -1) { // untuk membaca inputan titik koordinat
                    char item = (char) data;
                    switch (item) {
                        case '\n':
                            y += jarak;
                            if (this.lebar < x) {
                                this.lebar = x;
                            }
                            x = 0;
                            break;
                        case '#':
                            tmbk = new Tembok(x, y);
                            tembok.add(tmbk);
                            x += jarak;
                            break;
                        case 'o':
                            a = new Finish(x, y);
                            finish.add(a);
                            x += jarak;
                            break;
                        case '@':
                            pemain = new Pemain(x, y);
                            x += jarak;
                            break;
                        case '.':
                            x += jarak;
                            break;
                        default:
                            break;
                    }
                    tinggi = y;

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Peta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * method ini berfungsi untuk menampilkan gambar pada sel di frame game dan
     * mengatur gambar sesuai konfigurasi di file map
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
    public int getLebar() {
        return lebar;
    }

    /**
     * megambil data dari Variable tinggi
     *
     * @return
     */
    public int getTinggi() {
        return tinggi;
    }

    /**
     * method ini berfungsi untuk mengatur pergerakan pemain sesuai perintah
     * yang di inputkan
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
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainTembok(pemain, "u")) {
                            return;
                        } else {
                            pemain.Gerak(0, -jarak);
                            repaint();
                            //buat loopin untuk menggerakan player sebanyak yang di inputkan
                        }
                    }
                    isCompleted();
                    /**
                     * untuk menerjemahkan input yang di masukan agar pemain
                     * dapat bergerak
                     */
                } else if (in[0].equalsIgnoreCase("d")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainTembok(pemain, "d")) {
                            return;
                        } else {
                            pemain.Gerak(0, jarak);
                            repaint();
                        }

                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("r")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainTembok(pemain, "r")) {
                            return;

                        } else {
                            pemain.Gerak(jarak, 0);
                            repaint();
                        }
                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("l")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainTembok(pemain, "l")) {
                            return;
                        } else {
                            pemain.Gerak(-jarak, 0);
                            repaint();
                        }

                    }
                    isCompleted();
                } else if (in[0].equalsIgnoreCase("z")) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Perintah tidak Dikenal");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Perintah Gagal");
        }
    }

    /**
     * berfungsi untuk memenentukan posisi tembok agar pemain tidak dpat
     * melewati tembok dan membuat player tidak bergerak jika ada tembok
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
