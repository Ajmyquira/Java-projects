package simulador.de.memoria;

import java.awt.Color;
import java.util.ArrayList;


public class Proceso {
    private String nombre;
    private String estado = "Nuevo";
    private int tiempo;
    private int tamano;
    private Color color;
    private int id = 0;

    public Proceso() {
        ArrayList <String>nombres = new ArrayList<String>();
        nombres.add("Word");
        nombres.add("Excel");
        nombres.add("PowerPoint");
        nombres.add("BlockNotas");
        nombres.add("Paint");
        nombres.add("Chrome");
        nombres.add("Opera");
        nombres.add("NetBeans");
        nombres.add("CorelDraw");
        nombres.add("Publisher");
        this.nombre = nombres.get(aleatorio(0,nombres.size()-1));
        this.estado = "Listo";
        this.tiempo = aleatorio(40,80);
        this.tamano = aleatorio(50,100);
        this.id = aleatorio(1000,9999);
        this.color = new Color(aleatorio(0,254),aleatorio(0,254),aleatorio(0,254));
    }
    
    private int aleatorio(int inf, int sup){  //mejorar!!!
        int n;
        do {
            n = (int) (Math.random()*10000);
        } while (n < inf || n > sup);
        return n;
    }

    @Override
    public String toString() {
        return "Proceso{" + "nombre = " + nombre + ", estado = " + estado + ","
                + "tiempo = " + tiempo + ", tamano = " + tamano + '}';
    }
    
    public void verificarId(ArrayList <Proceso> procesos){
        boolean ban = false;
        for(Proceso a : procesos){
            if(a.id == this.id){
                ban = true;
            }
        }
        if(ban){
            this.id = aleatorio(1000,9000);
            verificarId(procesos);
        }
    }
    
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
