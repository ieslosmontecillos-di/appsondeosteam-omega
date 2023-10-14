/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ieslosmontecillos.appsondeos;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Profesora
 */
public class AppSondeos extends Application {

    @Override
    public void start(Stage stage){
        // Lista que guarda los valores para el ChoiceBox
        ObservableList<String> encuestas = FXCollections.observableArrayList("Animal", "Comida", "Deporte", "Lectura", "Viajes");

        // Panel base
        BorderPane root = new BorderPane();

        // Panel de la página de bienvenida
        VBox principal = new VBox();

        // Panel para las encuestas
        TabPane seccionesEncuesta = new TabPane();

        // Llamada a las distintas secciones(Tabs) de las clases objeto
        Comida comida = new Comida();
        Lectura lectura = new Lectura();

        // Asignación de la escena
        Scene scene = new Scene(root, 800, 900);

        // Asigna el css en la escena
        scene.getStylesheets().add(AppSondeos.class.getResource("css/Style.css").toExternalForm());

        // Texto de bienvenida
        Text txtBienvenida = new Text("Bienvenido a la App Sondeos,\n¿cómo te llamas?");
        Text txtErrorCategoriaUsuario = new Text("No se ha introducido una categoría o nombre de usuario.");

        // TextField que registrará el nombre en el csv
        TextField txtNombre = new TextField();

        // ChoiceBox para seleccionar las distintas encuestas
        ChoiceBox seleccionEncuesta = new ChoiceBox();

        // Botón para iniciar una encuesta
        Button iniciaEncuesta = new Button("Iniciar encuesta");

        // Permite especificar a qué sección acceder dependiendo de lo que se elija en el ChoiceBox
        SingleSelectionModel<Tab> selectionModel = seccionesEncuesta.getSelectionModel();


        // Adición de las secciones(Tabs) al TabPane
        seccionesEncuesta.getTabs().add(lectura);
        seccionesEncuesta.getTabs().add(comida);


        // Asigna los valores en la ChoiceBox para seleccionar las distintas encuestas
        seleccionEncuesta.setValue("Introduzca una categoría");
        seleccionEncuesta.setItems(encuestas);


        // Evento para el botón de iniciar la encuesta
        iniciaEncuesta.setOnAction(e->{

            // Convierte a String el valor seleccionado en el ChoiceBox de encuestas para que funcione el switch
            String selecEnc = seleccionEncuesta.getValue().toString();
            String nombreUsuario = txtNombre.getText();
            
            if(!selecEnc.equals("Introduzca una categoría") || !nombreUsuario.isEmpty()) {

                // Recoge el nombre del usuario para
                nombreUsuario = txtNombre.getText();

                try {
                    FileWriter encuestaCsv = new FileWriter(nombreUsuario + ".csv", true);
                    encuestaCsv.append(selecEnc);
                    comida.setCsvEncuesta(encuestaCsv);
                    lectura.setCsvEncuesta(encuestaCsv);
                    switch (selecEnc) {
                        case "Animal":
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
                }catch (IOException err){
                    err.printStackTrace();
                }
            }else{
                txtErrorCategoriaUsuario.setVisible(true);
            }

        });

        // Muestra el icono de la aplicación
        stage.getIcons().add(new Image(AppSondeos.class.getResourceAsStream("img/icon.png")));

        // Adición de nodos en el panel de la página principal
        principal.getChildren().add(txtBienvenida);
        principal.getChildren().add(txtNombre);
        principal.getChildren().add(seleccionEncuesta);
        principal.getChildren().add(iniciaEncuesta);
        principal.getChildren().add(txtErrorCategoriaUsuario);

        // Propiedades de la página principal
        principal.setSpacing(10);
        principal.setAlignment(Pos.CENTER);

        // Deja el mensaje de error en invisible por defecto
        txtErrorCategoriaUsuario.setVisible(false);

        // Establece IDs para el css
        txtErrorCategoriaUsuario.setId("errorCategoria");
        iniciaEncuesta.setId("iniciaEncuesta");
        txtBienvenida.setId("bienvenida");
        seleccionEncuesta.setId("categorias");

        // Ajusta el tamaño del TextField
        txtNombre.setMaxWidth(400);

        // Asigna el panel de la página principal(VBox)
        root.setCenter(principal);

        // Propiedades del stage
        stage.setResizable(false);
        stage.setTitle("App Sondeos");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    
}
