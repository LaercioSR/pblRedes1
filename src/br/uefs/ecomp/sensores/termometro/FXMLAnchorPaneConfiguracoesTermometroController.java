package br.uefs.ecomp.sensores.termometro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAnchorPaneConfiguracoesTermometroController implements Initializable {
    private FXMLTelaController controllerTela;
    private TermometroController controller;
    @FXML private TextField textFildIP;
    @FXML private TextField textFildPorta;
    @FXML private TextField textFildTopico;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setControllerTela(FXMLTelaController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void setController(TermometroController controller) {
        this.controller = controller;
    }

    @FXML
    void salvarConfiguracoes(ActionEvent event) throws IOException {
        controller.setConfiguracoes(textFildIP.getText(), Integer.parseInt(textFildPorta.getText()), textFildTopico.getText());
        controllerTela.carregarAnchorPaneTermometro();
        controller.start();
    }
}
