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

    private final ArrayList tembok = new ArrayList();
    private final ArrayList bola = new ArrayList();
    private final ArrayList finish = new ArrayList();
    private final ArrayList map = new ArrayList();
    private Pemain pemain;
    private int lebar = 0;
    private int tinggi = 0;
    private final int jarak = 20;
    private final String temp = "";
    private File Awal;
    private final ArrayList Allperintah = new ArrayList();

    public Peta(File file) {
        this.bacaPeta(file);
    }

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
                            }   x = 0;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // buat hapus background
        //tempat gmbar
        g.setColor(new Color(255, 255, 255)); // set panel warna putih
        g.fillRect(0, 0, this.getLebar(), this.getTinggi()); // set tinggi
        map.addAll(tembok);
        map.addAll(finish);
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
                            //buat loopin untuk menggerakan player sebanyak yang di inputkan
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
                JOptionPane.showMessageDialog(null, "Perintah tidak Dikenal");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Perintah Gagal");
        }
    }

    private boolean pemainNabrakTembok(Pixel pemain, String input) {
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

    public void isCompleted() {   
        int goal =0;
        int s;
        s = finish.size();
        for (int i = 0; i < finish.size(); i++) {
            Finish f = (Finish) finish.get(i);
            if (pemain.getPosisiX() == f.getPosisiX()-1 && pemain.getPosisiY() == f.getPosisiY()-1) {
                goal += 1;
            }
            if (goal == s) {
                JOptionPane.showMessageDialog(null, "Selesai");
            }
        }
    }

    public String getTeksPerintah() {
        return Integer.toString(Allperintah.size());
        //untuk GET Perintah dari teks yang di input
    }

    public void restartLevel() {
        Allperintah.clear();//restart jejak perintah
        finish.clear();//menngembalikan pemain ke posisi semula
        tembok.clear();//hapus tembok
        map.clear();//hapus peta 
        bacaPeta(Awal);//mengatur ulang peta
        repaint();//gambar ulang
    }
}
