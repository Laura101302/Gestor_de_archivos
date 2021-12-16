/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.MenuCrear;

import gestor_de_archivos.Funciones.Funciones;
import gestor_de_archivos.Funciones.Funciones_MenuAbrir;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class FXML_MenuCrearController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtField;

    @FXML
    private Button create;

    @FXML
    private Label label;

    private TilePane tilepane;

    private Stage stageMain;

    private TextArea textArea;

    public void setStage(Stage s) {
        this.stageMain = s;
    }

    public void setTextArea(TextArea ta) {
        this.textArea = ta;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cambiarTexto(String texto, TilePane tp) { //Cambia el texto según se quiera crear un archivo o un directorio
        label.setText("Create " + texto);
        tilepane = tp;
    }

    public void cerrarVentana(MouseEvent event) { //Cierra la ventana clicando en "Cancel"
        Funciones.cerrarVentana(event);
    }

    public void habilitarBoton() { //Comprueba si hay algo en "txtField". Si hay algo, habilita el botón "Create", si no, lo deshabilita
        if (txtField.getLength() > 0) {
            create.setDisable(false); //Habilita el botón "Create"
        } else {
            create.setDisable(true); //Deshabilita el botón "Create"
        }
    }

    public void crearNuevo(MouseEvent event) throws IOException { //Crear nuevo archivo/directorio
        if (label.getText().contains("file")) {
            Funciones_MenuAbrir.crearArchivo(txtField, tilepane, stageMain, textArea);
        } else {
            Funciones_MenuAbrir.crearDirectorio(txtField, tilepane, stageMain, textArea);
        }
        cerrarVentana(event);
    }
}
