package connect;

public class Ficha {
	private Jogador jogador;
	
	public Ficha(){
	}
	public Ficha( Jogador jogador){
		this.setJogador(jogador);
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}
