package Mundo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    private static DatagramSocket socket;

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
                socket = new DatagramSocket();
                
                //Mostrar ponto que o usuário pode digitar
                System.out.println("> ");

                //String do texto que sera enviado
                String envio = sc.nextLine();

                //Criar o buffer de dados que será enviado
                while(!envio.equals("")){
                        //montar o pacote de envio - passando a mensagem, o tamanho da mensagem, o servidor e a porta UDP
                        DatagramPacket msg = new DatagramPacket(
                            envio.getBytes(), 
                            envio.getBytes().length, 
                            InetAddress.getLocalHost(), 
                            4545
                        );

                        //envia o packet através do DatagramSocket
                        socket.send(msg);

                        //packet que recebe a resposta do servidor
                        DatagramPacket resposta = new DatagramPacket(new byte[512], 512);

                        //guarda o packet da resposta no DatagramSocket
                        socket.receive(resposta);

                        //Recebe a mensagem em Bytes de resposta, transforma em String e imprime na tela
                        System.out.println(new String(resposta.getData()));

                        //quebra uma linha e imprime o sinalzinho de digitação novamente e recebe uma nova estring
                        System.out.println("> ");
                        envio = sc.nextLine();
                }
        } catch (SocketException e) {
                System.out.println(e.getMessage());
        } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
        } catch (IOException e) {
                System.out.println(e.getMessage());
        }
        //fecha o scanner
        sc.close();			
    }
}
