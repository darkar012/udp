package com.example.clienteudp;

import android.net.IpSecManager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPconnection extends Thread {

    private DatagramSocket socket;

    private static UDPconnection unicainsta;

    public static UDPconnection getInstance() {
        if (unicainsta == null) {
            unicainsta = new UDPconnection();
            unicainsta.start();
        }
        return unicainsta;
    }

    private UDPconnection(){

    }

    private OnMessageListener observer;

    public void setObserver(OnMessageListener observer) {this.observer = observer;}


    public void run(){
        try {
            socket = new DatagramSocket(6000);

            while (true) {
                byte[] buffer = new byte [100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData()).trim();
                observer.OnMessage(mensaje);

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage (String mensaje) {

        new Thread(
                () ->{
                    try {

                        InetAddress ip = InetAddress.getByName("192.168.1.52");
                        DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, 5000);
                        socket.send(packet);

                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        ).start();



    }

}
