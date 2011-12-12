package connect;

import connect.logica.Connect;
import connect.logica.JogadaAdicionar;
import connect.logica.JogadaRemover;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
	private static final int PLANO = 0;	
	private static final int ESQUERDA = 1;
	private static final int DIREITA  = 2;

	private HashMap<String, Ficha> fichas;
	private Jogador jogadorLocal;
	private Jogador jogadorRemoto;
        private Connect connect;
        
        public Tabuleiro() {
            configura();
            this.fichas = new HashMap<String, Ficha>(); 
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
                executeAnimacao(jogada.getColuna());
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
                // Desenha o tabuleiro
            	Graphics2D g2 = (Graphics2D) g;
	  	super.paintComponent(g2);		
		Image tb = new ImageIcon("src/imagens/tabuleiro.png").getImage();
                Image jLocal = new ImageIcon("src/imagens/jogador1.png").getImage();
                Image jRemoto = new ImageIcon("src/imagens/jogador2.png").getImage();
                Image vitoriaLocal = new ImageIcon("src/imagens/vitoria1.png").getImage();
                Image vitoriaRemoto = new ImageIcon("src/imagens/vitoria2.png").getImage();
                Image vazia = new ImageIcon("src/imagens/vazia.png").getImage();
                g.drawImage(tb, 0, 0, 640, 400, this);
                Ficha ficha;
                   for(int i=0; i<10; i++){
                        for(int j=0; j<16; j++){
                            if(fichas.containsKey(getChave(i, j))){
                                ficha = fichas.get(getChave(i, j));
                                if(ficha.getIdJogador()==1){
                                    if(ficha.isVitoria())
                                        g.drawImage(vitoriaLocal, 2+j*40, 2+i*40, 35, 35, this);
                                    else
                                        g.drawImage(jLocal, 2+j*40, 2+i*40, 35, 35, this);
                                    
                                }else if(ficha.getIdJogador()==2){
                                    if(ficha.isVitoria())
                                        g.drawImage(vitoriaRemoto, 2+j*40, 2+i*40, 35, 35, this);
                                    else
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
        public Jogador temVencedor() {
            Jogador jogador = null;
            for(int i= 0;i<10; i++){
                for(int j = 0; j<16; j++){
                    if(jogador == null)
                        jogador = temVencedorAdireita(i,j);
                    if(jogador == null)
                        jogador = temVencedorAesquerda(i,j);
                    if(jogador == null)
                        jogador = temVencedorAbaixo(i,j);
                    if(jogador == null)
                        jogador = temVencedorAcima(i,j);
                    if(jogador == null)
                        jogador = temVencedorDSDireita(i,j);
                    if(jogador == null)
                        jogador = temVencedorDSEsqueda(i,j);
                    if(jogador == null)
                        jogador = temVencedorDIDireita(i,j);
                    if(jogador == null)
                        jogador = temVencedorDIEsquerda(i,j);
                    if(jogador == null)
                        jogador = temVencedorQSDireita(i,j);
                    if(jogador == null)
                        jogador = temVencedorQSEsqueda(i,j);
                    if(jogador == null)
                        jogador = temVencedorQIDireita(i,j);
                    if(jogador == null)
                        jogador = temVencedorQIEsquerda(i,j);
                    if(jogador!=null){
                        return jogador;
                    }
                }
            }
            return jogador;
        }
        private Jogador temVencedorAcima(int i, int j) {        
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i-1, j)),
                    c = fichas.get(getChave(i-2, j)),
                    d = fichas.get(getChave(i-3, j));
             return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorAdireita(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i, j+1)),
                    c = fichas.get(getChave(i, j+2)),
                    d = fichas.get(getChave(i, j+3));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorAesquerda(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i, j-1)),
                    c = fichas.get(getChave(i, j-2)),
                    d = fichas.get(getChave(i, j-3));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorAbaixo(int i, int j) {
                    Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i+1, j)),
                    c = fichas.get(getChave(i+2, j)),
                    d = fichas.get(getChave(i+3, j));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorQIEsquerda(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i+1, j)),
                    c = fichas.get(getChave(i, j-1)),
                    d = fichas.get(getChave(i+1,j-1));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorQIDireita(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i+1, j)),
                    c = fichas.get(getChave(i, j+1)),
                    d = fichas.get(getChave(i+1,j+1));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorQSEsqueda(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i-1, j)),
                    c = fichas.get(getChave(i, j-1)),
                    d = fichas.get(getChave(i-1,j-1));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorQSDireita(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i-1, j)),
                    c = fichas.get(getChave(i, j+1)),
                    d = fichas.get(getChave(i-1,j+1));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorDIEsquerda(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i+1, j-1)),
                    c = fichas.get(getChave(i+2, j-2)),
                    d = fichas.get(getChave(i+3,j-3));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorDSDireita(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i-1, j+1)),
                    c = fichas.get(getChave(i-2, j+2)),
                    d = fichas.get(getChave(i-3,j+3));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorDSEsqueda(int i, int j) {
            Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i-1, j-1)),
                    c = fichas.get(getChave(i-2, j-2)),
                    d = fichas.get(getChave(i-3,j-3));
            return getAvaliaFichas(a,b,c,d);
        }
        private Jogador temVencedorDIDireita(int i, int j) {
                    Ficha   a = fichas.get(getChave(i, j)),
                    b = fichas.get(getChave(i+1, j+1)),
                    c = fichas.get(getChave(i+2, j+2)),
                    d = fichas.get(getChave(i+3,j+3));
            return getAvaliaFichas(a,b,c,d);
        }    
        private Jogador getAvaliaFichas(Ficha a,Ficha b, Ficha c, Ficha d){
            Jogador retorno = null;

            if(a!=null&& b!=null&&c!=null&&d!=null){
                if(a.getIdJogador()==b.getIdJogador()&&c.getIdJogador()==d.getIdJogador()&&a.getIdJogador()==c.getIdJogador()){
                    a.setVitoria();
                    b.setVitoria();
                    c.setVitoria();
                    d.setVitoria();

                    if(c.getIdJogador()==jogadorLocal.getId())
                        retorno = jogadorLocal;
                    else
                        retorno = jogadorRemoto;
                    //Pontuacao a cada vitoria
                    retorno.ganhaPontos(100);                    
                }
            }
            return retorno;
        }

        public void executeAnimacao(int colunaAnimacao) {
            // A N I M A C A O
            int ultima = this.getUltimaPosicaoLivre(colunaAnimacao);
                for(int animacao=0; animacao<=ultima;animacao++){                        
                    try{
                        this.fichas.put((animacao+"_"+colunaAnimacao),new Ficha(0));
                        this.repaint();
                        Thread.sleep(30);
                        this.fichas.remove((animacao+"_"+colunaAnimacao));
                    }catch (Exception e){}
                }
        }
    private void configura() {
        
        this.setName("Tabuleiro"); 
        this.setBackground(new java.awt.Color(192, 191, 191));
        this.setLayout(new java.awt.BorderLayout());
        this.setPreferredSize(new java.awt.Dimension(640,400));       
               
        }
    public void zerar(){
        this.fichas = new HashMap<String, Ficha>();
        this.repaint();
    }
          
}