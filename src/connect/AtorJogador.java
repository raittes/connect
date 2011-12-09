package connect;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connect.logica.Connect;

import servidor.AtorRede;
import servidor.JogadaConnect;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;


public class AtorJogador {

	private JFrame jFrame;	
	private JPanel painelPrincipal;
	private Connect connect;
	private AtorRede atorRede;
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
		Connect connect = new Connect(new Tabuleiro("220002000"));
		if(comecoJogando){
			connect.criarParticipante(this.nome);
			connect.criarParticipante(nomeAdversario);
		}else{
			connect.criarParticipante(nomeAdversario);
			connect.criarParticipante(this.nome);
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

	public void receberJogada(Jogada jogada) {
		connect.trataJogada(jogada);
		
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
	
}
