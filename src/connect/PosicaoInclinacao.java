package connect;

public class PosicaoInclinacao extends Posicao{
	private int inclinacao;
	
	public PosicaoInclinacao(int inclinacao){
		super();
		this.setInclinacao(inclinacao);
	}

	public int getInclinacao() {
		return inclinacao;
	}

	public void setInclinacao(int inclinacao) {
		this.inclinacao = inclinacao;
	}
}
