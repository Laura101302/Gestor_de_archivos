/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Funciones;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Funciones_ClickDerecho {

    public static void clickDerecho(TilePane tilePane, TextArea textArea, Stage stageMain) {
        //*****Crear archivo y crear carpeta*****
        //Crea el menú que sale cuando se pulsa click derecho en cualquier parte
        ContextMenu contextMenu1 = new ContextMenu();
        MenuItem crear_archivo = new MenuItem("Crear archivo");
        MenuItem crear_carpeta = new MenuItem("Crear carpeta");
        contextMenu1.getItems().addAll(crear_archivo, crear_carpeta);

        tilePane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu1.show(tilePane, e.getScreenX(), e.getScreenY());
        });

        //Si pulsa en "Crear archivo" se llama a crear_archivo
        crear_archivo.setOnAction((event) -> {
            Funciones_MenuAbrir.nuevoArchivo("file", tilePane, textArea, stageMain); //Pasa "file" a nuevoArchivo
        });

        //Si pulsa en "Crear carpeta" se llama a crear_carpeta
        crear_carpeta.setOnAction((event) -> {
            Funciones_MenuAbrir.nuevoArchivo("directory", tilePane, textArea, stageMain); //Pasa "directory" a nuevoArchivo
        });
        //*****Fin crear archivo y crear carpeta*****

        tilePane.setOnMousePressed((event) -> { //Cierra el cualquier menú generado con click derecho cuando se pulsa click izquierdo
            contextMenu1.hide();
            //contextMenu2.hide();
        });
    }
}
