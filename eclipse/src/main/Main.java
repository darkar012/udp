package main;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Delivery;
import model.DeliveryDrawable;
import model.Generic;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener{

	private UdpConnection udp;
	//private DeliveryDrawable delD;
	private int y = 20;
	private int numPed = 0;
	private ArrayList<DeliveryDrawable> pedidos;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
	}

	public void settings () {
		size(400, 700);
	}
	public void setup () {
		udp = UdpConnection.getInstance();
		udp.setObserver(this);
		pedidos = new ArrayList<DeliveryDrawable>(10);
	}

	public void draw () {
		background(255);
		for (int i = 0; i < pedidos.size(); i++) {
			pedidos.get(i).pintar();
			pedidos.size();
	 }
	}

	public void mousePressed () {
		for (int i = 0; i < pedidos.size(); i++) {
			if (mouseX > 10 && mouseX < 10 + pedidos.get(i).getX()+60
					&& mouseY > pedidos.get(i).getY() && mouseY < pedidos.get(i).getY() + 60) {
				udp.sendMessage("Su "+ pedidos.get(i).getPedido()+" ya esta listo", pedidos.get(i).getIp(), pedidos.get(i).getPort());
				for (int j = i; j < pedidos.size(); j++) {
					pedidos.get(j).setY(pedidos.get(j).getY()-70);
					y=pedidos.get(j).getY()+70;
					numPed = pedidos.get(pedidos.size()-1).getNumeroPedido();
				}
				pedidos.remove(i);
				
			}
				
		}
	 }
		
	

	@Override
	public void OnMessage(String msg, String ip, String port) {

		Gson gson = new Gson();
		Generic generic = gson.fromJson(msg, Generic.class);

		System.out.println("Tipo recibido: "+ generic.type);

		switch (generic.type) {

		case "pedido": {
			Delivery del = gson.fromJson(msg, Delivery.class);
			numPed = numPed+1;
			System.out.println(pedidos.size());
			DeliveryDrawable delD = new DeliveryDrawable(del.getPedido(), del.getHora(), ip, port, 10, y, numPed, this);
			
			pedidos.add(delD);
			y += 70;
			break;	
		}

		} 
	}

}
