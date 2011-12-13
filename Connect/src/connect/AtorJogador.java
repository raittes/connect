package connect;

import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connect.logica.Connect;

import servidor.AtorRede;

import br.ufsc.inf.leobr.cliente.Jogada;
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaFinalizar;
import connect.logica.JogadaReiniciarPartida;
import connect.logica.JogadaRemover;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import javax.swing.JLabel;


public class AtorJogador extends JPanel implements MouseListener{

	private Connect connect;
	private AtorRede atorRede;
	private String nome;
	private String servidor;
	private JPanel mainPanel;
        private guiPlacar placar;
        // barra de status no ConnectView
        private JLabel status;
        private final ConnectView connectView;
        
	public AtorJogador(JPanel mainPanel, JLabel status, ConnectView connectView){
		super();
		this.atorRede = new AtorRede(this);
                this.mainPanel = mainPanel;
                this.status = status;
                this.connectView = connectView;
		this.connect = new Connect(new Tabuleiro(), this);
                this.placar = new guiPlacar(this);
	}
        public String getNome() {
            return nome;
        }        
        public void setNome(String nome) {
            this.nome = nome;
        }
    	public void iniciaPartida(boolean comecoJogando){
                this.getConnect().getTabuleiro().zerar();
		String nomeAdversario = atorRede.obterNomeAdversario();
                
                if(comecoJogando){
                    connect.criaJogadorLocal(1,this.nome);
                    connect.criaJogadorRemoto(2, nomeAdversario);
		}else{
                    connect.criaJogadorRemoto(1, nomeAdversario);	
                    connect.criaJogadorLocal(2, this.nome);
		}                
                connect.getTabuleiro().addMouseListener(this);    
                
                // painel layout
                JPanel layout = new JPanel(new FlowLayout(1));
                       layout.add(connect.getTabuleiro());                
                       layout.add(placar);
                
                this.mainPanel.add(layout,"Tabuleiro");   
                
                ((java.awt.CardLayout)this.mainPanel.getLayout()).show(this.mainPanel,"Tabuleiro");    
                this.mainPanel.setBackground(Color.BLACK);
                this.connectView.getPartidaIniciar().setEnabled(false);
                this.connectView.getPartidaFinalizar().setEnabled(true);
                this.connectView.getPartidaReiniciar().setEnabled(true);
                this.connectView.getMenuJogada().setEnabled(true);
                this.connectView.getMenuVerRaking().setEnabled(true);
                mainPanel.repaint();                
	}	
                
	public void iniciarJogo(){
            atorRede.iniciaPartida();
	}
        public void reiniciaPartida(){
            status.setText("Partida REINICIADA!");
            connect.getTabuleiro().removeMouseListener(this);
            this.connect.getTabuleiro().zerar();
            atorRede.enviaJogada(new JogadaReiniciarPartida());            
            connect.getTabuleiro().addMouseListener(this);    
        }
	public void receberJogada(Jogada jogada) {
           if(connect.trataJogada(jogada)){
                atorRede.enviaJogada(jogada);
           }
            this.verificaVencedor();                 
            placar.atualiza();
	}
	public Connect getConnect() {
		return connect;
	}
	public void setConnect(Connect connect) {
		this.connect = connect;
	}
	public void exibeMensagem(String msg) {
		status.setText(msg);
	}       
        public boolean estabelecerConexao(){
                 atorRede.conectar(servidor, nome);            
            return true;
        }    
        public String getServidor() {
            return servidor;
        }
        public void setServidor(String servidor) {
            this.servidor = servidor;
        }
        public void mouseClicked(MouseEvent me) {
            int coluna, linha;                   
                if(this.getAtorRede().isMinhaVez()){
                    coluna = me.getX()/40;
                    linha = me.getY()/40;                        
                    if(this.getConnect().isTipoJogadaInsercao()){
                        // INSERIR Ficha                        
                        this.receberJogada(new JogadaAdicionar(coluna, this.getConnect().getTabuleiro().getJogadorLocal().getId()));                            
                    }else if(!this.getConnect().isTipoJogadaInsercao()){
                        // REMOVER ficha do adversario
                        if(this.getConnect().getTabuleiro().getJogadorLocal().hasDels()){
                            this.getConnect().getTabuleiro().getJogadorLocal().decrementarDels();
                            this.receberJogada(new JogadaRemover(coluna, linha, this.getConnect().getTabuleiro().getJogadorLocal().getId()));
                        }else{
                            JOptionPane.showMessageDialog(null,"Você não possui mais chances de Deletar!");
                        }                            
                    }			
                }
                connect.getTabuleiro().repaint();
        }
        public void mousePressed(MouseEvent me) {    }
        public void mouseReleased(MouseEvent me) {   }
        public void mouseEntered(MouseEvent me) {    }
        public void mouseExited(MouseEvent me) {     }
        public AtorRede getAtorRede() {
            return atorRede;
        }
        public void setAtorRede(AtorRede atorRede) {
            this.atorRede = atorRede;
        }
        public void verificaVencedor() {
            Jogador vencedor = this.getConnect().temVencedor();
            if(vencedor!=null){   
                if(connect.getTabuleiro().getJogadorLocal().getNome().equals(vencedor.getNome())){
                    connect.getTabuleiro().getJogadorLocal().newVitoria();
                    connect.getTabuleiro().getJogadorRemoto().newDerrota();
                    connect.getTabuleiro().removeMouseListener(this);                
                    JOptionPane.showMessageDialog(null, "PARABÉNS "+vencedor.getNome()+",\n você venceu!!");                    
                }else{                
                    connect.getTabuleiro().getJogadorLocal().newDerrota();
                    connect.getTabuleiro().getJogadorRemoto().newVitoria();
                    connect.getTabuleiro().removeMouseListener(this);
                    JOptionPane.showMessageDialog(null, "QUE PENA, VOCE PERDEU!");
                }
                this.connectView.getPartidaFinalizar().setEnabled(false);
                this.connectView.getPartidaIniciar().setEnabled(true);
                this.connectView.getPartidaReiniciar().setEnabled(false);
            }
        }
        public guiPlacar getPlacar() {
            return placar;
        }
        public void setPlacar(guiPlacar placar) {
            this.placar = placar;
        }

    void finalizaPartida() {
        connect.getTabuleiro().removeMouseListener(this);
        this.connect.getTabuleiro().zerar();
        atorRede.enviaJogada(new JogadaFinalizar());
    }

    public boolean pergunte(String string) {
        return JOptionPane.showConfirmDialog(this.getConnect().getTabuleiro(), string)==1;
    }
}

