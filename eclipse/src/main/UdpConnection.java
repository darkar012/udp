package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;


import model.Delivery;
import model.Generic;
import processing.data.Table;

public class UdpConnection extends Thread {

	private DatagramSocket socket;
	
	private static UdpConnection unicaInsta;
	
	public static UdpConnection getInstance() {
		if(unicaInsta == null) {
			unicaInsta = new UdpConnection();
			unicaInsta.start();
		}
		return unicaInsta;
	} 

	private UdpConnection() {}
	
	private OnMessageListener observer;
	
	public void setObserver (OnMessageListener observer) {this.observer = observer;}
	
	public void run () {

		try {
			socket = new DatagramSocket(5000);

			while (true) {
				byte[] buffer = new byte [100];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("esperando datagrama");
				socket.receive(packet);
				String mensaje = new String(packet.getData()).trim();
				SocketAddress iport= packet.getSocketAddress();
				String i = iport.toString();
				String red = i.replace("/", "");
				String[] portip = red.split(":");
				String ip = portip[0];
				String port = portip[1];
				
				observer.OnMessage(mensaje, ip, port);
			}
		}catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void sendMessage (String mensaje, String ip, String port) {

		new Thread(
				() ->{
					try {

						InetAddress ipd = InetAddress.getByName(ip);
						int portd = Integer.parseInt(port);
						DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ipd, portd);
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