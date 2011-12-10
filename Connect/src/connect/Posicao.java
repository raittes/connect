package connect;

public class Posicao {
	
	private Ficha ficha;
	private boolean bloqueada;
	
	public Posicao(){
		this.bloqueada = false;
		this.ficha = null;
	}
	public Posicao(Ficha ficha, boolean bloqueada){
		this.setBloqueada(bloqueada);
		this.setFicha(ficha);
	}
	
	public Ficha getFicha() {
		return ficha;
	}
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	public boolean isBloqueada() {
		return bloqueada;
	}
	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

}
