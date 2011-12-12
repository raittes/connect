package connect;

import java.awt.Color;
import javax.swing.JPanel;


public class Placar extends JPanel {
    public Placar(){        
        this.configura();        
    }
    private void configura(){        
        this.setBackground(Color.BLACK);
        this.setLayout(new java.awt.BorderLayout());
        this.setPreferredSize(new java.awt.Dimension(200,400));
        this.setName("Placar"); 
    }
   
    
    
}
