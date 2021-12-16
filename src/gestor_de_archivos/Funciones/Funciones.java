/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Funciones;

import gestor_de_archivos.Files.FXML_FilesController;
import gestor_de_archivos.Gestor_de_archivos;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Funciones {

    //*****Funciones menú principal*****
    public static void menuAbout() { //Función para abrir el menú "About" dentro de "Info"
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos.class.getResource("Info/FXML_Info.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL); //Deja modificar la ventana de debajo
            stage.setTitle("About");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void menuAbrir(TextArea textArea, Stage stageMain) { //Función para abrir el menú "Abrir" dentro de "File"
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos.class.getResource("Files/FXML_Files.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            FXML_FilesController filesController = fxmlLoader.getController();
            filesController.setStageFiles(stage);
            filesController.setStageMain(stageMain);
            filesController.setTextArea(textArea);
            Funciones_MenuAbrir.mostrarArchivos(filesController.getTilePane(), stageMain, textArea);
            stage.initModality(Modality.APPLICATION_MODAL); //No deja modificar la ventana de debajo
            stage.setTitle("FILES/");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void menuGuardar(TextArea textArea, TilePane tilePane, Stage stageMain) throws IOException { //Función para abrir el menú "Guardar" dentro de "File", y guardar
        SingletonRutas ruta = SingletonRutas.getInstancia();
        String texto = textArea.getText();
        if (stageMain.getTitle().equals("Nuevo Archivo")) {
            Funciones_MenuAbrir.nuevoArchivo("file", tilePane, textArea, stageMain); //Pasa "file" a nuevoArchivo
        } else {
            Files.write(Paths.get(ruta.getRuta()), texto.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    //*****Fin funciones menú principal*****

    //*****Funciones menú crear nuevo archivo/directorio*****
    public static void cerrarVentana(MouseEvent event) { //Cierra la ventana clicando en "Cancel"
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    //*****Fin funciones menú crear nuevo archivo/directorio*****

    //*****Funciones nombre de ventana*****
    public static void nombreVentana(Stage stage) {
        stage.setTitle("Nuevo Archivo");
    }
    //*****Fin funciones nombre de ventana*****
}
