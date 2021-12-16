/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Main;

import gestor_de_archivos.Funciones.Funciones;
import gestor_de_archivos.Funciones.SingletonRutas;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea textArea;

    private Stage getStage() {
        return (Stage) textArea.getScene().getWindow();
    }

    @FXML
    private void about(ActionEvent k) { //Abre el menú "About" dentro de "Info"
        Funciones.menuAbout();
    }

    @FXML
    private void guardar(ActionEvent k) throws IOException {
        Funciones.menuGuardar(textArea, null, getStage()); //Pasa textArea, tilePane y stageMain a menuGuardar
    }

    @FXML
    private void borrar(ActionEvent k) {
        limpiarPantalla();
        SingletonRutas ruta = SingletonRutas.getInstancia();
        File file = new File(ruta.getRuta());
        file.delete();
        ruta.setClear();
    }

    @FXML
    private void abrir(ActionEvent k) { //Abre el menú "Abrir" dentro de "File"
        SingletonRutas ruta = SingletonRutas.getInstancia();
        ruta.setClear();
        Funciones.menuAbrir(textArea, getStage());
    }

    @FXML
    private void nuevoArchivo(ActionEvent k) {
        limpiarPantalla();
    }

    private void limpiarPantalla() { //Limpia la pantalla
        textArea.setText("");
        getStage().setTitle("Nuevo Archivo");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
