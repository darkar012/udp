package model;

public class Delivery {
	
	private String type = "pedido";
    private String pedido, hora;


    public Delivery (String pedido, String hora){
        this.hora = hora;
        this.pedido = pedido;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getType() {
        return type;
    }

    public String getPedido() {
        return pedido;
    }

    public String getHora() {
        return hora;
    }
}


