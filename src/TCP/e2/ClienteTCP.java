package TCP.e2;


import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 5000;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Se crea el socket para conectarse al servidor
            socket = new Socket(hostName, portNumber);
            // Se obtienen los streams de entrada y salida del socket
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo conectar con el servidor: " + hostName);
            System.exit(1);
        }

        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        String serverResponse;

        serverResponse = in.readLine();
        System.out.println(serverResponse);

        while (true) {
            userInput = leer.readLine();
            if (userInput != null) {
                out.println(userInput);
            }
            serverResponse = in.readLine();
            System.out.println(serverResponse);
            if (serverResponse.equals("Correcto")) {
                break;
            }
        }
        out.close();
        in.close();
        leer.close();
        socket.close();
    }
}