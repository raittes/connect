package connect;

import connect.Jogador;

/**
 *
 * @author raittes
 */
public class Ranking {
    private int vitorias;
    private int derrotas;
    
    
    public Ranking(){        
    }
    public Ranking(Jogador local, Jogador remoto){
        this.vitorias = 0;
        this.derrotas = 0;        
    }
    public void addDerrota(){
        this.derrotas++;
    }
    public void addVitoria(){
        this.vitorias++;
    }
    public String toString(){
        String ranking = "";
        ranking += "\nVitorias: "+this.vitorias;
        ranking += "\nDerrotas: "+this.derrotas;
        ranking += "\nPontuacao: "+(this.vitorias*10-this.derrotas*5);
        return ranking;
    }    
}
