package com.template.main;

import com.template.controller.MainController;
import com.template.validator.AcademiaValidator;
import com.template.validator.IAcademiaValidator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        IAcademiaValidator validador =
                new AcademiaValidator();

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/com/template/main.fxml")
        );

        loader.setControllerFactory(controllerClass -> {

            if (controllerClass == MainController.class) {
                return new MainController(validador);
            }

            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Sistema de Academias");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}