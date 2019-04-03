package Mundo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Servidor {
    private static DatagramSocket socket;

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
                //Cria um Socket na porta 4545
                socket = new DatagramSocket(4545);
                System.out.println("Servidor aguardando conexão................");

                //Monta o paconte pacote para receber as mensagens dos clientes
                DatagramPacket recebe = new DatagramPacket(new byte[512], 512);

                //Servidor entra em loop infinito recebendo dados dos clientes
                while(true) {
                        //Socket recebe o pacote do servidor
                        socket.receive(recebe);
                        socket.setBroadcast(true);
                        DatagramPacket resposta = new DatagramPacket(
                                        recebe.getData(),
                                        recebe.getLength(),
                                        //recebe.getAddress(),
                                        InetAddress.getByName("255.255.255.255"),
                                        recebe.getPort()
                                );
                        System.out.println(recebe.getAddress().getClass());
                        socket.send(resposta);
                }
        } catch (SocketException e) {
                System.out.println(e.getMessage());
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        //Fechando o Scanner
        sc.close();
    }
}
