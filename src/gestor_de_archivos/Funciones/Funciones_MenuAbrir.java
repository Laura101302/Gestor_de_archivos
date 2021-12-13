/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor_de_archivos.Funciones;

import gestor_de_archivos.Gestor_de_archivos;
import gestor_de_archivos.MenuCrear.FXML_MenuCrearController;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alumno
 */
public class Funciones_MenuAbrir {

    public static void crearDirectorio(TextField txtField, TilePane tilePane, Stage stageMain, TextArea textArea) throws IOException { //Crear nuevo directorio
        SingletonRutas ruta = SingletonRutas.getInstancia();
        Path path = Paths.get(ruta.getRuta() + txtField.getText() + "/");
        try {
            Files.createDirectory(path); //Crea directorio
        } catch (IOException e) {
            //Carga directorio
        }
        mostrarArchivos(tilePane, stageMain, textArea); //Muestra todos los archivos
    }

    public static void crearArchivo(TextField txtField, TilePane tilePane, Stage stageMain, TextArea textArea) throws IOException { //Crear nuevo archivo
        SingletonRutas ruta = SingletonRutas.getInstancia();
        Path path = Paths.get(ruta.getRuta() + txtField.getText() + ".txt");

        if (tilePane != null) {
            try {
                Files.createFile(path); //Crea archivo
            } catch (IOException e) {
                //Carga directorio
            }
            mostrarArchivos(tilePane, stageMain, textArea); //Muestra todos los archivos
        } else {
            ruta.setClear(); //Limpia la ruta
            ruta.setRuta(txtField.getText() + ".txt");
            Files.write(Paths.get(ruta.getRuta()), textArea.getText().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            stageMain.setTitle(txtField.getText() + ".txt");
        }
    }

    public static void mostrarArchivos(TilePane tp, Stage stageMain, TextArea textArea) throws IOException { //Mostrar archivos y directorios
        tp.getChildren().clear();

        SingletonRutas ruta = SingletonRutas.getInstancia();
        List<Path> result;
        try (Stream<Path> paths = Files.walk(Paths.get(ruta.getRuta()), 1)) {
            result = paths.collect(Collectors.toList());
        }
        //Variables
        File file;

        //Imagenes
        Image imagenDirectorio = new Image(gestor_de_archivos.Gestor_de_archivos.class.getResource("Resources/carpeta.png").toString());
        Image imagenFile = new Image(gestor_de_archivos.Gestor_de_archivos.class.getResource("Resources/archivo.png").toString());

        //Poner imágenes
        for (int i = 1; i < result.size(); i++) {
            ImageView image_view = new ImageView();
            BorderPane border_pane = new BorderPane();
            Text nombre = new Text(); //Nombre del archivo/directorio
            file = result.get(i).toFile();

            if (file.isDirectory()) { //Comprueba si es un directorio
                CreateIcon(image_view, imagenDirectorio, border_pane, nombre, file, tp);
                editarCarpeta(border_pane, ruta, file, stageMain, tp, textArea);
            } else if (file.isFile()) { //Comprueba si es un archivo
                CreateIcon(image_view, imagenFile, border_pane, nombre, file, tp);
                editarFichero(border_pane, ruta, file, stageMain, tp, textArea);
            }
        }
    }

    private static void editarCarpeta(BorderPane border_pane, SingletonRutas ruta, File file, Stage stageMain, TilePane tp, TextArea textArea) {
        border_pane.setOnMouseClicked((MouseEvent event) -> { //Doble click
            if (!event.getButton().equals(MouseButton.PRIMARY)) {
                return;
            }
            if (event.getClickCount() == 2) { //Si se pulsa dos veces seguidas "border_pane", abre la carpeta seleccionada
                ruta.setRuta(file.getName()); //Lee el nombre de la carpeta
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.setTitle(ruta.getRuta());
                try {
                    mostrarArchivos(tp, stageMain, textArea);
                } catch (IOException ex) {
                    Logger.getLogger(Funciones_MenuAbrir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ContextMenu contextMenu = new ContextMenu(); //Crea un menú
        MenuItem borrar_carpeta = new MenuItem("Borrar");
        contextMenu.getItems().addAll(borrar_carpeta);
        border_pane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(border_pane, e.getScreenX(), e.getScreenY());
            e.consume();
        });
        borrar_carpeta.setOnAction(event -> {
            Path path = Paths.get(ruta.getRuta() + file.getName());
            List<Path> lista = null;
            try {
                lista = Files.walk(path).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                for(Path p:lista){
                    Files.deleteIfExists(p);
                }
                mostrarArchivos(tp, stageMain, textArea);
            } catch (IOException ex) {
                Logger.getLogger(Funciones_MenuAbrir.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static void editarFichero(BorderPane border_pane, SingletonRutas ruta, File file, Stage stageMain, TilePane tp, TextArea textArea) {
        border_pane.setOnMouseClicked((MouseEvent event) -> { //Doble click
            if (!event.getButton().equals(MouseButton.PRIMARY)) {
                return;
            }
            if (event.getClickCount() == 2) { //Si se pulsa dos veces seguidas "border_pane", abre el archivo seleccionado
                stageMain.setTitle(file.getName());
                ruta.setRuta(file.getName());
                byte[] bytes;
                try {
                    bytes = Files.readAllBytes(Paths.get(ruta.getRuta()));
                    String content = new String(bytes, StandardCharsets.UTF_8);
                    textArea.setText(content);
                } catch (IOException ex) {
                    Logger.getLogger(Funciones_MenuAbrir.class.getName()).log(Level.SEVERE, null, ex);
                }
                Funciones.cerrarVentana(event);
            }
        });
        ContextMenu contextMenu = new ContextMenu(); //Crea un menú
        MenuItem borrar_fichero = new MenuItem("Borrar");
        contextMenu.getItems().addAll(borrar_fichero);
        border_pane.setOnContextMenuRequested((ContextMenuEvent e) -> {
            contextMenu.show(border_pane, e.getScreenX(), e.getScreenY());
            e.consume();
        });
        borrar_fichero.setOnAction(event -> {
            File f = new File(ruta.getRuta() + file.getName()); //Recoge la ruta
            f.delete();
            try {
                mostrarArchivos(tp, stageMain, textArea);
            } catch (IOException ex) {
                Logger.getLogger(Funciones_MenuAbrir.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //Crear imágenes
    private static void CreateIcon(ImageView image_view, Image imagenDirectorio, BorderPane border_pane, Text nombre, File file, TilePane tp) {
        image_view.setImage(imagenDirectorio);
        image_view.setFitHeight(90);
        image_view.setFitWidth(90);
        border_pane.setCenter(image_view);
        nombre.setText(file.getName());
        border_pane.setBottom(nombre);
        border_pane.setAlignment(nombre, Pos.CENTER);
        tp.getChildren().addAll(border_pane);
    }

    //Abre el menú de crear nuevo archivo
    public static void nuevoArchivo(String texto, TilePane tp, TextArea textArea, Stage stageMain) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gestor_de_archivos.class.getResource("MenuCrear/FXML_MenuCrear.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXML_MenuCrearController crearController = fxmlLoader.getController(); //Coge el controller de "MenuCrear"
            crearController.cambiarTexto(texto, tp); //Llama al controller de "MenuCrear" para ejecutar la función "cambiarTexto"
            crearController.setStage(stageMain);
            crearController.setTextArea(textArea);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nuevo");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
