package connect.logica;

import br.ufsc.inf.leobr.cliente.Jogada;
import connect.Jogador;
import connect.Tabuleiro;

public class Connect {
	private boolean vez;
	private Tabuleiro tabuleiro;

	public Connect(Tabuleiro tabuleiro) {
		this.setTabuleiro(tabuleiro);
	}

	public void criarParticipante(String nome) {
		if (tabuleiro.getJogadorDono() == null) {
			tabuleiro.setJogadorDono(new Jogador(nome));
		} else {
			tabuleiro.setJogadorConvidado(new Jogador(nome));
		}
	}

	public void trataJogada(Jogada jogada) {
		if (vez) {
			if (jogada instanceof JogadaAdicionar) {
				tabuleiro.executaJogadaAdicionar((JogadaAdicionar) jogada);
			} else if (jogada instanceof JogadaRemover) {
				tabuleiro.executaJogadaRemover((JogadaRemover) jogada);
			}
		}
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
}
