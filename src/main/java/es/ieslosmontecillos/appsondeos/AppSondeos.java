/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * @author Equipo Omega
 */
public class AppSondeos extends Application {

    @Override
    public void start(Stage stage) {

        cargaApp(stage);

    }

    // Métodos para que salga el SplashScreen
    private void cargaApp(Stage stage) {
        Stage splashStage = new Stage();

        StackPane splashLayout = new StackPane();

        Label splashLabel = new Label("App Sondeos\nEquipo Omega");

        Scene splashScene = new Scene(splashLayout, 300, 150);


        splashLayout.getChildren().add(splashLabel);


        splashScene.getStylesheets().add(AppSondeos.class.getResource("css/SplashScreen.css").toExternalForm());

        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.getIcons().add(new Image(AppSondeos.class.getResourceAsStream("img/icon.png")));
        splashStage.setScene(splashScene);
        splashStage.show();

        splashLayout.setId("layout");

        cargaPrincipal(splashStage, stage);
    }

    // Carga la ventana de la página de Inicio
    private void cargaPrincipal(Stage splashStage, Stage stage) {
        new Thread(() -> {
            try {
                // Simula la carga de la aplicación
                Thread.sleep(5000);

                // Cierra la pantalla de carga
                Platform.runLater(splashStage::close);

                // Iniciar la aplicación principal
                Platform.runLater(() -> {

                    // Stage
                    stage.getIcons().add(new Image(AppSondeos.class.getResourceAsStream("img/icon.png")));
                    stage.setResizable(false);
                    stage.setTitle("App Sondeos");
                    stage.setScene(escena());
                    stage.show();
                });

            } catch (InterruptedException e) {
                System.err.println("Error al cargar la pantalla");
            }
        }).start();
    }

    // Llamada a la escena para abrirse después de la Splash Screen
    private Scene escena() {
        // Lista que guarda los valores para el ChoiceBox
        ObservableList<String> encuestas = FXCollections.observableArrayList("Animales", "Comida", "Deporte", "Lectura", "Viajes");

        // Paneles
        // Base
        BorderPane root = new BorderPane();

        // Página bienvenida
        VBox principal = new VBox();

        // Encuestas
        TabPane seccionesEncuesta = new TabPane();


        // Llamada a las distintas secciones(Tabs) de las clases objeto
        Animales animal = new Animales();
        Comida comida = new Comida();
        Deportes deporte = new Deportes();
        Lectura lectura = new Lectura();
        Viajes viaje = new Viajes();

        // Creación de la escena
        Scene scene = new Scene(root, 775, 800);

        
        // Asigna el css en la escena
        scene.getStylesheets().add(AppSondeos.class.getResource("css/Style.css").toExternalForm());

        // Textos
        // Bienvenida
        Text txtBienvenida = new Text("Bienvenido a la App Sondeos,\n¿cómo te llamas?");

        // Error
        Text txtErrorCategoriaUsuario = new Text("No se ha introducido una categoría o nombre de usuario.");


        // TextField que registrará el nombre en el csv
        TextField txtNombre = new TextField();


        // ChoiceBox para seleccionar las distintas encuestas
        ChoiceBox seleccionEncuesta = new ChoiceBox();


        // Botón para iniciar una encuesta
        Button iniciaEncuesta = new Button("Iniciar encuesta");


        // Permite especificar a qué sección acceder dependiendo de lo que se elija en el ChoiceBox
        SingleSelectionModel<Tab> selectionModel = seccionesEncuesta.getSelectionModel();


        // Adición de nodos en el panel de la página principal
        principal.getChildren().add(txtBienvenida);
        principal.getChildren().add(txtNombre);
        principal.getChildren().add(seleccionEncuesta);
        principal.getChildren().add(iniciaEncuesta);
        principal.getChildren().add(txtErrorCategoriaUsuario);


        // Adición de las secciones(Tabs) al TabPane
        seccionesEncuesta.getTabs().addAll(animal, comida, deporte, lectura, viaje);



        // Asigna los valores en la ChoiceBox para seleccionar las distintas encuestas
        seleccionEncuesta.setValue("Introduzca una categoría");
        seleccionEncuesta.setItems(encuestas);


        // Eventos
        iniciaEncuesta.setOnAction(e -> {
            txtErrorCategoriaUsuario.setVisible(false);

            String selecEnc = seleccionEncuesta.getValue().toString();
            String nombreUsuario = txtNombre.getText();

            if (!selecEnc.equals("Introduzca una categoría") && !nombreUsuario.isEmpty()) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));

                // Obtiene la ruta del directorio Documents del usuario actual
                String directorioDocumentos = System.getProperty("user.home") + File.separator + "Documents";
                String carpetaEncuestas = directorioDocumentos + File.separator + "Encuestas";

                // Crea la carpeta Encuestas si no existe
                File carpetaEncuestasFile = new File(carpetaEncuestas);
                carpetaEncuestasFile.mkdirs();

                // Construir la ruta completa al archivo
                String rutaCompleta = carpetaEncuestas + File.separator + nombreUsuario + ".csv";

                try {
                    // Crea el csv en la carpeta Encuesta de
                    FileWriter encuestaCsv = new FileWriter(rutaCompleta, true);

                    // Asigna el fichero de la encuesta a las secciones
                    comida.setCsvEncuesta(encuestaCsv);
                    lectura.setCsvEncuesta(encuestaCsv);
                    deporte.setCsvEncuesta(encuestaCsv);
                    viaje.setCsvEncuesta(encuestaCsv);
                    animal.setCsvEncuesta(encuestaCsv);

                    // Se desplaza a una encuesta distinta dependiendo de la sección
                    switch (selecEnc) {

                        case "Animales":
                            root.setCenter(seccionesEncuesta);
                            selectionModel.select(0);
                            break;

                        case "Comida":
                            root.setCenter(seccionesEncuesta);
                            selectionModel.select(1);
                            break;

                        case "Deporte":
                            root.setCenter(seccionesEncuesta);
                            selectionModel.select(2);
                            break;

                        case "Lectura":
                            root.setCenter(seccionesEncuesta);
                            selectionModel.select(3);
                            break;

                        case "Viajes":
                            root.setCenter(seccionesEncuesta);
                            selectionModel.select(4);
                            break;
                    }
                } catch (IOException err) {
                    err.printStackTrace();
                }
            } else {
                txtErrorCategoriaUsuario.setVisible(true);
            }
        });


        // Formato para los TextField
        // txtNombre
        UnaryOperator<TextFormatter.Change> filtroLetra = cambio -> {
            // RegEx para permitir solo letras
            Pattern patron = Pattern.compile("[0-9]");

            // Verificamos si el nuevo texto cumple con la RegEx
            if (!patron.matcher(cambio.getControlNewText()).matches()) {
                return cambio;
            } else {
                // Si no se cumple con los requisitos del patrón, no se devuelve nada
                return null;
            }
        };

        TextFormatter<String> letraSolo = new TextFormatter<>(filtroLetra);
        txtNombre.setTextFormatter(letraSolo);


        // IDs CSS
        principal.setId("main");
        txtErrorCategoriaUsuario.setId("errorCategoria");
        iniciaEncuesta.setId("iniciaEncuesta");
        txtBienvenida.setId("bienvenida");
        seleccionEncuesta.setId("categorias");


        // Propiedades
        // Escena
        scene.getStylesheets().add(AppSondeos.class.getResource("css/Style.css").toExternalForm());

        // Paneles
        principal.setSpacing(10);
        principal.setAlignment(Pos.CENTER);

        root.setCenter(principal);

        // Nodos
        txtNombre.setMaxWidth(400);

        txtErrorCategoriaUsuario.setVisible(false);

        return scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
