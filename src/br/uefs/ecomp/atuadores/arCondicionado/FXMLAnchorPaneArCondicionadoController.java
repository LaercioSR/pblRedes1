package br.uefs.ecomp.atuadores.arCondicionado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAnchorPaneArCondicionadoController implements Initializable {
    private FXMLTelaArCondicionadoController controllerTela;
    private ArCondicionadoController controller;
    @FXML private ImageView imageViewArCondicionado;

    public void setControllerTela(FXMLTelaArCondicionadoController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public void setController(ArCondicionadoController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
    }

    @FXML
    void abrirConfiguracoes(ActionEvent event) {
        controllerTela.trocarAnchorPaneConfiguracoes();
    }

    @FXML
    void abrirInscricaoTopico(ActionEvent event) {

    }

    public void ligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoLigado.png"));
    }

    public void desligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
    }
}
