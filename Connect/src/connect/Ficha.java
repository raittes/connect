package connect;

public class Ficha {
	private int idJogador;
	
	public Ficha(){
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
}