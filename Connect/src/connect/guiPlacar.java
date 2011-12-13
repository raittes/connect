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
        jLocal.setText("Você: "+gui.getConnect().getTabuleiro().getJogadorLocal().getNome());
        jRemoto.setText("Adversário: "+gui.getConnect().getTabuleiro().getJogadorRemoto().getNome());
       //troca icones conforme cor do jogador
        if(gui.getConnect().getTabuleiro().getJogadorLocal().getId()==1){
            jogadorLocal.setIcon(new ImageIcon("src/imagens/jogador1.png"));
            jogadorRemoto.setIcon(new ImageIcon("src/imagens/jogador2.png"));
        }else{
            jogadorLocal.setIcon(new ImageIcon("src/imagens/jogador2.png"));
            jogadorRemoto.setIcon(new ImageIcon("src/imagens/jogador1.png"));            
        }              
         if(gui.getAtorRede().isMinhaVez()){
             vez.setIcon(jogadorLocal.getIcon());
        }
        else{
            vez.setIcon(jogadorRemoto.getIcon());
        }        
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLocal = new javax.swing.JLabel();
        jRemoto = new javax.swing.JLabel();
        labelVez = new javax.swing.JLabel();
        jogadorLocal = new javax.swing.JLabel();
        jogadorRemoto = new javax.swing.JLabel();
        vez = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(connect.ConnectApp.class).getContext().getResourceMap(guiPlacar.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 150));

        jLocal.setFont(resourceMap.getFont("jLocal.font")); // NOI18N
        jLocal.setText(resourceMap.getString("jLocal.text")); // NOI18N
        jLocal.setName("jLocal"); // NOI18N

        jRemoto.setFont(resourceMap.getFont("jRemoto.font")); // NOI18N
        jRemoto.setText(resourceMap.getString("jRemoto.text")); // NOI18N
        jRemoto.setName("jRemoto"); // NOI18N

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

        vez.setIcon(resourceMap.getIcon("vez.icon")); // NOI18N
        vez.setText(resourceMap.getString("vez.text")); // NOI18N
        vez.setName("vez"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jogadorLocal)
                    .addComponent(jogadorRemoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLocal)
                            .addComponent(jRemoto))
                        .addGap(187, 187, 187))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelVez)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(vez, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(305, 305, 305))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLocal)
                        .addGap(8, 8, 8)
                        .addComponent(labelVez)
                        .addGap(4, 4, 4)
                        .addComponent(jRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vez, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jogadorLocal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jogadorRemoto)))))
                .addGap(65, 65, 65))
        );

        vez.getAccessibleContext().setAccessibleName(resourceMap.getString("vez.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLocal;
    private javax.swing.JLabel jRemoto;
    private javax.swing.JLabel jogadorLocal;
    private javax.swing.JLabel jogadorRemoto;
    private javax.swing.JLabel labelVez;
    private javax.swing.JLabel vez;
    // End of variables declaration//GEN-END:variables
}
