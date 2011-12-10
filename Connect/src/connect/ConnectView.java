package connect;

import java.awt.Dimension;
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

/**
 * The application's main frame.
 */
public class ConnectView extends FrameView {
    AtorJogador gui;
    public ConnectView(SingleFrameApplication app) {
        super(app);
        
        
        initComponents();
        // ATOR JOGADOR
        gui = new AtorJogador(mainPanel);
        
        
               // thank god
        this.getFrame().setPreferredSize(new Dimension(640, 462));
        this.getFrame().setResizable(false);
               
        
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
        status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nomeJogador = new javax.swing.JTextField();
        informarNomeJogador = new javax.swing.JToggleButton();
        nomeServidor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Conectado = new javax.swing.JPanel();
        status2 = new javax.swing.JLabel();
        buttonIniciarPartida = new javax.swing.JButton();
        Partida = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        menuConectar = new javax.swing.JMenuItem();
        menuDesconectar = new javax.swing.JMenuItem();
        menuVerRaking = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
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
        status.setText(resourceMap.getString("status.text")); // NOI18N
        status.setName("status"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        nomeJogador.setText(resourceMap.getString("nomeJogador.text")); // NOI18N
        nomeJogador.setName("nomeJogador"); // NOI18N

        informarNomeJogador.setText(resourceMap.getString("informarNomeJogador.text")); // NOI18N
        informarNomeJogador.setName("informarNomeJogador"); // NOI18N
        informarNomeJogador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informarNomeJogadorActionPerformed(evt);
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
                .addGap(131, 131, 131)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status)
                    .addGroup(ConectandoLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(informarNomeJogador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(nomeJogador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(nomeServidor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
                .addGap(474, 474, 474))
        );
        ConectandoLayout.setVerticalGroup(
            ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectandoLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(status)
                .addGap(18, 18, 18)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(ConectandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(informarNomeJogador)
                .addContainerGap(293, Short.MAX_VALUE))
        );

        mainPanel.add(Conectando, "Conectando");

        Conectado.setName("Conectado"); // NOI18N

        status2.setFont(resourceMap.getFont("status2.font")); // NOI18N
        status2.setText(resourceMap.getString("status2.text")); // NOI18N
        status2.setName("status2"); // NOI18N

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
                .addGroup(ConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConectadoLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(status2))
                    .addGroup(ConectadoLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(buttonIniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        ConectadoLayout.setVerticalGroup(
            ConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConectadoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(status2)
                .addGap(31, 31, 31)
                .addComponent(buttonIniciarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        mainPanel.add(Conectado, "Conectado");

        Partida.setName("Partida"); // NOI18N
        Partida.setPreferredSize(new java.awt.Dimension(700, 600));

        javax.swing.GroupLayout PartidaLayout = new javax.swing.GroupLayout(Partida);
        Partida.setLayout(PartidaLayout);
        PartidaLayout.setHorizontalGroup(
            PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        PartidaLayout.setVerticalGroup(
            PartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        mainPanel.add(Partida, "Partida");

        menuBar.setName("menuBar"); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(600, 27));

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
        fileMenu.add(menuVerRaking);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(connect.ConnectApp.class).getContext().getActionMap(ConnectView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu5.setText(resourceMap.getString("iniciar.text")); // NOI18N
        jMenu5.setName("iniciar"); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenu5.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText(resourceMap.getString("reiniciar.text")); // NOI18N
        jMenuItem4.setName("reiniciar"); // NOI18N
        jMenu5.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText(resourceMap.getString("finalizar.text")); // NOI18N
        jMenuItem5.setName("finalizar"); // NOI18N
        jMenu5.add(jMenuItem5);

        menuBar.add(jMenu5);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        inserir.setSelected(true);
        inserir.setText(resourceMap.getString("inserir.text")); // NOI18N
        inserir.setName("inserir"); // NOI18N
        inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirActionPerformed(evt);
            }
        });
        jMenu1.add(inserir);

        remover.setSelected(false);
        remover.setText(resourceMap.getString("remover.text")); // NOI18N
        remover.setName("remover"); // NOI18N
        remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerActionPerformed(evt);
            }
        });
        jMenu1.add(remover);

        menuBar.add(jMenu1);

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
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 414, Short.MAX_VALUE)
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
        estabelecerConexao();
    }//GEN-LAST:event_menuConectarActionPerformed

    private void menuDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDesconectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuDesconectarActionPerformed

    private void informarNomeJogadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informarNomeJogadorActionPerformed
        status.setText("Status: Conectando...");   
        
        if(nomeJogador.getText().isEmpty()){ 
            status.setText("Status: Nome em branco!");   
        }else if(nomeServidor.getText().isEmpty()){
            status.setText("Status: Servidor em branco");   
        }else{            
            gui.setServidor(nomeServidor.getText());
            gui.setNome(nomeJogador.getText());      
            estabelecerConexao();
        }        

    }//GEN-LAST:event_informarNomeJogadorActionPerformed

    private void buttonIniciarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIniciarPartidaActionPerformed
        status2.setText("Aguardando Oponente");
        gui.iniciarJogo();
        //mainPanel.addMouseListener(gui);        
        //ConnectApp.getApplication().getMainFrame().addMouseListener(gui);       
        
    }//GEN-LAST:event_buttonIniciarPartidaActionPerformed

    private void inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirActionPerformed
        gui.getConnect().setTipoJogadaInsercao(true);
        remover.setSelected(false);
        
    }//GEN-LAST:event_inserirActionPerformed

    private void removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerActionPerformed
          gui.getConnect().setTipoJogadaInsercao(false);
          inserir.setSelected(false);
    }//GEN-LAST:event_removerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Conectado;
    private javax.swing.JPanel Conectando;
    private javax.swing.JPanel Partida;
    private javax.swing.JButton buttonIniciarPartida;
    private javax.swing.JToggleButton informarNomeJogador;
    private javax.swing.JRadioButtonMenuItem inserir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConectar;
    private javax.swing.JMenuItem menuDesconectar;
    private javax.swing.JMenuItem menuVerRaking;
    private javax.swing.JTextField nomeJogador;
    private javax.swing.JTextField nomeServidor;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButtonMenuItem remover;
    private javax.swing.JLabel status;
    private javax.swing.JLabel status2;
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

    private void estabelecerConexao() {
//        ((java.awt.CardLayout)mainPanel.getLayout()).show(mainPanel,"Conectando");
        
        if(gui.estabelecerConexao())
        {
            status.setText("Status: Conectado ao Servidor!");
            ((java.awt.CardLayout)mainPanel.getLayout()).show(mainPanel,"Conectado");
        }else{
            status.setText("Status: Erro na conexão!");
        }
        
    }
}
