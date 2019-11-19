package br.uefs.ecomp.atuadores.arCondicionado;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaArCondicionadoController implements Initializable {
    private ArCondicionado application;
    private ArCondicionadoController controller;
    @FXML private AnchorPane anchorPaneTela;
    private AnchorPane arCondicionado;
    private AnchorPane configuracoes;
    FXMLAnchorPaneConfiguracoesArCondicionadoController controllerConfiguracoes;
    FXMLAnchorPaneArCondicionadoController controllerArCondicionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setApplication(ArCondicionado application) {
        this.application = application;
    }

    public void setController(ArCondicionadoController controller) {
        this.controller = controller;
    }

    public void carregarAnchorPaneConfiguracoes() throws IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLAnchorPaneConfiguracoesArCondicionado.fxml"));
        configuracoes = loader.load();
        controllerConfiguracoes = loader.getController();
        controllerConfiguracoes.setController(controller);
        controllerConfiguracoes.setControllerTela(this);
    }

    public void carregarAnchorPaneArCondicionado() throws  IOException {
        AnchorPane a;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLAnchorPaneArCondicionado.fxml"));
        arCondicionado = loader.load();
        controllerArCondicionado = loader.getController();
        controllerArCondicionado.setController(controller);
        controllerArCondicionado.setControllerTela(this);

    }

    public void trocarAnchorPaneArCondicionado() {
        anchorPaneTela.getChildren().setAll(arCondicionado);
    }

    public void trocarAnchorPaneConfiguracoes() {
        anchorPaneTela.getChildren().setAll(configuracoes);
    }

    public void mudarArCondicionado(boolean status) {
        if(status) {
            controllerArCondicionado.ligarArCondicionado();
        } else {
            controllerArCondicionado.desligarArCondicionado();
        }
    }
}
