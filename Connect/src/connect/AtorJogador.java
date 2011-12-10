package connect;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connect.logica.Connect;

import servidor.AtorRede;
import servidor.JogadaConnect;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
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
		atorRede = new AtorRede(this);
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
                                        //new Tabuleiro("220002000")
		connect = new Connect(new Tabuleiro(), this);
                if(comecoJogando){
			connect.criaJogadorLocal(1,this.nome);
			connect.criaJogadorRemoto(2, nomeAdversario);
		}else{
                    connect.criaJogadorRemoto(1, nomeAdversario);	
                    connect.criaJogadorLocal(2, this.nome);
		}
                //JOptionPane.showMessageDialog(null,"ok");
                
                //
                
                
                connect.getTabuleiro().setBackground(Color.BLACK);
                connect.getTabuleiro().setPreferredSize(new java.awt.Dimension(640,400));
                connect.getTabuleiro().setName("Tabuleiro"); 
                this.mainPanel.add(connect.getTabuleiro(),"Tabuleiro");                
                mainPanel.addMouseListener(this);
                
                connect.getTabuleiro().addMouseListener(this);
                
                ((java.awt.CardLayout)this.mainPanel.getLayout()).show(this.mainPanel,"Tabuleiro");                 
                mainPanel.repaint();
                
	}	
                
	public void iniciarJogo(){
		atorRede.iniciaPartida();
	}
	public void receberJogada(Jogada jogada) {
		if(connect.trataJogada(jogada))
                    atorRede.enviaJogada(jogada);
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
                            this.receberJogada(new JogadaAdicionar(coluna, this.getConnect().getTabuleiro().getJogadorLocal().getId()));
                            //atorRede.enviaJogada(new JogadaAdicionar(coluna, this.getConnect().getTabuleiro().getJogadorLocal().getId()));
                        }else if(!this.getConnect().isTipoJogadaInsercao()){
                            if(this.getConnect().getTabuleiro().getJogadorLocal().hasDels()){
                                this.receberJogada(new JogadaRemover(coluna, linha, this.getConnect().getTabuleiro().getJogadorLocal().getId()));
                            }else{
                                JOptionPane.showMessageDialog(null,"Você não possui mais chances de Deletar!");
                            }
                            //atorRede.enviaJogada(new JogadaRemover(coluna, linha, this.getConnect().getTabuleiro().getJogadorLocal().getId()));
                        }			
		}
		connect.getTabuleiro().repaint();
    }

    public void mousePressed(MouseEvent me) {    }
    public void mouseReleased(MouseEvent me) { }
    public void mouseEntered(MouseEvent me) {    }
    public void mouseExited(MouseEvent me) {    }
    public AtorRede getAtorRede() {
        return atorRede;
    }
    public void setAtorRede(AtorRede atorRede) {
        this.atorRede = atorRede;
    }
}

