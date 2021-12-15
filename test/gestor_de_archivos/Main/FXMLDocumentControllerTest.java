/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Main;

import gestor_de_archivos.Funciones.Funciones;
import gestor_de_archivos.Gestor_de_archivos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author alumno
 */
public class FXMLDocumentControllerTest extends ApplicationTest {

    @Override
    public void start(Stage paramStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos.class.getResource("Main/FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            paramStage.setScene(scene);
            Funciones.nombreVentana(paramStage);
            paramStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void integrationTest_ClickOnAbout_MenuOpen_OK() throws InterruptedException {
        clickOn("#menInfo");
        clickOn("#menItemAbout");
        ImageView imageView = lookup("#imageView").query();
        org.junit.Assert.assertNotEquals(imageView, null);
    }

    @Test
    public void integrationTest_ClickOnGuardar_OK() throws InterruptedException {
        clickOn("#menFile");
        clickOn("#menItemGuardar");
    }

    @Test
    public void integrationTest_ClickOnAbrir_MenuOpen_OK() throws InterruptedException {
        clickOn("#menFile");
        clickOn("#menItemAbrir");
        TilePane tilePane = lookup("#tilePane").query();
        org.junit.Assert.assertNotEquals(tilePane, null);
    }

    //Hacer test ClickOnNuevoArchivo
    //Repasar que todos los archivos estén bien
    //Hacer el word de testing
    //Entregar antes de las 12
}
