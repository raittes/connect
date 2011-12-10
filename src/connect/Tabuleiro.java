package connect;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
import servidor.JogadaConnect;

public class Tabuleiro {
	private static final int PLANO = 0;
	private static final int ESQUERDA = 1;
	private static final int DIREITA = 2;
	/*
	 * Serializacao em string:
	 * 
	 * primeiro numero = numero de colunas faz um loop segundo numero = numero
	 * de linhas da primeira coluna pega os N numeros de valores seguintes para
	 * preencher as linhas daquela coluna e sendo o ultimo a inclinacao do campo
	 * 
	 * exemplo
	 * 
	 * C = Coluna, L = Linha, V = Valor da linha e coluna, I = Indice.
	 * CLVVVVVVVILVVVVVI...LCVVVVI
	 * 
	 * 3211140221030010 3 = numero de colunas 2 numero de linhas da coluna 1 11
	 * valores 1 inclinacao 4 numero de linhas da coluna 2 0221 valores 0 =
	 * inclinacao 3 numero de linhas da coluna 3 001 valores inclinacao
	 * 
	 * 0 1 2 3
	 * 
	 * Valores sem peca jogador 1 jogador 2 casa bloqueadainclinacao plano
	 * esquerda direita
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
			coluna = new Posicao[nLinhas + 1];
			for (int j = 0; j < nLinhas; j++) {
				coluna[0] = getFirstAsPosicao(imagemTabuleiro);
			}
			coluna[coluna.length] = this.getFirstAsInclinacao(imagemTabuleiro);
		}
	}

	private Posicao getFirstAsInclinacao(String imagemTabuleiro) {
		String aux = "" + imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return new PosicaoInclinacao(Integer.parseInt(aux));
	}

	private Posicao getFirstAsPosicao(String imagemTabuleiro) {
		String aux = "" + imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		Posicao retorno = null;
		switch (Integer.parseInt(aux)) {
		case 1:
			new Posicao(new Ficha(this.getJogadorDono()), true);
			break;
		case 2:
			new Posicao(new Ficha(this.getJogadorConvidado()), true);
			break;
		default:
			new Posicao();
			break;
		}
		return retorno;
	}

	private int getFirstAsInt(String imagemTabuleiro) {
		String aux = "" + imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return Integer.parseInt(aux);
	}

	public void executaJogadaAdicionar(JogadaAdicionar jogada) {
		if (this.temPosisaoLivre(jogada.getColuna())) {
			this.depositaFicha(new Ficha(jogada.getJogador()),
					jogada.getColuna());
		}

	}

	private void depositaFicha(Ficha ficha, int coluna) {
		Posicao ultima = getUltimaPosicaoLivre(coluna);
		if (ultima != null) {
			ultima.setFicha(ficha);
			ultima.setBloqueada(true);
		}
	}

	private Posicao getUltimaPosicaoLivre(int coluna) {
		Posicao[] linhas;
		int tamanho;
		Posicao retorno;
		boolean posicaoAchada = false, bloqueada = false;
		do {
			linhas = linhaColuna[coluna];
			tamanho = linhas.length;
			int cont = tamanho - 2;
			PosicaoInclinacao posIncli = (PosicaoInclinacao) linhas[tamanho - 1];
			if (!linhas[cont].isBloqueada()) {
				switch (posIncli.getInclinacao()) {
				case ESQUERDA:
					if (coluna > 0) {
						if (linhaColuna[coluna].length < linhaColuna[coluna - 1].length) {
							if (!linhaColuna[coluna - 1][linhaColuna[coluna].length]
									.isBloqueada()) {
								coluna--;
							}
						} else {
							posicaoAchada = true;
						}
					} else {
						posicaoAchada = true;
					}
					break;
				case DIREITA:
					if (coluna > linhaColuna.length - 1) {
						if (linhaColuna[coluna].length < linhaColuna[coluna + 1].length) {
							if (!linhaColuna[coluna + 1][linhaColuna[coluna].length]
									.isBloqueada()) {
								coluna++;
							} else {
								posicaoAchada = true;
							}
						} else {
							posicaoAchada = true;
						}
					}
					break;
				default:
					posicaoAchada = true;
					break;
				}
			} else {
				posicaoAchada = true;
			}
		} while (!posicaoAchada);
		tamanho = linhas.length - 1;
		do {
			bloqueada = linhas[tamanho].isBloqueada();
			tamanho--;
		} while (bloqueada || tamanho != -1);
		retorno = linhas[tamanho + 1];
		return retorno;
	}

	private boolean temPosisaoLivre(int coluna) {
		int cont = 0;
		boolean temPosicao = false;
		while (cont == linhaColuna[coluna].length || temPosicao) {
			if (!linhaColuna[coluna][cont].isBloqueada()) {
				temPosicao = true;
			}
			cont++;
		}
		return false;
	}

	public void executaJogadaRemover(JogadaRemover jogada) {
		Posicao posicao = this.getPosicao(jogada.getColuna(),jogada.getLinha());
		if(posicao.getFicha().getJogador() == jogadorConvidado && jogadorDono.podeDeletarPecaDoAdversario() &&(temPecaAcimaOuAbaixo(jogada.getColuna(),jogada.getLinha()))){
			posicao.getFicha().getJogador().deletouPecaDoAdversario();
			this.deletarPecaAdversario(posicao);			
		}
	}

	private boolean temPecaAcimaOuAbaixo(int coluna, int linha) {
		if(linhaColuna[coluna].length-1==linha){
			if(linhaColuna[coluna][linha-1].isBloqueada())
				return true;
		}
		return false;
	}

	private void deletarPecaAdversario(Posicao posicao) {
		posicao.setFicha(null);
		posicao.setBloqueada(false);	
	}

	private Posicao getPosicao(int coluna, int linha) {
		return linhaColuna[coluna][linha];
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
