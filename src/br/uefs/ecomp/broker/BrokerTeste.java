package br.uefs.ecomp.broker;

import testes.chat.Servidor;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrokerTeste {
    private static HashMap<String, List<BufferedWriter>> subscribers;
    private static ArrayList<BufferedWriter> publishers;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    public BrokerTeste(Socket con) {
        this.con = con;
        try {
            in  = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try{

            String msg;
            OutputStream ou =  this.con.getOutputStream();
            Writer ouw = new OutputStreamWriter(ou);
            BufferedWriter bfw = new BufferedWriter(ouw);
            publishers.add(bfw);
            nome = msg = bfr.readLine();

            while(!"Sair".equalsIgnoreCase(msg) && msg != null) {
                msg = bfr.readLine();
                sendToAll(bfw, msg);
                System.out.println(msg);
            }

        }catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendToAll(BufferedWriter bwSaida, String msg) throws  IOException {
        BufferedWriter bwS;

        for(BufferedWriter bw : publishers) {
            bwS = (BufferedWriter)bw;
            if(!(bwSaida == bwS)) {
                bw.write(nome + " -> " + msg+"\r\n");
                bw.flush();
            }
        }
    }

    public void addPublisher() {

    }

    public void addSubscriber() {

    }

    public static void main(String []args) {

        try{
            //Cria os objetos necessário para instânciar o servidor
            JLabel lblMessage = new JLabel("Porta do Servidor:");
            JTextField txtPorta = new JTextField("12345");
            Object[] texts = {lblMessage, txtPorta };
            JOptionPane.showMessageDialog(null, texts);
            server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
            subscribers = new HashMap<>();
            publishers = new ArrayList<>();
            JOptionPane.showMessageDialog(null,"Servidor ativo na porta: "+ txtPorta.getText());

            while(true) {
                System.out.println("Aguardando conexão...");
                Socket con = server.accept();
                System.out.println("Cliente conectado...");
                Thread t = new Servidor(con);
                t.start();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
