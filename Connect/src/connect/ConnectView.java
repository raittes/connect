package connect;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class ConnectView extends FrameView {
    AtorJogador gui;
    public ConnectView(SingleFrameApplication app) {
        super(app);
        
        initComponents();
        // ATOR JOGADOR
        gui = new AtorJogador(mainPanel, statusMessageLabel,this);
        statusMessageLabel.setText("Aguardando conexão com Servidor!");
        
        
               // thank god
        this.getFrame().setPreferredSize(new Dimension(650, 562));
        this.getFrame().setResizable(false);
        
        
        // Desativa Botoes da Partida
        menuPartida.setEnabled(false);
        menuJogada.setEnabled(false);
        menuVerRaking.setEnabled(false);  
        menuDesconectar.setEnabled(false);
        
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = ConnectApp.getApplication().getMainFrame();
            aboutBox = new ConnectAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ConnectApp.getApplication().show(aboutBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        Conectando = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomeJogador = new javax.swing.JTextField();
        botaoConectar = new javax.swing.JToggleButton();
        nomeServidor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Conectado = new javax.swing.JPanel();
        buttonIniciarPartida = new javax.swing.JButton();
        Partida = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        menuConectar = new javax.swing.JMenuItem();
        menuDesconectar = new javax.swing.JMenuItem();
        menuVerRaking = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        menuPartida = new javax.swing.JMenu();
        partidaIniciar = new javax.swing.JMenuItem();
        partidaReiniciar = new javax.swing.JMenuItem();
        partidaFinalizar = new javax.swing.JMenuItem();
        menuJogada = new javax.swing.JMenu();
        inserir = new javax.swing.JRadioButtonMenuItem();
        remover = new javax.swing.JRadioButtonMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(900, 600));
        mainPanel.setLayout(new java.awt.CardLayout());

        Conectando.setName("Conectando"); // NOI18N
        Conectando.setPreferredSize(new java.awt.Dimension(900, 600));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(connect.ConnectApp.class).getContext().getResourceMap(ConnectView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        nomeJogador.setText(resourceMap.getString("nomeJogador.text")); // NOI18N
        nomeJogador.setName("nomeJogador"); // NOI18N

        botaoConectar.setText(resourceMap.getString("botaoConectar.text")); // NOI18N
        botaoConectar.setName("botaoConectar"); // NOI18N
        botaoConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConectarActionPerformed(evt);
            }
        });

        nomeServidor.setText(resourceMap.getString("nomeServidor.text")); // NOI18N
        nomeServidor.setName("nomeServidor"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout ConectandoLayout = new javax.swing.GroupLayout(Conectando);
        Conectando.setLayout(ConectandoLayout);
        ConectandoLayout.setHorizontalGroup(
            ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectandoLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(nomeServidor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeJogador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(botaoConectar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        ConectandoLayout.setVerticalGroup(
            ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectandoLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoConectar)
                .addContainerGap(206, Short.MAX_VALUE))
        );

        mainPanel.add(Conectando, "Conectando");

        Conectado.setName("Conectado"); // NOI18N

        buttonIniciarPartida.setFont(resourceMap.getFont("buttonIniciarPartida.font")); // NOI18N
        buttonIniciarPartida.setText(resourceMap.getString("buttonIniciarPartida.text")); // NOI18N
        buttonIniciarPartida.setName("buttonIniciarPartida"); // NOI18N
        buttonIniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciarPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConectadoLayout = new javax.swing.GroupLayout(Conectado);
        Conectado.setLayout(ConectadoLayout);
        ConectadoLayout.setHorizontalGroup(
            ConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectadoLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(buttonIniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        ConectadoLayout.setVerticalGroup(
            ConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectadoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(buttonIniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        mainPanel.add(Conectado, "Conectado");

        Partida.setName("Partida"); // NOI18N
        Partida.setPreferredSize(new java.awt.Dimension(700, 600));

        javax.swing.GroupLayout PartidaLayout = new javax.swing.GroupLayout(Partida);
        Partida.setLayout(PartidaLayout);
        PartidaLayout.setHorizontalGroup(
            PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );
        PartidaLayout.setVerticalGroup(
            PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        mainPanel.add(Partida, "Partida");

        menuBar.setMinimumSize(new java.awt.Dimension(300, 20));
        menuBar.setName("menuBar"); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(300, 20));

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        menuConectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuConectar.setText(resourceMap.getString("Conectar.text")); // NOI18N
        menuConectar.setActionCommand(resourceMap.getString("Conectar.actionCommand")); // NOI18N
        menuConectar.setName("Conectar"); // NOI18N
        menuConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConectarActionPerformed(evt);
            }
        });
        fileMenu.add(menuConectar);

        menuDesconectar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuDesconectar.setText(resourceMap.getString("menuDesconectar.text")); // NOI18N
        menuDesconectar.setName("menuDesconectar"); // NOI18N
        menuDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDesconectarActionPerformed(evt);
            }
        });
        fileMenu.add(menuDesconectar);

        menuVerRaking.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menuVerRaking.setText(resourceMap.getString("raking.text")); // NOI18N
        menuVerRaking.setActionCommand(resourceMap.getString("raking.actionCommand")); // NOI18N
        menuVerRaking.setName("raking"); // NOI18N
        menuVerRaking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerRakingActionPerformed(evt);
            }
        });
        fileMenu.add(menuVerRaking);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(connect.ConnectApp.class).getContext().getActionMap(ConnectView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        menuPartida.setText(resourceMap.getString("iniciar.text")); // NOI18N
        menuPartida.setName("iniciar"); // NOI18N

        partidaIniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        partidaIniciar.setText(resourceMap.getString("partidaIniciar.text")); // NOI18N
        partidaIniciar.setName("partidaIniciar"); // NOI18N
        partidaIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partidaIniciarActionPerformed(evt);
            }
        });
        menuPartida.add(partidaIniciar);

        partidaReiniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        partidaReiniciar.setText(resourceMap.getString("reiniciar.text")); // NOI18N
        partidaReiniciar.setName("reiniciar"); // NOI18N
        partidaReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partidaReiniciarActionPerformed(evt);
            }
        });
        menuPartida.add(partidaReiniciar);

        partidaFinalizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        partidaFinalizar.setText(resourceMap.getString("finalizar.text")); // NOI18N
        partidaFinalizar.setName("finalizar"); // NOI18N
        partidaFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partidaFinalizarActionPerformed(evt);
            }
        });
        menuPartida.add(partidaFinalizar);

        menuBar.add(menuPartida);

        menuJogada.setText(resourceMap.getString("menuJogada.text")); // NOI18N
        menuJogada.setName("menuJogada"); // NOI18N

        inserir.setSelected(true);
        inserir.setText(resourceMap.getString("inserir.text")); // NOI18N
        inserir.setName("inserir"); // NOI18N
        inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirActionPerformed(evt);
            }
        });
        menuJogada.add(inserir);

        remover.setSelected(false);
        remover.setText(resourceMap.getString("remover.text")); // NOI18N
        remover.setName("remover"); // NOI18N
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });
        menuJogada.add(remover);

        menuBar.add(menuJogada);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void menuConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConectarActionPerformed
      statusMessageLabel.setText("Estabelecendo conexão...");   
        
        if(nomeJogador.getText().isEmpty()){ 
            statusMessageLabel.setText("ERRO: Nome em branco!");   
        }else if(nomeServidor.getText().isEmpty()){
            statusMessageLabel.setText("ERRO: Servidor em branco");   
        }else{            
            gui.setServidor(nomeServidor.getText());
            gui.setNome(nomeJogador.getText());      
            if(estabelecerConexao()){
                menuPartida.setEnabled(true);    
                menuDesconectar.setEnabled(true);
                menuVerRaking.setEnabled(false);
                partidaIniciar.setEnabled(true);
                partidaFinalizar.setEnabled(false);
                partidaReiniciar.setEnabled(false);
                menuConectar.setEnabled(false);
                statusMessageLabel.setText("Conexão estabelecida! - Aguardando Partida");
            }
        }        
       
    }//GEN-LAST:event_menuConectarActionPerformed

    private void menuDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDesconectarActionPerformed
        gui.getAtorRede().desconectar();        
        ((java.awt.CardLayout)mainPanel.getLayout()).show(mainPanel,"Conectando");
        statusMessageLabel.setText("Conexão Encerrada");
        menuPartida.setEnabled(false);
        menuJogada.setEnabled(false);
        menuVerRaking.setEnabled(false);
        menuConectar.setEnabled(true);
        menuDesconectar.setEnabled(false);
    }//GEN-LAST:event_menuDesconectarActionPerformed

    private void botaoConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConectarActionPerformed
        statusMessageLabel.setText("Estabelecendo conexão...");   
        
        if(nomeJogador.getText().isEmpty()){ 
            statusMessageLabel.setText("ERRO: Nome em branco!");   
        }else if(nomeServidor.getText().isEmpty()){
            statusMessageLabel.setText("ERRO: Servidor em branco");   
        }else{            
            gui.setServidor(nomeServidor.getText());
            gui.setNome(nomeJogador.getText());      
            if(estabelecerConexao()){
                menuPartida.setEnabled(true);    
                menuDesconectar.setEnabled(true);
                menuVerRaking.setEnabled(false);
                partidaIniciar.setEnabled(true);
                partidaFinalizar.setEnabled(false);
                partidaReiniciar.setEnabled(false);
                menuConectar.setEnabled(false);
                statusMessageLabel.setText("Conexão estabelecida! - Aguardando Partida");
            }
        }        

    }//GEN-LAST:event_botaoConectarActionPerformed

    private void buttonIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIniciarPartidaActionPerformed
        gui.iniciarJogo();  
        statusMessageLabel.setText("Nova Partida");
    }//GEN-LAST:event_buttonIniciarPartidaActionPerformed

    private void inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirActionPerformed
        gui.getConnect().setTipoJogadaInsercao(true);
        remover.setSelected(false);        
    }//GEN-LAST:event_inserirActionPerformed
    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
          gui.getConnect().setTipoJogadaInsercao(false);
          inserir.setSelected(false);
    }//GEN-LAST:event_removerActionPerformed

    private void partidaReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partidaReiniciarActionPerformed
        gui.reiniciaPartida();        
    }//GEN-LAST:event_partidaReiniciarActionPerformed

    private void menuVerRakingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerRakingActionPerformed
        JOptionPane.showMessageDialog(null, ""+gui.getConnect().getRanking());
        
    }//GEN-LAST:event_menuVerRakingActionPerformed

    private void partidaFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partidaFinalizarActionPerformed
        gui.finalizaPartida();        
    }//GEN-LAST:event_partidaFinalizarActionPerformed

private void partidaIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partidaIniciarActionPerformed
        gui.iniciarJogo();   
        statusMessageLabel.setText("Nova Partida");   
}//GEN-LAST:event_partidaIniciarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Conectado;
    private javax.swing.JPanel Conectando;
    private javax.swing.JPanel Partida;
    private javax.swing.JToggleButton botaoConectar;
    private javax.swing.JButton buttonIniciarPartida;
    private javax.swing.JRadioButtonMenuItem inserir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConectar;
    private javax.swing.JMenuItem menuDesconectar;
    private javax.swing.JMenu menuJogada;
    private javax.swing.JMenu menuPartida;
    private javax.swing.JMenuItem menuVerRaking;
    private javax.swing.JTextField nomeJogador;
    private javax.swing.JTextField nomeServidor;
    private javax.swing.JMenuItem partidaFinalizar;
    private javax.swing.JMenuItem partidaIniciar;
    private javax.swing.JMenuItem partidaReiniciar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButtonMenuItem remover;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;

    private boolean estabelecerConexao() {
        
        if(gui.estabelecerConexao())
        {
            statusMessageLabel.setText("Conectado ao Servidor!");
            ((java.awt.CardLayout)mainPanel.getLayout()).show(mainPanel,"Conectado");
            menuVerRaking.setEnabled(true);
            menuPartida.setEnabled(true);
            partidaReiniciar.setEnabled(true);
            partidaFinalizar.setEnabled(true);
            inserir.setEnabled(true);
            remover.setEnabled(true);
            return true;
        }else{
            statusMessageLabel.setText("Erro na conexão!");
            return false;
        }
        
    }

    public JPanel getConectado() {
        return Conectado;
    }

    public void setConectado(JPanel Conectado) {
        this.Conectado = Conectado;
    }

    public JPanel getConectando() {
        return Conectando;
    }

    public void setConectando(JPanel Conectando) {
        this.Conectando = Conectando;
    }

    public JPanel getPartida() {
        return Partida;
    }

    public void setPartida(JPanel Partida) {
        this.Partida = Partida;
    }

    public JDialog getAboutBox() {
        return aboutBox;
    }

    public void setAboutBox(JDialog aboutBox) {
        this.aboutBox = aboutBox;
    }

    public JToggleButton getBotaoConectar() {
        return botaoConectar;
    }

    public void setBotaoConectar(JToggleButton botaoConectar) {
        this.botaoConectar = botaoConectar;
    }

    public int getBusyIconIndex() {
        return busyIconIndex;
    }

    public void setBusyIconIndex(int busyIconIndex) {
        this.busyIconIndex = busyIconIndex;
    }

    public JButton getButtonIniciarPartida() {
        return buttonIniciarPartida;
    }

    public void setButtonIniciarPartida(JButton buttonIniciarPartida) {
        this.buttonIniciarPartida = buttonIniciarPartida;
    }

    public AtorJogador getGui() {
        return gui;
    }

    public void setGui(AtorJogador gui) {
        this.gui = gui;
    }

    public JRadioButtonMenuItem getInserir() {
        return inserir;
    }

    public void setInserir(JRadioButtonMenuItem inserir) {
        this.inserir = inserir;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JMenuItem getMenuConectar() {
        return menuConectar;
    }

    public void setMenuConectar(JMenuItem menuConectar) {
        this.menuConectar = menuConectar;
    }

    public JMenuItem getMenuDesconectar() {
        return menuDesconectar;
    }

    public void setMenuDesconectar(JMenuItem menuDesconectar) {
        this.menuDesconectar = menuDesconectar;
    }

    public JMenu getMenuJogada() {
        return menuJogada;
    }

    public void setMenuJogada(JMenu menuJogada) {
        this.menuJogada = menuJogada;
    }

    public JMenu getMenuPartida() {
        return menuPartida;
    }

    public void setMenuPartida(JMenu menuPartida) {
        this.menuPartida = menuPartida;
    }

    public JMenuItem getMenuVerRaking() {
        return menuVerRaking;
    }

    public void setMenuVerRaking(JMenuItem menuVerRaking) {
        this.menuVerRaking = menuVerRaking;
    }

    public JTextField getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(JTextField nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public JTextField getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(JTextField nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public JMenuItem getPartidaFinalizar() {
        return partidaFinalizar;
    }

    public void setPartidaFinalizar(JMenuItem partidaFinalizar) {
        this.partidaFinalizar = partidaFinalizar;
    }

    public JMenuItem getPartidaIniciar() {
        return partidaIniciar;
    }

    public void setPartidaIniciar(JMenuItem partidaIniciar) {
        this.partidaIniciar = partidaIniciar;
    }

    public JMenuItem getPartidaReiniciar() {
        return partidaReiniciar;
    }

    public void setPartidaReiniciar(JMenuItem partidaReiniciar) {
        this.partidaReiniciar = partidaReiniciar;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JRadioButtonMenuItem getRemover() {
        return remover;
    }

    public void setRemover(JRadioButtonMenuItem remover) {
        this.remover = remover;
    }

    public JLabel getStatusAnimationLabel() {
        return statusAnimationLabel;
    }

    public void setStatusAnimationLabel(JLabel statusAnimationLabel) {
        this.statusAnimationLabel = statusAnimationLabel;
    }

    public JLabel getStatusMessageLabel() {
        return statusMessageLabel;
    }

    public void setStatusMessageLabel(JLabel statusMessageLabel) {
        this.statusMessageLabel = statusMessageLabel;
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }

    public void setStatusPanel(JPanel statusPanel) {
        this.statusPanel = statusPanel;
    }
    
}
