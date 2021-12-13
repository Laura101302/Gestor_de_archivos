/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Funciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class SingletonRutas {

    private static SingletonRutas ruta;
    private List lista;

    public static SingletonRutas getInstancia() {
        if (ruta == null) {
            ruta = new SingletonRutas();
        }
        return ruta;
    }

    private SingletonRutas() {
        this.lista = new ArrayList();
        this.lista.add("FILES");
    }

    public String getRuta() {
        String temporal = "";
        String ruta = "";
        for (int i = 0; i < lista.size(); i++) {
            ruta = lista.get(i) + "/";
            temporal += ruta;
        }
        return temporal;
    }
    
    public void setRuta(String nombreArchivo){ //Nueva ruta
        this.lista.add(nombreArchivo);
    }
    
    public void setClear(){ //Limpiar lista
        this.lista.clear();
        this.lista.add("FILES");
    }
    
    public void setRutaAnterior(){ //Volver atrás
        if(this.lista.size()>1){
            this.lista.remove(this.lista.size()-1);
        }
    }
}
