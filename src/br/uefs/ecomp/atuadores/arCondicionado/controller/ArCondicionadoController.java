package br.uefs.ecomp.atuadores.arCondicionado.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ArCondicionadoController extends Thread {
    private FXMLTelaArCondicionadoController controllerTela;
    private boolean statusArCondicionado = false;
    private String ip;
    private int porta;
    private String topico;
    private String temperatura = "35";

    public void setConfiguracoes(String ip, int porta, String topico) {
        this.ip = ip;
        this.porta = porta;
        this.topico = topico;
    }

    public void setControllerTela(FXMLTelaArCondicionadoController controllerTela) {
        this.controllerTela = controllerTela;
    }

    public boolean arEstaLigado() {
        return statusArCondicionado;
    }

    public void setStatusArCondicionado(boolean statusArCondicionado) {
        this.statusArCondicionado = statusArCondicionado;
    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            String mensagem = topico+":"+"S";   // Criando mensagem para enviar ao servidor, se inscrevendo num tópico
            InetAddress endereco = InetAddress.getByName(ip);

            DatagramPacket enviarMensagem = new DatagramPacket(mensagem.getBytes(), mensagem.getBytes().length, endereco, porta);
            socket.send(enviarMensagem);    // Envio da mensagem de inscrição

            /* Laço de repetição ficar recebendo mensagens do servidor */
            while(true) {
                byte[] msg = new byte[2];
                DatagramPacket msgRecebida = new DatagramPacket(msg, msg.length);
                socket.receive(msgRecebida);
                temperatura = new String(msgRecebida.getData());

                statusArCondicionado = Integer.parseInt(temperatura) > 25;
                controllerTela.mudarArCondicionado(statusArCondicionado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
