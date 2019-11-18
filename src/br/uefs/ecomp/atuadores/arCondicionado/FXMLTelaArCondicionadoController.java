package br.uefs.ecomp.atuadores.arCondicionado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaArCondicionadoController implements Initializable {
    private boolean statusAr;
    @FXML private ImageView imageViewArCondicionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
        statusAr = false;
    }

    @FXML
    void testarAr(ActionEvent event) {
        if(statusAr) {
            desligarArCondicionado();
            statusAr = false;
        }
        else {
            ligarArCondicionado();
            statusAr = true;
        }

    }

    public void ligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoLigado.png"));
    }

    public void desligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
    }
}
