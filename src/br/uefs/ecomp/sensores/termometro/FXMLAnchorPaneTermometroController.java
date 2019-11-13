package br.uefs.ecomp.sensores.termometro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAnchorPaneTermometroController implements Initializable {
    private FXMLTelaController controllerTela;
    private TermometroController controller;
    private int temperatura = 35;
    @FXML private Text textTemperatura;

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
    void aumentarTemperatura(ActionEvent event) {
        textTemperatura.setText(Integer.toString(++temperatura));
    }

    @FXML
    void diminuirTemperatura(ActionEvent event) {
        textTemperatura.setText(Integer.toString(--temperatura));
    }

    @FXML
    void configurarTermometro(ActionEvent event) throws IOException {
        controllerTela.carregarAnchorPaneConfiguracoes();
    }
}
