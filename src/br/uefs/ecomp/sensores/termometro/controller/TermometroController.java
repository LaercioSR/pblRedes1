package br.uefs.ecomp.sensores.termometro.controller;

import java.io.IOException;
import java.net.*;

public class TermometroController extends Thread {
    private String ip;
    private int porta;
    private String topico;
    private String temperatura = "35";

    public void setConfiguracoes(String ip, int porta, String topico) {
        this.ip = ip;
        this.porta = porta;
        this.topico = topico;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void run() {
        /* Laço de repetição ficar enviando mensagens ao servidor */
        while(true) {
            try {
                DatagramSocket socket = new DatagramSocket();

                InetAddress endereco = InetAddress.getByName(ip);
                String mensagem = topico+":P:"+temperatura;
                byte[] msg = mensagem.getBytes();

                DatagramPacket datagrama = new DatagramPacket(msg, msg.length, endereco, porta);
                socket.send(datagrama);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sleep(5000);    // Tempo de 5s para enviar uma nova mensagem ao servidor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
