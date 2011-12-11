package connect;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connect.logica.Connect;

import servidor.AtorRede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jdesktop.application.ResourceMap;


public class AtorJogador  extends JPanel implements MouseListener{

	private Connect connect;
	private AtorRede atorRede;
	private String nome;
	private String servidor;
	private JPanel mainPanel;
        
	public AtorJogador(JPanel mainPanel){
		super();
		this.atorRede = new AtorRede(this);
                this.mainPanel = mainPanel;                
	}
        public String getNome() {
            return nome;
        }        
        public void setNome(String nome) {
            this.nome = nome;
        }
    	public void iniciaPartida(boolean comecoJogando){                
		String nomeAdversario = atorRede.obterNomeAdversario();
                                        
		connect = new Connect(new Tabuleiro(), this);
                if(comecoJogando){
			connect.criaJogadorLocal(1,this.nome);
			connect.criaJogadorRemoto(2, nomeAdversario);
		}else{
                    connect.criaJogadorRemoto(1, nomeAdversario);	
                    connect.criaJogadorLocal(2, this.nome);
		}
                
                connect.getTabuleiro().setBackground(new Color(192, 191, 191));
                connect.getTabuleiro().setLayout(new java.awt.BorderLayout());
                //
                connect.getTabuleiro().setPreferredSize(new java.awt.Dimension(640,400));
                connect.getTabuleiro().setName("Tabuleiro"); 
                this.mainPanel.add(connect.getTabuleiro(),"Tabuleiro");                                
                connect.getTabuleiro().addMouseListener(this);    
                
                ((java.awt.CardLayout)this.mainPanel.getLayout()).show(this.mainPanel,"Tabuleiro");             
                mainPanel.repaint();                
	}	
                
	public void iniciarJogo(){
		atorRede.iniciaPartida();
	}
	public void receberJogada(Jogada jogada) {
            if(atorRede.isMinhaVez()&& jogada instanceof JogadaAdicionar){
                this.connect.getTabuleiro().executeAnimacao((JogadaAdicionar)jogada);
            }
		if(connect.trataJogada(jogada)){
                    atorRede.enviaJogada(jogada);
                }                
                this.verificaVencedor();                 
	}
	public Connect getConnect() {
		return connect;
	}
	public void setConnect(Connect connect) {
		this.connect = connect;
	}
	public void exibeMensagem(String msg) {
		// TODO Auto-generated method stub
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
        }
    }
}

