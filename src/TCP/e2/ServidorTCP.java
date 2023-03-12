package TCP.e2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        int port = 5000;
        SecretNum secretNum = new SecretNum(100);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado. Esperando conexiones en el puerto " + port + "...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde la dirección " + clientSocket.getInetAddress());

                Thread thread = new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                        out.writeBytes("Bienvenido al juego de adivinanza. Introduce un número: ");
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            int guess = Integer.parseInt(inputLine);
                            int result = secretNum.comprova(guess);
                            if (result == 0) {
                                out.writeBytes("Correcto!\n");
                                break;
                            } else if (result == 1) {
                                out.writeBytes("Más pequeño\n");
                            } else {
                                out.writeBytes("Más grande\n");
                            }
                            out.writeBytes("Inténtalo de nuevo: ");
                        }

                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}