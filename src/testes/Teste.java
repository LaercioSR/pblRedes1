package testes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Teste {
    ServerSocket server = new ServerSocket(12345);
    Socket client = server.accept();

    public Teste() throws IOException {
    }


    public static void main(String[] args) throws UnknownHostException {
        byte[] addr = {127,0,0,1};
        System.out.println(InetAddress.getByAddress(addr).getHostName());
    }
}
