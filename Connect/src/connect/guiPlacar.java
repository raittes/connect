package connect;

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author raittes
 */
public class guiPlacar extends javax.swing.JPanel {
    private AtorJogador gui;
    
    public guiPlacar(AtorJogador gui) {
        this.gui=gui;
        initComponents();
    }
    
    public void atualiza(){
        jLocal.setText("Jogador Local: "+gui.getConnect().getTabuleiro().getJogadorLocal().getNome());
        jRemoto.setText("Jogador Remoto: "+gui.getConnect().getTabuleiro().getJogadorRemoto().getNome());
        if(gui.getAtorRede().isMinhaVez()){
            labelVez.setText("Sua Vez!"); 
            labelVez.setForeground(new Color(2, 143, 4));
            vez.setBackground(new Color(2, 143, 4));
        }
        else{
            labelVez.setText("Aguarde a jogada do Oponente!");
            labelVez.setForeground(new Color(237, 33, 2));
            vez.setBackground(new Color(237, 33, 2));
        }
        
        //troca icones conforme cor do jogador
        if(gui.getConnect().getTabuleiro().getJogadorLocal().getId()==1){
            jogadorLocal.setIcon(new ImageIcon("src/imagens/jogador1.png"));
            jogadorRemoto.setIcon(new ImageIcon("src/imagens/jogador2.png"));
        }else{
            jogadorLocal.setIcon(new ImageIcon("src/imagens/jogador2.png"));
            jogadorRemoto.setIcon(new ImageIcon("src/imagens/jogador1.png"));            
        }
              
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLocal = new javax.swing.JLabel();
        jRemoto = new javax.swing.JLabel();
        vez = new javax.swing.JPanel();
        labelVez = new javax.swing.JLabel();
        jogadorLocal = new javax.swing.JLabel();
        jogadorRemoto = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(connect.ConnectApp.class).getContext().getResourceMap(guiPlacar.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 150));

        jLocal.setText(resourceMap.getString("jLocal.text")); // NOI18N
        jLocal.setName("jLocal"); // NOI18N

        jRemoto.setText(resourceMap.getString("jRemoto.text")); // NOI18N
        jRemoto.setName("jRemoto"); // NOI18N

        vez.setName("vez"); // NOI18N

        javax.swing.GroupLayout vezLayout = new javax.swing.GroupLayout(vez);
        vez.setLayout(vezLayout);
        vezLayout.setHorizontalGroup(
            vezLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );
        vezLayout.setVerticalGroup(
            vezLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        labelVez.setFont(resourceMap.getFont("labelVez.font")); // NOI18N
        labelVez.setForeground(resourceMap.getColor("labelVez.foreground")); // NOI18N
        labelVez.setText(resourceMap.getString("labelVez.text")); // NOI18N
        labelVez.setName("labelVez"); // NOI18N

        jogadorLocal.setIcon(resourceMap.getIcon("jogadorLocal.icon")); // NOI18N
        jogadorLocal.setText(resourceMap.getString("jogadorLocal.text")); // NOI18N
        jogadorLocal.setName("jogadorLocal"); // NOI18N

        jogadorRemoto.setIcon(resourceMap.getIcon("jogadorRemoto.icon")); // NOI18N
        jogadorRemoto.setText(resourceMap.getString("jogadorRemoto.text")); // NOI18N
        jogadorRemoto.setName("jogadorRemoto"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jogadorLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLocal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jogadorRemoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRemoto)))
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelVez)
                    .addComponent(vez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLocal)
                                .addGap(18, 18, 18)
                                .addComponent(jRemoto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(labelVez))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jogadorLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogadorRemoto)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLocal;
    private javax.swing.JLabel jRemoto;
    private javax.swing.JLabel jogadorLocal;
    private javax.swing.JLabel jogadorRemoto;
    private javax.swing.JLabel labelVez;
    private javax.swing.JPanel vez;
    // End of variables declaration//GEN-END:variables
}
