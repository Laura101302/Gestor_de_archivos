/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Info;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class FXML_InfoController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            start();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FXML_InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() throws Exception { //Establece la altura y la anchura de la foto igual que las del pane
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
    }
}
