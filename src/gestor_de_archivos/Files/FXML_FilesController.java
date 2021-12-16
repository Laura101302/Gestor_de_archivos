/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Files;

import gestor_de_archivos.Funciones.Funciones;
import gestor_de_archivos.Funciones.Funciones_ClickDerecho;
import gestor_de_archivos.Funciones.Funciones_MenuAbrir;
import gestor_de_archivos.Funciones.SingletonRutas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class FXML_FilesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TilePane tilePane;

    private TextArea textArea;

    private Stage stageMain;

    private Stage stageFiles;

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public void setStageMain(Stage stageMain) {
        this.stageMain = stageMain;
    }

    public void setStageFiles(Stage stageFStage) {
        this.stageFiles = stageFiles;
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    @FXML
    public void volver(MouseEvent k) throws IOException {
        SingletonRutas ruta = SingletonRutas.getInstancia();
        if (ruta.getRuta().equals("FILES/")) {
            Funciones.cerrarVentana(k);
            return;
        }
        ruta.setRutaAnterior();
        Node source = (Node) k.getSource();
        Stage stageAbrir = (Stage) source.getScene().getWindow();
        stageAbrir.setTitle(ruta.getRuta());
        Funciones_MenuAbrir.mostrarArchivos(tilePane, stageMain, textArea);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funciones_ClickDerecho.clickDerecho(tilePane, textArea, stageMain); //Llama a clickDerecho
    }
}
