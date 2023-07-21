/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.de.memoria;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {
    ArrayList <JLabel> memorias = new ArrayList<JLabel>();
    ArrayList <Proceso> procesos = new ArrayList<Proceso>();
    DefaultListModel modelo = new DefaultListModel();
    int memoriatotal = 1324;
    
    
    Thread hilo = new Thread(){
        
        @Override
        public void run(){
            for(int i = 0; i < 300; i++){
                try {
                    reloj.setText(Integer.toString(Integer.parseInt(reloj.getText())-1));
                    sleep(1000);
                    crearProceso();
                    llenarCuadritos();
                    reducirTiempo();
                    quitarProceso();
                    mostrarResultados();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(this);
        modelo.removeAllElements();
        this.listaProcesos.setModel(modelo);
        
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){
                JLabel cuadro = new JLabel();
                cuadro.setBackground(Color.white);
                cuadro.setOpaque(true);
                cuadro.setBounds(10 + (j*16), 10 + (i*16), 15, 15);
                this.memoriapincipal.add(cuadro);
                memorias.add(cuadro);
            }
        }
        
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 10; j++){
                JLabel cuadro = new JLabel();
                cuadro.setBackground(Color.white);
                cuadro.setOpaque(true);
                cuadro.setBounds(10 + (j*16), 10 + (i*16), 15, 15);
                this.memoriasecundaria.add(cuadro);
                memorias.add(cuadro);
            }
        }
        hilo.start();
    }
    
    private int aleatorio(int inf, int sup) {
        int alea = 0;
        do {
            alea = (int) (Math.random() * 9999);
        } while (alea < inf || alea > sup);

        return alea;
    }
    
    public void crearProceso(){
        if(aleatorio(0,10) < 6){
            Proceso proceso = new Proceso();
            proceso.verificarId(procesos);
            this.procesos.add(proceso);
            this.modelo.addElement(proceso.toString());
        } 
    }
    
    public void llenarCuadritos(){
        for(Proceso proceso : this.procesos){
            if(proceso.getEstado().equals("Listo")){
                if(proceso.getTamano() <= this.memoriatotal){
                    int i = 0;
                    for(JLabel m : memorias){
                        if(m.getBackground() == Color.white){
                            m.setBackground(proceso.getColor());
                            m.setText("" + proceso.getTiempo());
                            i++;
                            this.memoriatotal--;
                            proceso.setEstado("Corriendo");
                            if(i >= proceso.getTamano()){
                                break;
                            }
                        }
                    }
                    this.modelo.removeAllElements();
                    for(Proceso p : this.procesos){
                        this.modelo.addElement(p.toString());
                    }
                }
            }
        }
    }
    
    public void reducirTiempo(){
        for(JLabel m : memorias){
            if(!m.getText().equals("")){
                m.setText("" + (Integer.parseInt(m.getText()) - 1));
            }
            for(Proceso p : procesos){
                if(m.getBackground() == p.getColor() && p.getEstado().equals("Bloqueado")){
                    m.setText("" + (Integer.parseInt(m.getText()) + 1));
                }
                if(m.getBackground() == p.getColor() && p.getEstado().equals("Terminado")){
                    m.setText("1");
                }
            }
        }
    }
    
    public void quitarProceso(){
        Color c = null;
        boolean ban = false;
        for(JLabel m : memorias){
            if(m.getText().equals("0")){
                c = m.getBackground();
                m.setBackground(Color.white);
                this.memoriatotal++;
                ban = true;
                m.setText("");
            }
        }
        if(ban){
            for(Proceso p : procesos){
                if(p.getColor() == c){
                    p.setEstado("Terminado");
                }
            }
        }
    }
    
    public void mostrarResultados(){
        int corriendo = 0;
        int listo = 0;
        int terminado = 0;
        int bloqueado = 0;
        if(Integer.parseInt(reloj.getText()) == 0){
            for(Proceso p : procesos){
                if(p.getEstado().equals("Corriendo")){
                    corriendo++;
                }
                if(p.getEstado().equals("Listo")){
                    listo++;
                }
                if(p.getEstado().equals("Terminado")){
                    terminado++;
                }
                if(p.getEstado().equals("Bloqueado")){
                    bloqueado++;
                }
            }
            JOptionPane.showMessageDialog(rootPane, "RESULTADOS:\n\nEstados corriendo: "
                    + corriendo + "\nEstados listos: " + listo + "\nEstados terminados: "
                    + terminado + "\nEstados bloqueados: " + bloqueado);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        memoriapincipal = new javax.swing.JPanel();
        memoriasecundaria = new javax.swing.JPanel();
        reloj = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaProcesos = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        memoriapincipal.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout memoriapincipalLayout = new javax.swing.GroupLayout(memoriapincipal);
        memoriapincipal.setLayout(memoriapincipalLayout);
        memoriapincipalLayout.setHorizontalGroup(
            memoriapincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        memoriapincipalLayout.setVerticalGroup(
            memoriapincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        memoriasecundaria.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout memoriasecundariaLayout = new javax.swing.GroupLayout(memoriasecundaria);
        memoriasecundaria.setLayout(memoriasecundariaLayout);
        memoriasecundariaLayout.setHorizontalGroup(
            memoriasecundariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        memoriasecundariaLayout.setVerticalGroup(
            memoriasecundariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        reloj.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        reloj.setText("300");

        jScrollPane1.setViewportView(listaProcesos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Memoria Principal");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Memoria Secundaria");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Lista de Procesos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(memoriapincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(memoriasecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(98, 98, 98)))
                .addComponent(reloj)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memoriapincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memoriasecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(reloj))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaProcesos;
    private javax.swing.JPanel memoriapincipal;
    private javax.swing.JPanel memoriasecundaria;
    private javax.swing.JLabel reloj;
    // End of variables declaration//GEN-END:variables
}
