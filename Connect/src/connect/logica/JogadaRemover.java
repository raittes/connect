package connect.logica;

import connect.Jogador;
import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaRemover implements Jogada{
	
	private int idJogador;
	private int coluna;
	private int linha;
	
	public JogadaRemover(){
		
	}
	public JogadaRemover(int coluna, int linha, int idJogador){
		this.setColuna(coluna);
		this.setIdJogador(idJogador);
		this.setLinha(linha);
	}

        public int getIdJogador() {
            return idJogador;
        }

        public void setIdJogador(int idJogador) {
            this.idJogador = idJogador;
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