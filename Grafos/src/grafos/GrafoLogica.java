/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import Arboles.ArbolGeneral;
import Arboles.Elemento;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ssrs_
 */
public class GrafoLogica {

    private ArrayList<String> CoordVertices;
    private ArrayList<Integer> Vertices;
    private ArrayList<Integer>[] Adyacencia;
    ArrayList<Integer> visitado;
    private ArbolGeneral<Integer> arbolGeneral;
    Elemento<Integer> elemento;

    public void BFS(int raiz) {
        int cont = 0;
        ArrayList<Integer> orden = new ArrayList<>();
        ArrayList<Integer> marcado = new ArrayList<>();
        Queue<Integer> cola = new LinkedList<>();

        marcado.add(raiz);
        cola.add(raiz);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            orden.add(actual);
            cont++;
            ArrayList<Integer> vecinos = Adyacencia[actual - 1];
            if (vecinos != null) {
                for (Integer destino : vecinos) {
                    if (!marcado.contains(destino)) {
                        if (cont != 0) {
                            elemento = arbolGeneral.obtenerElemento(actual);
                            arbolGeneral.insertarHijo(elemento, destino);
                        }
                        marcado.add(destino);
                        cola.offer(destino);
                    }
                }
            }
        }
        System.out.println("Orden: ");
        for (Integer dato : orden) {
            System.out.print(dato + " ");
        }
    }

    public void crearRaiz(int raiz) {
        arbolGeneral = new ArbolGeneral<>();
        elemento = arbolGeneral.insertarRaiz(raiz);
    }

    public void asigVisitado() {
        this.visitado = new ArrayList();
    }

    public void profundidad(int valor) {

        if (!visitado.contains(valor)) {
            visitado.add(valor);
        }

        for (int i = 0; i < Adyacencia[valor - 1].size(); i++) {
            if (!visitado.contains(Adyacencia[valor - 1].get(i))) {
                elemento = arbolGeneral.obtenerElemento(valor);
                arbolGeneral.insertarHijo(elemento, Adyacencia[valor - 1].get(i));
                elemento = arbolGeneral.obtenerElemento(Adyacencia[valor - 1].get(i));
                System.out.println(arbolGeneral.obtenerPadre(elemento) + ":  " + Adyacencia[valor - 1].get(i));
                profundidad(Adyacencia[valor - 1].get(i));
            }

        }

    }

    /**
     * @return the Circulos
     */
    public ArrayList<String> getCoordVertices() {
        return CoordVertices;
    }

    public void setDatos() {
        CoordVertices = new ArrayList<>();
        Vertices = new ArrayList<>();
        visitado = new ArrayList<>();
        Adyacencia = null;
    }

    /**
     * @return the Circulos
     */
    public ArrayList<Integer> getVertices() {
        return Vertices;
    }

    /**
     * @return the Adyacencia
     */
    public ArrayList<Integer>[] getAdyacencia() {
        return Adyacencia;
    }

    /**
     * @param Adyacencia the Adyacencia to set
     */
    public void setAdyacencia(ArrayList<Integer>[] Adyacencia) {
        this.Adyacencia = Adyacencia;
    }

    /**
     * @return the arbolGeneral
     */
    public ArbolGeneral<Integer> getArbolGeneral() {
        return arbolGeneral;
    }
}
