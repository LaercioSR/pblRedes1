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

/*
    Servidor Broker UPD
    * Recebe mensagens como array de bytes, seguindo o seguinte protocolo:
        "nomeDoTopico:X:mensagem"
    * Sendo:
        - nomeDoTopico: o nome do topico que a mensagem se refere;
        - X: um código, podendo ser S (Subscriber) ou P (Publisher):
            S: para se increver num tópico
            P: para enviar mensagem num tópico
        - mensagem: mensagem necessária apenas com o código P, mensagem esta que será publicada no tópico
*/

public class BrokerUDP extends Thread {
    private static ArrayList<Topico> topicos;
    private static DatagramSocket server;
    private DatagramPacket datagrama;

    public BrokerUDP(DatagramPacket datagrama) {
        this.datagrama = datagrama;
    }

    public void run() {
        /* Transforma o array de bytes numa string removendo os espaços vazios da mensagem */
        String texto = new String(datagrama.getData()).trim();

        System.out.println("Mensagem recebida: " + texto);

        /* A mensagem é separada a partir dos ":", pegando o nome do tópico e o código da mensagem */
        StringTokenizer st = new StringTokenizer(texto, ":");
        String nomeTopico = st.nextToken();
        String codigo = st.nextToken();

        /* O tópico é buscado pelo nome recebido na mensagem, caso receba um null é porque não exite tópico com esse nome
           e um novo tópico é criado */
        Topico topico = buscarTopico(nomeTopico);

        if(topico == null) {
            topico = new Topico(nomeTopico);
            topicos.add(topico);
        }

        /* Verificação do código da mensagem, para inscrever cliente num tópico ou enviar uma mensagem a todos os clientes
           inscritos no tópico que recebeu a mensagem */
        if(codigo.equals("S")) {
            topico.addSubscriber(datagrama);
        } else if(codigo.equals("P")) {
            topico.setValor(st.nextToken());    // O tópico salva a mensagem recebida
            Iterator subscribers = topico.iteratorSubscribers();
            byte[] msg = topico.getValor().getBytes();  // A mensagem é transformada em array de bytes para o envio

            while(subscribers.hasNext()) {
                DatagramPacket subscriber = (DatagramPacket) subscribers.next();

                InetAddress endereco = subscriber.getAddress();
                int porta = subscriber.getPort();

                datagrama = new DatagramPacket(msg, msg.length, endereco, porta);   // Mensagem é enviada para o cliente
                try {
                    server.send(datagrama);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* Função para buscar tópico nas listas de tópicos */
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
        int porta = Integer.parseInt(JOptionPane.showInputDialog("Porta do Servidor:"));    // Diálogo para o usuário informar a porta do servidor

        byte[] mensagem = new byte[1024];   // Array de bytes para receber mensagens

        try {
            server = new DatagramSocket(porta); // Abrindo servidor
        } catch (SocketException e) {
            e.printStackTrace();
        }

        /* Laço de repetição para o servidor receber mensagens */
        while(true) {
            DatagramPacket datagrama = new DatagramPacket(mensagem, mensagem.length);
            server.receive(datagrama);  // Servidor ficará esperando uma mensagem

            Thread thread = new BrokerUDP(datagrama);   // Thread criada para processar a mensagem recebida
            thread.start();
        }
    }
}
