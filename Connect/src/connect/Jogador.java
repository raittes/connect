package connect;

public class Jogador {
	
	private String nome;
        private int id;
        private int dels;
        
        
        public Jogador(int id,String nome) {
                this.dels = 5;
		this.nome = nome;
                this.id = id;
	}
        
	public String getNome() {
            return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

        public boolean hasDels() {
            return (dels>0);
        }
        public void decrementarDels() {
            this.dels--;
        }
        public int getDels() {
            return dels;
        }        	
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        
}
