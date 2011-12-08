package servidor;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaConnect implements Jogada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String jogada;
	
	public JogadaConnect(String jogada){
		this.jogada = jogada;
	}

	public String getJogada() {
		return jogada;
	}
	
}
