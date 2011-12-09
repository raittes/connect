package connect.logica;

import connect.Jogador;
import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaAdicionar implements Jogada{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jogador jogador;
	private int coluna;
	public JogadaAdicionar(int coluna, Jogador jogador){
		this.setColuna(coluna);
		this.setJogador(jogador);
	}
	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
}
