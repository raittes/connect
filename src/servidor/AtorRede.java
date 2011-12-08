package servidor;

import javax.swing.JOptionPane;

import connect.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorRede implements OuvidorProxy {
	
	
	private AtorJogador atorJogador;
	private Proxy proxy;
	private boolean minhaVez = false;
	
	
	public AtorRede(AtorJogador atorJogador){
		super();
		this.atorJogador = atorJogador;
		proxy = Proxy.getInstance();
	}
	
	public void conectar(String nome, String servidor){
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void iniciaPartida(){
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void enviaJogada(String jogada){
		
		JogadaConnect jgd = new JogadaConnect(jogada);
		try {
			proxy.enviaJogada(jgd);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if(posicao==1){
			minhaVez = true;
		}else if(posicao == 2){
			minhaVez =false;
		}
		atorJogador.iniciaPartida(minhaVez);

	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String msg) {
		atorJogador.getTabuleiro().trataJogada(msg);

	}

	@Override
	public void receberJogada(Jogada jogada) {
		JogadaConnect jogadaConnect = (JogadaConnect) jogada;
		atorJogador.receberMensagem(jogadaConnect.getJogada());
	}

	public void desconectar(){
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(atorJogador.getjFrame(), e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	public String obterNomeAdversario() {
		String nome = "";
		if(minhaVez){
			nome = proxy.obterNomeAdversario(2);
		}else{
			nome = proxy.obterNomeAdversario(1);
		}
		return nome;
	}

}
