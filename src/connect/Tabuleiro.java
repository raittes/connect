package connect;

import servidor.JogadaConnect;

public class Tabuleiro {
	
	Jogador jogadorDono;
	Jogador jogadorConvidado;
	
	public Tabuleiro() {
	}
	
	public void criarParticipante(String nome) {
		if(jogadorDono==null){
			jogadorDono = new Jogador(nome);
		}else{
			jogadorConvidado = new Jogador(nome);
		}
	}

	public void trataJogada(String msg) {
		
	}
	
	
}
