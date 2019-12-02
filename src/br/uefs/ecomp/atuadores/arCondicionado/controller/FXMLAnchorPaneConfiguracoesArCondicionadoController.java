package br.uefs.ecomp.atuadores.arCondicionado.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAnchorPaneConfiguracoesArCondicionadoController implements Initializable {
    private FXMLTelaArCondicionadoController controllerTela;
    private ArCondicionadoController controller;
    @FXML private TextField textFildTopico;
    @FXML private TextField textFildPorta;
    @FXML private TextField textFildIP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setControllerTela(FXMLTelaArCondicionadoController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void setController(ArCondicionadoController controller) {
        this.controller = controller;
    }

    @FXML
    void salvarConfiguracoes(ActionEvent event) throws IOException {
        controller.setConfiguracoes(textFildIP.getText(), Integer.parseInt(textFildPorta.getText()), textFildTopico.getText());
        controllerTela.trocarAnchorPaneArCondicionado();
        if(!controller.isAlive()) {
            controller.start();
        }
    }
}
