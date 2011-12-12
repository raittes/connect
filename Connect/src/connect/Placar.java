package connect;






//aposentado










import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Placar extends JPanel {
    private AtorJogador gui;
    private JLabel jogador1;
    private JLabel jogador2;
    private JLabel vez;
    
    public Placar(AtorJogador gui){        
        this.gui=gui;
        this.init();        
        this.configura();        
        this.desenha();
        
    }
    private void init() {
        jogador1 = new JLabel();
        jogador2 = new JLabel();
        vez = new JLabel();
    }
    private void configura(){        
        this.setBackground(Color.BLACK);
        this.setLayout(new java.awt.GridLayout(2,4));
        this.setPreferredSize(new java.awt.Dimension(640,100));
        this.setName("Placar"); 
    }
    
    public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g2);		
    Image j1 = new ImageIcon("src/imagens/jogador1.png").getImage();
    Image j2 = new ImageIcon("src/imagens/jogador2.png").getImage();
    Image vitoriaLocal = new ImageIcon("src/imagens/vitoria1.png").getImage();
    Image vitoriaRemoto = new ImageIcon("src/imagens/vitoria2.png").getImage();

    
    
        g.drawImage(j1, 2, 10, 35, 35, this);        
        g.drawImage(j2, 2, 50, 35, 35, this);        
    }


    private void desenha() {
        
        jogador1.setText("        Jogador Local = "+gui.getConnect().getTabuleiro().getJogadorLocal().getNome());
        jogador2.setText("        Jogador Remoto = "+gui.getConnect().getTabuleiro().getJogadorRemoto().getNome());
        this.add(jogador1);
        this.add(jogador2);
                
        if(gui.getAtorRede().isMinhaVez())
            vez.setText("SUA VEZ");
        else
            vez.setText("ESPERE");
        
        //this.add(vez);
        this.repaint();
        
        
    }
    
}
