package connect.logica;

import connect.Jogador;

/**
 *
 * @author raittes
 */
class Ranking {
    private Jogador jLocal;
    private Jogador jRemoto;
    
    public Ranking(){        
    }
    public Ranking(Jogador local, Jogador remoto){
        this.jLocal=local;
        this.jRemoto=remoto;        
    }
    public Jogador getjLocal() {
        return jLocal;
    }
    public void setjLocal(Jogador jLocal) {
        this.jLocal = jLocal;
    }
    public Jogador getjRemoto() {
        return jRemoto;
    }
    public void setjRemoto(Jogador jRemoto) {
        this.jRemoto = jRemoto;
    }
    public String toString(){
        String ranking = "";
        ranking += jLocal.getNome();
        ranking += "\nVitorias: "+jLocal.getVitorias();
        ranking += "\nDerrotas: "+jLocal.getDerrotas();
        ranking += "\nPontuacao: "+jLocal.getPontuacao();
        ranking += "\n\n";
        ranking += jRemoto.getNome()+"\n";
        ranking += "\nVitorias: "+jRemoto.getVitorias();
        ranking += "\nDerrotas: "+jRemoto.getDerrotas();
        ranking += "\nPontuacao: "+jRemoto.getPontuacao();
        return ranking;
    }    
}
