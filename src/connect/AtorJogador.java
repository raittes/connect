package connect;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import servidor.AtorRede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;


public class AtorJogador {

	private JFrame jFrame;	
	private JPanel painelPrincipal;
	private Tabuleiro tabuleiro;
	private AtorRede atorRede;
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}


	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	private String nome;
	

	
	
	public AtorJogador(){
		super();
		atorRede = new AtorRede(this);
	}

	
	public void configura(){
		
		jFrame = new JFrame("Connect Plus");
		nome = JOptionPane.showInputDialog(jFrame, "Digite seu nome ou apelido:");
		atorRede.conectar(nome, "localhost");
	}
	
	
	public void iniciaPartida(boolean comecoJogando) {
		String nomeAdversario = atorRede.obterNomeAdversario();
		tabuleiro = new Tabuleiro();
		
		if(comecoJogando){
			tabuleiro.criarParticipante(this.nome);
			tabuleiro.criarParticipante(nomeAdversario);
		}else{
			tabuleiro.criarParticipante(nomeAdversario);
			tabuleiro.criarParticipante(this.nome);
		}
	}
	
	public void iniciarJogo(){
		atorRede.iniciaPartida();
	}
	
	public JFrame getjFrame() {
		return jFrame;
	}
	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}
	public void receberMensagem(String jogada) {
		// TODO Auto-generated method stub
		
	}
	
}
