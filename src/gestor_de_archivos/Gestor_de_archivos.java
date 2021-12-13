/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos;

import gestor_de_archivos.Funciones.Funciones;
import gestor_de_archivos.Funciones.SingletonRutas;
import gestor_de_archivos.Main.FXMLDocumentController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Gestor_de_archivos extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos.class.getResource("Main/FXMLDocument.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLDocumentController mainController = fxmlLoader.getController(); //Coge el controller de "Main"
        mainController.setTexto(stage); //Pasa el stage al DocumentController
        Scene scene = new Scene(root);
        

        SingletonRutas ins = SingletonRutas.getInstancia();
        stage.setScene(scene);
        System.out.println(ins);
        Funciones.nombreVentana(stage);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
