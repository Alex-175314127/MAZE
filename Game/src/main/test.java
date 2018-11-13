/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;
import model.Map;
import model.Tempat;

/**
 *
 * @author Alex
 */
public class test {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("INI GAME MAZE");
        f.setSize(500,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new Tempat());
    }
}
