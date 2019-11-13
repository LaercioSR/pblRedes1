package br.uefs.ecomp.atuadores.arCondicionado;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaArCondicionadoController implements Initializable {
    @FXML private ImageView imageViewArCondicionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
    }

    public void ligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoDesligado.png"));
    }

    public void desligarArCondicionado() {
        imageViewArCondicionado.setImage(new Image("/br/uefs/ecomp/imagens/arCondicionadoLigado.png"));
    }
}
