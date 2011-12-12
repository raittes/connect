package connect.logica;

import connect.Jogador;
import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaAdicionar implements Jogada{

	private static final long serialVersionUID = 1L;
	
	private int idJogador;
	private int coluna;
	public JogadaAdicionar(int coluna, int idJogador){
		this.setColuna(coluna);
		this.setIdJogador(idJogador);
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
}
