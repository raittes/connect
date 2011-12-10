package connect;

public class Jogador {
	
	String nome;
	private int deletes;
	
	public Jogador(String nome){
		this.nome = nome;
		this.deletes = 5;
	}
	
	public Jogador(){
		this.deletes = 5;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean podeDeletarPecaDoAdversario() {
		if(deletes>0)
			return true;
		return false;
	}
	public void deletouPecaDoAdversario(){
		this.deletes--;
	}

}
