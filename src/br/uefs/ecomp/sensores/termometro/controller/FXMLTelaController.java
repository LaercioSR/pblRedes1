package br.uefs.ecomp.sensores.termometro.controller;

import br.uefs.ecomp.sensores.termometro.view.Termometro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaController implements Initializable {
    private Termometro application;
    private TermometroController controller;
    @FXML private AnchorPane archorPaneTela;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setApplication(Termometro application) {
        this.application = application;
    }

    public void setController(TermometroController controller) {
        this.controller = controller;
    }

    public void carregarAnchorPaneConfiguracoes() throws  IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/sensores/termometro/view/FXMLAnchorPaneConfiguracoesTermometro.fxml"));
        a = loader.load();
        FXMLAnchorPaneConfiguracoesTermometroController controllerTermometro = loader.getController();
        controllerTermometro.setController(controller);
        controllerTermometro.setControllerTela(this);
        archorPaneTela.getChildren().setAll(a);
    }

    public void carregarAnchorPaneTermometro() throws  IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/br/uefs/ecomp/sensores/termometro/view/FXMLAnchorPaneTermometro.fxml"));
        a = loader.load();
        FXMLAnchorPaneTermometroController controllerTermometro = loader.getController();
        controllerTermometro.setController(controller);
        controllerTermometro.setControllerTela(this);
        archorPaneTela.getChildren().setAll(a);
    }
}
