package connect;

import connect.logica.Connect;
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import servidor.JogadaConnect;

public class Tabuleiro extends JPanel {
	private static final int PLANO = 0;	
	private static final int ESQUERDA = 1;
	private static final int DIREITA  = 2;
	/*
	 * Serializacao em string:
	 * 
	 * primeiro numero = numero de colunas
	 * faz um loop
	 * segundo numero = numero de linhas da primeira coluna
	 * pega os N numeros de valores seguintes para preencher as linhas daquela coluna e sendo o ultimo a inclinacao do campo
	 * 
	 * exemplo
	 * 
	 * C = Coluna, L = Linha, V = Valor da linha e coluna, I = Indice.
	 * CLVVVVVVVILVVVVVI...LCVVVVI
	 *	
	 *	3211140221030010
	 *	3 = numero de colunas
	 *		2 numero de linhas da coluna 1 
	 *			 11 valores
	 *				1 inclinacao
	 *		4 numero de linhas da coluna 2
	 *			0221 valores
	 *				0 = inclinacao
	 *		3 numero de linhas da coluna 3
	 *			001 valores
	 *				inclinacao
	 *
	 *						0			1			2			3
	 *
	 *Valores			sem peca	jogador 1	jogador 2	casa bloqueada
	 *inclinacao		plano		esquerda	direita 
	 * 
	 */
	private HashMap<String, Ficha> fichas;
	private Jogador jogadorLocal;
	private Jogador jogadorRemoto;
        private Connect connect;
	
//	public Tabuleiro(String imagemTabuleiro) {
//		int nColunas = getFirstAsInt(imagemTabuleiro);
//		this.linhaColuna = new Posicao[nColunas][3];
//		int nLinhas;
//		for (Posicao[] coluna : this.linhaColuna) {
//			nLinhas = getFirstAsInt(imagemTabuleiro);
//			coluna = new Posicao[nLinhas+1];
//			for (int j = 0; j<nLinhas;j++) {
//				coluna[j] = getFirstAsPosicao(imagemTabuleiro);
//			}
//			coluna[coluna.length-1] = this.getFirstAsInclinacao(imagemTabuleiro);			
//		}
//	}
//
        public Tabuleiro() {
           this.fichas = new HashMap<String, Ficha>(); 
           //this.connect = connect;
        }
	private Posicao getFirstAsInclinacao(String imagemTabuleiro) {
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return new PosicaoInclinacao(Integer.parseInt(aux));
	}
	private Posicao getFirstAsPosicao(String imagemTabuleiro) {
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		Posicao retorno = null;
		switch (Integer.parseInt(aux)) {
		case 1:
			new Posicao(new Ficha(this.getJogadorLocal().getId()),true);
			break;
		case 2: 
			new Posicao(new Ficha(this.getJogadorRemoto().getId()),true);
			break;
		default:
			new Posicao();
			break;
		}
		return retorno;
	}
        
	private int getFirstAsInt(String imagemTabuleiro){
		String aux = ""+imagemTabuleiro.charAt(0);
		imagemTabuleiro = imagemTabuleiro.substring(1);
		return Integer.parseInt(aux);
	}
	public boolean executaJogadaAdicionar(JogadaAdicionar jogada) {
		if(this.temPosisaoLivre(jogada.getColuna())){
			this.depositaFicha(new Ficha(jogada.getIdJogador()), jogada.getColuna(),jogada.getIdJogador());
                        this.repaint();
                        return true;
		}
                return false;
		
	}
	private void depositaFicha(Ficha ficha, int coluna, int idJogador) {
            int ultima = getUltimaPosicaoLivre(coluna);
		if (ultima != -1) {
			fichas.put((ultima+"_"+coluna),new Ficha(idJogador));
		}		
	}
	private boolean temPosisaoLivre(int coluna) {
		if(coluna<4 || coluna>11){
                    for(int i = 0; i < 8;i++){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return true;
                        }
                    }
                }else if(coluna==4||coluna==11){
                    for(int i = 0; i < 9;i++){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return true;
                        }
                    }
                }else{
                    for(int i = 0; i < 10;i++){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return true;
                        }
                    }                    
                }
                return false;
	}
	public boolean executaJogadaRemover(JogadaRemover jogada) {
		if(fichas.containsKey(jogada.getLinha()+"_"+jogada.getColuna())){
                   if(fichas.get(jogada.getLinha()+"_"+jogada.getColuna()).getIdJogador()!=jogada.getIdJogador()){
                       fichas.remove(jogada.getLinha()+"_"+jogada.getColuna());
                       fazCascata(jogada.getLinha(),jogada.getColuna());
                       this.repaint();
                       return true;
                   }                    
                }
                return false;
		
	}

        public Jogador getJogadorLocal() {
            return jogadorLocal;
        }

        public void setJogadorLocal(Jogador jogadorLocal) {
            this.jogadorLocal = jogadorLocal;
        }

        public Jogador getJogadorRemoto() {
            return jogadorRemoto;
        }

        public void setJogadorRemoto(Jogador jogadorRemoto) {
            this.jogadorRemoto = jogadorRemoto;
        }

     	public void paintComponent(Graphics g) {
		
            	Graphics2D g2 = (Graphics2D) g;
	  	super.paintComponent(g2);		
		Image tb = new ImageIcon("src/imagens/tabuleiro.png").getImage();
                Image jLocal = new ImageIcon("src/imagens/jogador1.png").getImage();
                Image jRemoto = new ImageIcon("src/imagens/jogador2.png").getImage();
                Image vazia = new ImageIcon("src/imagens/vazia.png").getImage();
                g.drawImage(tb, 0, 0, 640, 400, this);
                Ficha ficha;
                   for(int i=0; i<10; i++){
                        for(int j=0; j<16; j++){
                            if(fichas.containsKey(getChave(i, j))){
                                ficha = fichas.get(getChave(i, j));
                                if(ficha.getIdJogador()==this.getJogadorLocal().getId()){
                                    g.drawImage(jLocal, 2+j*40, 2+i*40, 35, 35, this);
                                }else if(ficha.getIdJogador() == this.getJogadorRemoto().getId()){
                                    g.drawImage(jRemoto, 2+j*40, 2+i*40, 35, 35, this);
                                }else{
                                    g.drawImage(vazia, 2+j*40, 2+i*40, 35, 35, this);
                                }
                            }
                        }
                    }
	}

    private int getUltimaPosicaoLivre(int coluna) {
       
                if(coluna<4 || coluna>11){
                    for(int i = 7; i >=0;i--){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return i;
                        }
                    }
                }else if(coluna==4||coluna==11){
                    for(int i = 8; i >=0;i--){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return i;
                        }
                    }
                }else{
                    for(int i = 9; i >= 0;i--){
                        if(!fichas.containsKey(i+"_"+coluna)){
                            return i;
                        }
                    }                    
                }
                return -1;
    }

    private void fazCascata(int linha ,int coluna) {
        puxaODeCima(linha, coluna);
         /*if(coluna<4 || coluna>11){
                    if(int i = linha; i >=0;i--){
             
                        puxaODeCima(i,coluna);
                    }
                }else if(coluna==4||coluna==11){
                    for(int i = linha; i >=0;i--){
                        puxaODeCima(i,coluna);
                    }
                }else{
                    for(int i = linha; i >= 0;i--){
                        puxaODeCima(i,coluna);
                    }                    
                }*/
    }

    private void puxaODeCima(int i, int coluna) {
        
        Ficha aux;
        if(fichas.containsKey(getChave(i-1, coluna))){
            aux = fichas.get(getChave(i-1, coluna));
                fichas.put(getChave(i,coluna),aux);
                fichas.remove(getChave(i-1, coluna));
                puxaODeCima(i-1, coluna);
        }
    }

    private String getChave(int i, int coluna) {
        return i+"_"+coluna;
    }
      
}