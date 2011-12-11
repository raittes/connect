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
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;

public class AtorRede implements OuvidorProxy {
		
	private AtorJogador atorJogador;
	private Proxy proxy;
	private boolean minhaVez = false;
		
	public AtorRede(AtorJogador atorJogador){
		super();
		this.atorJogador = atorJogador;
		this.proxy = Proxy.getInstance();
                this.proxy.addOuvinte(this);
	}
        public boolean isMinhaVez() {
            return minhaVez;
        }
        public void setMinhaVez(boolean minhaVez) {
            this.minhaVez = minhaVez;
        }
	public boolean conectar(String servidor, String nome){
		try {
                        proxy.conectar(servidor, nome);
                        return true;
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
                        return false;
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
                        return false;
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
                        return false;
		}
	}
	public void iniciaPartida(){                
		try {
                    proxy.iniciarPartida(2);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}            
	}		
	public void enviaJogada(Jogada jogada){
	    try {
                	proxy.enviaJogada(jogada);
			minhaVez = false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
		
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		if(posicao==1){
			minhaVez = true;                        
		}
                else if(posicao == 2){
			minhaVez =false;
		}                
		atorJogador.iniciaPartida(minhaVez);                
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	@Override
	public void receberMensagem(String msg) {
		atorJogador.exibeMensagem(msg);
	}
	@Override
	public void receberJogada(Jogada jogada) {   
                              
                if(jogada instanceof JogadaAdicionar)
                    atorJogador.getConnect().getTabuleiro().executaJogadaAdicionar((JogadaAdicionar)jogada);
                else if (jogada instanceof JogadaRemover)
                    atorJogador.getConnect().getTabuleiro().executaJogadaRemover((JogadaRemover)jogada);                
                
                // Verifica Vencedor            
                minhaVez = true; 
                atorJogador.verificaVencedor();
                
              
	}
	public void desconectar(){
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}	
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
	}
	@Override
	public void tratarPartidaNaoIniciada(String message) {
		int confirm = JOptionPane.showConfirmDialog(null, message);
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
