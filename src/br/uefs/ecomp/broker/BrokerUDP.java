package br.uefs.ecomp.broker;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BrokerUDP extends Thread {
    private static ArrayList<Topico> topicos;
    private static DatagramSocket server;
    private DatagramPacket datagrama;

    public BrokerUDP(DatagramPacket datagrama) {
        this.datagrama = datagrama;
    }

    public void run() {
        String texto = new String(datagrama.getData()).trim();

        System.out.println("Mensagem recebida: " + texto);

        StringTokenizer st = new StringTokenizer(texto, ":");
        String nomeTopico = st.nextToken();
        String codigo = st.nextToken();

        Topico topico = buscarTopico(nomeTopico);

        if(topico == null) {
            topico = new Topico(nomeTopico);
            topicos.add(topico);
        }

        if(codigo.equals("S")) {
            topico.addSubscriber(datagrama);
            System.out.println("Testando");
        } else if(codigo.equals("P")) {
            topico.setValor(st.nextToken());
            Iterator subscribers = topico.iteratorSubscribers();
            byte[] msg = topico.getValor().getBytes();

            while(subscribers.hasNext()) {
                DatagramPacket subscriber = (DatagramPacket) subscribers.next();

                InetAddress endereco = subscriber.getAddress();
                int porta = subscriber.getPort();

                datagrama = new DatagramPacket(msg, msg.length, endereco, porta);
                try {
                    server.send(datagrama);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Topico buscarTopico(String nomeTopico) {
        Iterator iteratorTopico = topicos.iterator();

        while(iteratorTopico.hasNext()) {
            Topico topico = (Topico) iteratorTopico.next();

            if(topico.getNome().equals(nomeTopico)) {
                return topico;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        topicos = new ArrayList<>();
        int porta = Integer.parseInt(JOptionPane.showInputDialog("Porta do Servidor:"));

        byte[] mensagem = new byte[1024];

        try {
            server = new DatagramSocket(porta);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while(true) {
            DatagramPacket datagrama = new DatagramPacket(mensagem, mensagem.length);
            server.receive(datagrama);

            Thread thread = new BrokerUDP(datagrama);
            thread.start();
        }
    }
}
