package connect.logica;

import connect.Jogador;
import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaRemover implements Jogada{
	
	private Jogador jogador;
	private int coluna;
	private int linha;
	
	public JogadaRemover(){
		
	}
	public JogadaRemover(int coluna, int linha, Jogador jogador){
		this.setColuna(coluna);
		this.setJogador(jogador);
		this.setLinha(linha);
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
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
}
