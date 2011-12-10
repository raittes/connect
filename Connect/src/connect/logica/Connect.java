package connect.logica;

import br.ufsc.inf.leobr.cliente.Jogada;
import connect.AtorJogador;
import connect.Jogador;
import connect.Tabuleiro;

public class Connect {
	private Tabuleiro tabuleiro;
        private boolean tipoJogadaInsercao;
        private AtorJogador atorJogador;

	public Connect(Tabuleiro tabuleiro, AtorJogador jo) {
		this.setTabuleiro(tabuleiro);
                this.tipoJogadaInsercao = true;
                this.atorJogador = jo;                
	}

	public boolean trataJogada(Jogada jogada) {
		if (atorJogador.getAtorRede().isMinhaVez()) {
			if (jogada instanceof JogadaAdicionar) {
				return tabuleiro.executaJogadaAdicionar((JogadaAdicionar) jogada);                                                                    
			} else if(jogada instanceof JogadaRemover) {
				return tabuleiro.executaJogadaRemover((JogadaRemover) jogada);                               
			}
		}
                return false;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
        public boolean isTipoJogadaInsercao() {
            return tipoJogadaInsercao;
        }
        public void setTipoJogadaInsercao(boolean tipoJogadaInsercao) {
            this.tipoJogadaInsercao = tipoJogadaInsercao;
        }

        public void criaJogadorRemoto(int i, String nomeAdversario) {
            tabuleiro.setJogadorRemoto(new Jogador(i, nomeAdversario));
        }

        public void criaJogadorLocal(int i, String nome) {
            tabuleiro.setJogadorLocal(new Jogador(i, nome));
        }    
}
