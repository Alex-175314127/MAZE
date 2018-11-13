/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import javax.swing.JFrame;

/**
 *
 * @author Alex
 */
public class Map extends JFrame{
    int [][] peta = 
{ {1,1,1,1,1,1,1,1,1,1,1,1,1},
  {1,0,1,0,1,0,1,0,0,0,0,0,1},
  {1,0,1,0,0,0,1,0,1,1,1,0,1},
  {1,0,0,0,1,1,1,0,0,0,0,0,1},
  {1,0,1,0,0,0,0,0,1,1,1,0,1},
  {1,0,1,0,1,1,1,0,1,0,0,0,1},
  {1,0,1,0,1,0,0,0,1,1,1,0,1},
  {1,0,1,0,1,1,1,0,1,0,1,0,1},
  {1,0,0,0,0,0,0,0,0,0,1,9,1},
  {1,1,1,1,1,1,1,1,1,1,1,1,1}
};
    public Map(){}
    
    public void Paint(Graphics g){
        super.paint(g);
        for (int i = 0; i < peta.length ; i++) {
            for (int j = 0; j < peta[0].length; j++) {
                Color warna;
                if (peta[i] ==  peta[) {
                    
                }
            }
        }
    }
}
