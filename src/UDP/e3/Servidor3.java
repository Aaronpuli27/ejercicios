package UDP.e3;

import java.net.*;
import java.util.*;

public class Servidor3 {

    public static void main(String[] args) throws Exception {
        String[] words = {"una", "de", "cafe", "tomar", "taza", "lentejas", "plato", "con"};
        Random rand = new Random();

        InetAddress group = InetAddress.getByName("224.0.0.1");
        MulticastSocket socket = new MulticastSocket();

        while(true) {
            String word = words[rand.nextInt(words.length)];
            byte[] data = word.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, group, 1234);
            socket.send(packet);
            Thread.sleep(1000);
        }
    }
}
