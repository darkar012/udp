package model;

import processing.core.PApplet;
import processing.core.PImage;

public class DeliveryDrawable extends PApplet{
	
    private String pedido, hora, ip, port;
    private int x, y, numeroPedido;
    private PApplet app;
    private PImage danone, sandwich, perrito, juguito;
    

	public DeliveryDrawable(String pedido, String hora, String ip, String port, int x, int y, int numeroPedido, PApplet app) {
		this.pedido = pedido;
		this.hora = hora;
		this.ip = ip;
		this.port = port;
		this.x = x;
		this.y = y;
		this.numeroPedido = numeroPedido;
		this.app = app;
		danone = app.loadImage("../imagenes/danone.jpg");
		juguito = app.loadImage("../imagenes/manguito.jpg");
		perrito = app.loadImage("../imagenes/perrito.jpg");
		sandwich = app.loadImage("../imagenes/sandiwch.jpg");
	}
	
	public void pintar () {
		
		switch (pedido) {
		case "danone":
			resize(danone);
			break;
		case "perrito":
			resize(perrito);
			break;
		case "sandwich":
			resize(sandwich);
			break;
		case "manguito":
			resize(juguito);
			break;
		}
	}
	
	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public void resize (PImage imagen) {
		imagen.resize(60, 60);
		app.image(imagen, x, y-15);
		app.fill(0);
		app.textSize(20);
		app.text("pedido #"+numeroPedido, x+70, y);
		app.textSize(14);
		app.text("hora: "+ hora, x+70, y+30);
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
}
