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

public class Peta extends JPanel {

    private ArrayList tembok = new ArrayList();
    private ArrayList bola = new ArrayList();
    private ArrayList gawang = new ArrayList();
    private ArrayList map = new ArrayList();
    private Pemain pemain;
    private int lebar = 0;
    private int tinggi = 0;
    private int jarak = 20;
    private String temp = "";
    private File Awal;
    private ArrayList Allperintah = new ArrayList();

    public Peta(File file) {
        this.setPeta(file);
    }

    public void setPeta(File file) {

        try {
            if (file != null) {
                FileInputStream input = new FileInputStream(file);
                Awal = file;
                int x = 0;
                int y = 0;
                Tembok tmbk;
              
                Gawang a;
                int data;
                while ((data = input.read()) != -1) { // untuk membaca inputan titik koordinat
                    char item = (char) data;
                    if (item == '\n') {
                        y += jarak;
                        if (this.lebar < x) {
                            this.lebar = x;
                        }
                        x = 0;
                    } else if (item == '#') {
                        tmbk = new Tembok(x, y);
                        tembok.add(tmbk);
                        x += jarak;
                    } else if (item == 'o') {
                        a = new Gawang(x, y);
                        gawang.add(a);
                        x += jarak;
                    } else if (item == '@') {
                        pemain = new Pemain(x, y);
                        x += jarak;
                    } else if (item == '.') {
                        x += jarak;
                    }
                    tinggi = y;

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Peta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // buat hapus background
        //tempat gmbar
        g.setColor(new Color(255, 255, 255)); // set panel warna putih
        g.fillRect(0, 0, this.getLebar(), this.getTinggi()); // set tinggi
        map.addAll(tembok);
        map.addAll(gawang);
        map.addAll(bola);
        map.add(pemain);
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) != null) {
                Pixel item = (Pixel) map.get(i);
                g.drawImage(item.getImage(), item.getPosisiX(), item.getPosisiY(), this);// untuk buat gambarnya
            }
        }
    }

    public int getLebar() {
        return this.lebar;
    }

    public int getTinggi() {
        return this.tinggi;
    }

    public void GerakPemain(String input) {
        String in[] = input.split(" ");
        if (in.length > 2) {
            JOptionPane.showMessageDialog(null, "Perintah harus berupa huruf [udlr], spasi dan diikuti langkah");
        } else if (in.length == 2) {
            if (in[0].matches("[udrlz]")) {
                Allperintah.add(input);
                if (in[0].equalsIgnoreCase("u")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainNabrakTembok(pemain, "u")) {
                            return;
                        } else {
                            pemain.Gerak(0, -jarak);
                            repaint();
                            //buat looping sebanyak yang di inputkan 
                        }
                    }

                } else if (in[0].equalsIgnoreCase("d")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainNabrakTembok(pemain, "d")) {
                            return;
                        }else {
                            pemain.Gerak(0, jarak);
                            repaint();
                        }

                    }
                } else if (in[0].equalsIgnoreCase("r")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainNabrakTembok(pemain, "r")) {
                            return;
                      
                        } else {
                            pemain.Gerak(jarak, 0);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("l")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (pemainNabrakTembok(pemain, "l")) {
                            return;
                        }else {
                            pemain.Gerak(-jarak, 0);
                            repaint();
                        }

                    }
                } else if (in[0].equalsIgnoreCase("z")) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Kata tidak Dikenal");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Jumlah kata hanya Satu");
        }
    }

    private boolean pemainNabrakTembok(Pixel pemain, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.cekKiriObjek(wall)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.cekKananObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.cekAtasObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);//ambil posisi tembok
                if (pemain.cekBawakObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;//default return false
    }

//    private boolean bolaNabrakBola(Pixel objek, String input) {
//        boolean bantu = false;
//        if (input.equalsIgnoreCase("l")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol = (Bola) bola.get(i);//ambil posisi bola
//                if (objek.cekKiriObjek(bol)) {
//                    bantu = true;
//                    break;
//                }
//            }
//        } else if (input.equalsIgnoreCase("r")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol = (Bola) bola.get(i);//ambil posisi bola
//                if (objek.cekKananObjek(bol)) {
//                    bantu = true;
//                    break;
//                }
//            }
//
//        } else if (input.equalsIgnoreCase("u")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol = (Bola) bola.get(i);//ambil posisi bola
//                if (objek.cekAtasObjek(bol)) {
//                    bantu = true;
//                    break;
//                }
//            }
//
//        } else if (input.equalsIgnoreCase("d")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol = (Bola) bola.get(i);//ambil posisi bola
//                if (objek.cekBawakObjek(bol)) {
//                    bantu = true;
//                    break;
//                }
//            }
//        }
//        return bantu;//default return false
//    }

//    private boolean cekBolaPemainTembok(String input) {
//        boolean bantu = false;
//        if (input.equalsIgnoreCase("l")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol1 = (Bola) bola.get(i);//ambil posisi bola
//                if (pemain.cekKiriObjek(bol1)) {//cek apakah pemain sebelah kiri bola ke i
//                    if (bolaNabrakBola(bol1, "l")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else if (pemainNabrakTembok(bol1, "l")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else {
//                        bol1.Gerak(-jarak, 0);//bola ikut bergerak ke kiri
//                        isCompleted();
//                    }
//                }
//            }
//        } else if (input.equalsIgnoreCase("r")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol1 = (Bola) bola.get(i);//ambil posisi bola
//                if (pemain.cekKananObjek(bol1)) {//cek apakah pemain sebelah kanan bola ke i
//                    if (bolaNabrakBola(bol1, "r")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else if (pemainNabrakTembok(bol1, "r")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else {
//                        bol1.Gerak(jarak, 0);//bola ikut bergerak ke kanan
//                        isCompleted();
//                    }
//                }
//            }
//        } else if (input.equalsIgnoreCase("u")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola bol1 = (Bola) bola.get(i);//ambil posisi bola
//                if (pemain.cekAtasObjek(bol1)) {//cek apakah bola 1 di atas pemain
//                    if (bolaNabrakBola(bol1, "u")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else if (pemainNabrakTembok(bol1, "u")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else {
//                        bol1.Gerak(0, -jarak);//bola ikut bergerak ke atas
//                        isCompleted();
//                    }
//                }
//            }
//        } else if (input.equalsIgnoreCase("d")) {
//            for (int i = 0; i < bola.size(); i++) {
//                Bola ball = (Bola) bola.get(i);//ambil posisi bola
//                if (pemain.cekBawakObjek(ball)) {//cek apakah bola 1 di bawah pemain
//                    if (bolaNabrakBola(ball, "d")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else if (pemainNabrakTembok(ball, "d")) {//cek apakah bola ke satu nabrak tembok.
//                        bantu = true;//ya, tidak boleh bergerak.
//                        break;//hentikan proses looping i
//                    } else {
//
//                        ball.Gerak(0, jarak);;//bola ikut bergerak ke bawah
//                        isCompleted();
//                    }
//                }
//            }
//        }
//        return bantu;
//    }

    public void isCompleted() {      
        int jumlahBola;//jumlah bola
        jumlahBola = pemain.size();
        int goal = 0;
        for (int i = 0; i < bola.size(); i++) {
            Pemain bol = (Pemain) bola.get(i);//ambil posisi bola
            for (int j = 0; j < gawang.size(); j++) {
                Gawang gaw = (Gawang) gawang.get(j);//ambil posisi gawang
                if (bol.getPosisiX() == gaw.getPosisiX() && bol.getPosisiY() == gaw.getPosisiY()) {//cek posisi bola sama dengan bola.
                    goal += 1;
                }
            }
        }
        if (goal == jumlahBola) {//jika semua gawang sudah terisi bola
            JOptionPane.showMessageDialog(null, "Selamat anda berhasil menyelesaikan game ini.");
        }
    }

    public String getTeksPerintah() {
        return Integer.toString(Allperintah.size());
        //buat ngambil semua perintah
    }

    public void restartLevel() {
        Allperintah.clear();//hapus semua perintah yang tersimpan
        gawang.clear();//hapus gawang
        bola.clear();//hapus bola
        tembok.clear();//hapus tembok
        map.clear();//hapus map
        

        setPeta(Awal);//set ulang gambar peta
        repaint();//gambar ulang
    }
}
