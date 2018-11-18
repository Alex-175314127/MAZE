/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Sel;

/**
 *
 * @author user only
 */
public class GameFrame extends JFrame {

    private TempatPanel tempatPanel;
    
    private JLabel perintahlabel;
    private JTextField perintahText;
    private JButton okButton;
    private JButton kanan;
    private JButton kiri;
    private JButton tambah;
    private JButton hapus;
    private JButton atas;
    private JButton bawah;
    private JButton kananBawah;
    private JButton kananAtas;
    private JButton kiriBawah;
    private JButton kiriAtas;
    
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem bacaKonfigurasiMenuItem;
    
    public GameFrame(String title) {
        this.setTitle(title);
        this.init();
    }
    
    public GameFrame(String title, TempatPanel tempatPanel) {
        setTitle(title);
        this.tempatPanel = tempatPanel;
        this.init();
    }
    
    public void init() {
        // set ukuran dan layout
        this.setSize(1500, 850);
        this.setLayout(new BorderLayout());

        // set menu Bar
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Game");
        exitMenuItem = new JMenuItem("Keluar");
        bacaKonfigurasiMenuItem = new JMenuItem("Baca");
        gameMenu.add(bacaKonfigurasiMenuItem);
        gameMenu.add(exitMenuItem);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);      

        //action perform for exitMenuItem
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        );

        // panel selatan
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        
        this.perintahlabel = new JLabel("Perintah");
        southPanel.add(perintahlabel);
        
        this.perintahText = new JTextField(20);
        southPanel.add(perintahText);
        
        this.okButton = new JButton("OK");
        southPanel.add(okButton);
        
        this.kanan = new JButton("Kanan");
        southPanel.add(kanan);
        
        kanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pindahKanan();
            }
        });

        // set contentPane
        Container cp = this.getContentPane();
        if (tempatPanel != null) {
            cp.add(getTempatPanel(), BorderLayout.CENTER);
        }
        cp.add(southPanel, BorderLayout.SOUTH);

        // set visible= true
        this.setVisible(true);
        
        this.kiri = new JButton("kiri");
        southPanel.add(kiri);
        kiri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pindahKiri();
            }
        });
        
        this.tambah = new JButton("Tambah Bola");
        southPanel.add(tambah);
        tambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TambahBola();
            }            
        });
        
        this.hapus = new JButton("Hapus Bola");
        southPanel.add(hapus);
        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HapusBola();
            }
        });
        
        this.atas = new JButton("atas");
        southPanel.add(atas);
        atas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keAtas();
            }
        });
        
        this.bawah = new JButton("bawah");
        southPanel.add(bawah);
        bawah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keBawah();
            }
        });
        
        this.kananBawah = new JButton("Kanan Bawah");
        southPanel.add(kananBawah);
        kananBawah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KananBawah();
            }
        });
        this.kananAtas = new JButton("Kanan Atas");
        southPanel.add(kananAtas);
        kananAtas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KananAtas();
            }
        });
        this.kiriBawah = new JButton("Kiri Bawah");
        southPanel.add(kiriBawah);
        kiriBawah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KiriBawah();
            }
        });
        
        this.kiriAtas = new JButton("Kiri Atas");
        southPanel.add(kiriAtas);
        kiriAtas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KiriAtas();
            }
        });
              
    }

    /**
     * Fungsi untuk memindahkan sel dan menggambar ulang
     */
    public void pindahKanan() {
        // posisiX seluruh sel ditambah 20
        // sehingga sel akan terlihat bergerak ke kanan
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            // set posisiX yang baru
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKanan();
        }
        // gambar ulang tempat Panel
        getTempatPanel().repaint();
    }

    public void pindahKiri(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKiri();
        }
        getTempatPanel().repaint();
    }
    /**
     * @return the tempatPanel
     */
    public TempatPanel getTempatPanel() {
        return tempatPanel;
    }

    /**
     * @param tempatPanel the tempatPanel to set
     */
    public void setTempatPanel(TempatPanel tempatPanel) {
        this.tempatPanel = tempatPanel;
    }

    private void TambahBola() {
        tempatPanel.getTempat().tambahSel(new Sel(0, 0, 25, 25, '#', Color.RED));
        getTempatPanel().repaint();
    }
    
    private void HapusBola(){
        tempatPanel.getTempat().hapusSel();
        getTempatPanel().repaint();
    }
    
    private void keAtas(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserAtas();
        }
        getTempatPanel().repaint();
    }
    
    private void keBawah(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserBawah();
        }
        getTempatPanel().repaint();
    }
    
    private void KananBawah(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKanan();
            getTempatPanel().getTempat().getDaftarSel().get(i).geserBawah();
        }
        getTempatPanel().repaint();
    }
    
    private void KananAtas(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKanan();
            getTempatPanel().getTempat().getDaftarSel().get(i).geserAtas();
        }
        getTempatPanel().repaint();
    }
    private void KiriAtas(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKiri();
            getTempatPanel().getTempat().getDaftarSel().get(i).geserAtas();
        }
        getTempatPanel().repaint();
    }
    private void KiriBawah(){
        for (int i = 0; i < getTempatPanel().getTempat().getDaftarSel().size(); i++) {
            getTempatPanel().getTempat().getDaftarSel().get(i).geserKiri();
            getTempatPanel().getTempat().getDaftarSel().get(i).geserBawah();
        }
        getTempatPanel().repaint();
    }
}
