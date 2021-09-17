/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Arboles.ArbolGeneral;
import Arboles.Elemento;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creado por hadexexplade el 01 de febrero del 2016
 */
public class DibujarArbol<E> {

    private int radio = 10;
    private int espacioVertical = 30;
    private ArbolGeneral<E> arbolGeneral;
    private HashMap<Elemento<E>, Point> coordenadas;
    JPanel P_Arbol;

    public DibujarArbol(ArbolGeneral<E> arbolGeneral) {
        this.arbolGeneral = arbolGeneral;
        this.coordenadas = new HashMap<Elemento<E>, Point>();
        //P_Arbol.setBackground(Color.gray);
    }

    public void dibujarComponente(Graphics g, int correr, JPanel P_Arbol) {
        System.out.println(arbolGeneral.getRaiz() + " raiz");
        if (!arbolGeneral.estaVacia()) {
            dibujar(g, arbolGeneral.getRaiz(), (P_Arbol.getWidth() / 2) + correr, 30, P_Arbol.getWidth() / 10);
        }
    }

    private void dibujar(Graphics g, Elemento<E> raiz, int x, int y, int espacioH) {
        g.setColor(Color.black);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        g.drawString(raiz.getElemento() + "", x - 6, y + 4);
        g.setColor(Color.black);
        ArrayList<Elemento<E>> hijos = arbolGeneral.obtenerHijos(raiz);
        coordenadas.put(raiz, new Point(x, y));
        for (Elemento<E> hijo : hijos) {
            Point punto = coordenadas.get(arbolGeneral.obtenerPadre(hijo));
            dibujarLinea(g, x - espacioH, y + espacioVertical, punto.x, punto.y);
            dibujar(g, hijo, x - espacioH, y + espacioVertical, espacioH / hijos.size());
            x += espacioH;
        }
    }


    private void dibujarLinea(Graphics g, int x1, int y1, int x2, int y2) {
        double d = Math.sqrt(espacioVertical * espacioVertical + (x2 - x1) * (x2 - x1));
        int xx1 = (int) (x1 - radio * (x1 - x2) / d);
        int yy1 = (int) (y1 - radio * (y1 - y2) / d);
        int xx2 = (int) (x2 + radio * (x1 - x2) / d);
        int yy2 = (int) (y2 + radio * (y1 - y2) / d);

        g.drawLine(xx1, yy1, xx2, yy2);

    }
}
