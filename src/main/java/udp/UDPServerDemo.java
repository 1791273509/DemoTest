package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @Author wenbaoxie
 * @Date 2020/10/21
 */
public class UDPServerDemo {

    public static void main(String[] args) {

        MyUdpServer server = new MyUdpServer(8080);
        Thread t = new Thread(() -> {

            server.receiveData();
        });

        t.start();
    }

}

class MyUdpServer {

    int port = 0;
    DatagramSocket server = null;
    DatagramPacket packet = null;
    byte[] b = null;


    public MyUdpServer(int port) {
        super();
        this.port = port;
        try {
            server = new DatagramSocket(port);
            System.out.println("UDP服务端已启动，正在监听端口" + port);
        } catch (SocketException e) {

            e.printStackTrace();
        }
    }

    public void receiveData() {
        b = new byte[1024];
        int len = 0;
        packet = new DatagramPacket(b, b.length);
        try {
            while (true) {
                server.receive(packet);
                if ((len = packet.getLength()) > 0) {
                    String msg = new String(packet.getData(), 0, len);
                    InetAddress ip = packet.getAddress();
                    System.out.println("来自主机" + ip + "的消息:" + msg);
                    String s = "hello client";
                    InetAddress destination = packet.getAddress();
                    String data = "hello Client";
                    DatagramPacket datagramPacket =
                            new DatagramPacket(data.getBytes(), data.getBytes().length, destination, packet.getPort());
                    //发送数据
                    server.send(datagramPacket);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}