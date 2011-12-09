package connect;

import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
import servidor.JogadaConnect;

public class Tabuleiro {
	private static final int PLANO = 0;	
	private static final int ESQUERDA = 1;
	private static final int DIREITA  = 2;
	/*
	 * Serializacao em string:
	 * 
	 * primeiro numero = numero de colunas
	 * faz um loop
	 * segundo numero = numero de linhas da primeira coluna
	 * pega os N numeros de valores seguintes para preencher as linhas daquela coluna e sendo o ultimo a inclinacao do campo
	 * 
	 * exemplo
	 * 
	 * C = Coluna, L = Linha, V = Valor da linha e coluna, I = Indice.
	 * CLVVVVVVVILVVVVVI...LCVVVVI
	 *	
	 *	3211140221030010
	 *	3 = numero de colunas
	 *		2 numero de linhas da coluna 1 
	 *			 11 valores
	 *				1 inclinacao
	 *		4 numero de linhas da coluna 2
	 *			0221 valores
	 *				0 = inclinacao
	 *		3 numero de linhas da coluna 3
	 *			001 valores
	 *				inclinacao
	 *
	 *						0			1			2			3
	 *
	 *Valores			sem peca	jogador 1	jogador 2	casa bloqueada
	 *inclinacao		plano		esquerda	direita 
	 * 
	 */
	Posicao[][] linhaColuna;
	private Jogador jogadorDono;
	private Jogador jogadorConvidado;
	
	public Tabuleiro(String imagemTabuleiro) {
		int nColunas = getFirstAsInt(imagemTabuleiro);
		this.linhaColuna = new Posicao[nColunas][];
		int nLinhas;
		for (Posicao[] coluna : this.linhaColuna) {
			nLinhas = getFirstAsInt(imagemTabuleiro);
			coluna = new Posicao[nLinhas+1];
			for (int j = 0; j<nLinhas;j++) {
				coluna[0] = getFirstAsPosicao(imagemTabuleiro);
			}
			coluna[coluna.length] = this.getFirstAsInclinacao(imagemTabuleiro);			
		}
	}
	private Posicao getFirstAsInclinacao(String imagemTabuleiro) {
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return new PosicaoInclinacao(Integer.parseInt(aux));
	}
	private Posicao getFirstAsPosicao(String imagemTabuleiro) {
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		Posicao retorno = null;
		switch (Integer.parseInt(aux)) {
		case 1:
			new Posicao(new Ficha(this.getJogadorDono()),true);
			break;
		case 2: 
			new Posicao(new Ficha(this.getJogadorConvidado()),true);
			break;
		default:
			new Posicao();
			break;
		}
		return retorno;
	}
	private int getFirstAsInt(String imagemTabuleiro){
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return Integer.parseInt(aux);
	}
	public void executaJogadaAdicionar(JogadaAdicionar jogada) {
		if(this.temPosisaoLivre(jogada.getColuna())){
			this.depositaFicha(new Ficha(jogada.getJogador()));
		}
		
	}
	private void depositaFicha(Ficha ficha) {
		// TODO Deposita ficha na coluna selecionada, caso haja inclinacao, tentar jogar para a coluna ao lado.
		
	}
	private boolean temPosisaoLivre(int coluna) {
		int cont = 0;
		boolean temPosicao = false;
		while(cont == linhaColuna[coluna].length||temPosicao) {
			if(!linhaColuna[coluna][cont].isBloqueada()){
				temPosicao = true;
			}
			cont++;
		}
		return false;
	}
	public void executaJogadaRemover(JogadaRemover jogada) {
		// TODO Criar metodo que remove peca do adversario a partir da jogada adquirida.
		
	}
	public Jogador getJogadorDono() {
		return jogadorDono;
	}
	public void setJogadorDono(Jogador jogadorDono) {
		this.jogadorDono = jogadorDono;
	}
	public Jogador getJogadorConvidado() {
		return jogadorConvidado;
	}
	public void setJogadorConvidado(Jogador jogadorConvidado) {
		this.jogadorConvidado = jogadorConvidado;
	}	
}
