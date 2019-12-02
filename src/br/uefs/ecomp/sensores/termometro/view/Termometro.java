package br.uefs.ecomp.sensores.termometro.view;

import br.uefs.ecomp.sensores.termometro.controller.FXMLTelaController;
import br.uefs.ecomp.sensores.termometro.controller.TermometroController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Termometro extends Application {
    static private Stage stage;
    private TermometroController controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        controller = new TermometroController();

        stage = primaryStage;

        mostrarTela();
        stage.setResizable(false);
        stage.show();

        Platform.setImplicitExit(true);
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void mostrarTela() throws IOException {
        FXMLTelaController controllerTela = (FXMLTelaController) trocarScene("FXMLTela.fxml");
        controllerTela.setApplication(this);
        controllerTela.setController(controller);
        controllerTela.carregarAnchorPaneConfiguracoes();

        stage.setTitle("Termômetro");
    }

    private Initializable trocarScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Termometro.class.getResource(fxml));

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
