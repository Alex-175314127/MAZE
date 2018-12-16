/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author user only
 */
public class Tempat extends JPanel implements ActionListener{

    private int tinggi; // tinggi tempat Game
    private int lebar;  // lebar tempat Game
    private ArrayList<Sel> daftarSel; // daftar sel

    private String isi; // isi file konfigurasi

    public static int batasKanan;
    public static int batasBawah;

    public Tempat() {
        daftarSel = new ArrayList<Sel>();
    }

    /**
     * Fungsi pembaca file konfigurasi. Hasil pembacaan file akan disimpan di
     * atribut 'isi' dan juga di atribut daftarSel
     *
     * @param file
     */
    public void bacaKonfigurasi(File file) {
        FileInputStream fis = null;
        int data;
        String hasil = "";
        boolean isi = false;
        int baris = 0;
        int kolom = 0;
        Tempat tmp = new Tempat();
        try {
            fis = new FileInputStream(file);
            while ((data = fis.read()) != -1) {
                hasil = hasil + (char) data;
                if ((char) data != '\n') {
                    if ((char) data != '\t') {
                      Sel sel = new Sel(baris, kolom, (char) data);
                        this.tambahSel(sel);
                        kolom++;
                    }
                } else {
                    kolom = 0;
                    baris++;
                }
                this.setIsi(hasil);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tempat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fungsi penambah daftar sel.
     *
     * @param sel
     */
    public void tambahSel(Sel sel) {
        daftarSel.add(sel);
    }
    
    public void hapusSel(){
        if (!daftarSel.isEmpty()) {
            daftarSel.remove(daftarSel.size() -1);
        }
    }

    /**
     * @return the tinggi
     */
    public int getTinggi() {
        return tinggi;
    }

    /**
     * @param tinggi the tinggi to set
     */
    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    /**
     * @return the lebar
     */
    public int getLebar() {
        return lebar;
    }

    /**
     * @param lebar the lebar to set
     */
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    /**
     * @return the daftarSel
     */
    public ArrayList<Sel> getDaftarSel() {
        return daftarSel;
    }

    /**
     * @param daftarSel the daftarSel to set
     */
    public void setDaftarSel(ArrayList<Sel> daftarSel) {
        for (int i = 0; i < daftarSel.size(); i++) {
            if (daftarSel.get(i) == null) {
                
            }
        }
        this.daftarSel = daftarSel;
    }

    /**
     * @return the isi
     */
    public String getIsi() {
        return isi;
    }

    /**
     * @param isi the isi to set
     */
    public void setIsi(String isi) {
        this.isi = isi;
    }

    public void Pint(Graphics g){
        super.paint(g);
        g.fillRect(45, 60, 32, 32);
        g.setColor(Color.red);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
      repaint();
    }
}
