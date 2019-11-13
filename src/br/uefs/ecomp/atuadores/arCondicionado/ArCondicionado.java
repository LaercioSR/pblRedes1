package br.uefs.ecomp.atuadores.arCondicionado;

import br.uefs.ecomp.sensores.termometro.Termometro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ArCondicionado extends Application {
    static private Stage stage;
    private ArCondicionadoController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        controller = new ArCondicionadoController();

        stage = primaryStage;

        FXMLTelaArCondicionadoController controllerTela = (FXMLTelaArCondicionadoController) trocarScene("FXMLTelaArCondicionado.fxml");
        controller.setControllerTela(controllerTela);

        stage.setTitle("Ar Condicionado");
        stage.setResizable(false);
        stage.show();
    }

    private Initializable trocarScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ArCondicionado.class.getResource(fxml));

        Parent page = loader.load();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.centerOnScreen();

        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
