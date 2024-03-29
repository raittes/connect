package connect.logica;

import br.ufsc.inf.leobr.cliente.Jogada;
import connect.AtorJogador;
import connect.Jogador;
import connect.Tabuleiro;
import connect.Ranking;
import javax.swing.JOptionPane;

public final class Connect {

        private Tabuleiro tabuleiro;
        private boolean tipoJogadaInsercao;
        private AtorJogador atorJogador;
        private Ranking ranking;        

	public Connect(Tabuleiro tabuleiro, AtorJogador jo) {
		this.setTabuleiro(tabuleiro);
                this.tipoJogadaInsercao = true;
                this.atorJogador = jo;      
                this.ranking = new Ranking();
	}
	public boolean trataJogada(Jogada jogada) {
		if (atorJogador.getAtorRede().isMinhaVez()) {
			if (jogada instanceof JogadaAdicionar) {
                                //tabuleiro.executeAnimacao((JogadaAdicionar)jogada);
				return tabuleiro.executaJogadaAdicionar((JogadaAdicionar) jogada);                                                                    
			} else if(jogada instanceof JogadaRemover) {                            
				return tabuleiro.executaJogadaRemover((JogadaRemover) jogada);                               
			}
                    return true;
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
            Jogador remoto = new Jogador(i, nomeAdversario);
            //        remoto.setIcone(new ImageIcon("src/imagens/jogador2.png").getImage());
            tabuleiro.setJogadorRemoto(remoto);
        }

        public void criaJogadorLocal(int i, String nome) {
            Jogador local = new Jogador(i, nome);
            //       local.setIcone(new ImageIcon("src/imagens/jogador1.png").getImage());
            tabuleiro.setJogadorLocal(local);
        }
        public Jogador temVencedor() {        
            Jogador jogador = null;
            jogador = this.getTabuleiro().temVencedor();    
            this.getTabuleiro().repaint();
            return jogador;
        }
        public Ranking getRanking() {
            return ranking;
        }
        public void setRanking(Ranking ranking) {
            this.ranking = ranking;
        }   
        
                public void verificaVencedor() {
            Jogador vencedor = temVencedor();
            if(vencedor!=null){   
                String menssagem;
                if(this.getTabuleiro().getJogadorLocal().getNome().equals(vencedor.getNome())){
                    this.getTabuleiro().removeMouseListener(atorJogador);                
                    this.getRanking().addVitoria();
                    menssagem = "PARABÉNS "+vencedor.getNome()+", você venceu!!";

                    
                }else{ 
                    this.getRanking().addDerrota() ;
                    menssagem = "QUE PENA, VOCE PERDEU!";
                    this.getTabuleiro().removeMouseListener(atorJogador);
                }
                
                //atorJogador.connectView.getPartidaFinalizar().setEnabled(false);
                //this.connectView.getPartidaIniciar().setEnabled(true);
                //this.connectView.getPartidaReiniciar().setEnabled(false);
                JOptionPane.showMessageDialog(null,menssagem);
                atorJogador.finalizaPartida();//
                //        atorJogador.iniciarJogo();
                  
                  
                  
            }

        }
}
