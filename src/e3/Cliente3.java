package e3;

import java.net.*;

public class Cliente3 {

    public static void main(String[] args) throws Exception {
        InetAddress group = InetAddress.getByName("224.0.0.1");
        MulticastSocket socket = new MulticastSocket(1234);
        socket.joinGroup(group);

        byte[] buffer = new byte[1024];

        while(true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String word = new String(packet.getData(), 0, packet.getLength());
            System.out.println(word);
        }
    }
}