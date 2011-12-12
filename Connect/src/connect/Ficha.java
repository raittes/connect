package connect;

public class Ficha {
	private int idJogador;
	private boolean vitoria;
        
	public Ficha(){
            this.vitoria=false;
	}
	public Ficha(int idJogador){
		this.setIdJogador(idJogador);
	}        
        public int getIdJogador() {
            return idJogador;
        }

        public void setIdJogador(int idJogador) {
            this.idJogador = idJogador;
        }
        public boolean isVitoria() {
            return vitoria;
        }
        public void setVitoria() {
            this.vitoria = true;
        }       
        
}