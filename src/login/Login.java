package login;

import conexion.Database;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.sql.*;
import paneldecontrol.PanelDeControl;

public class Login extends javax.swing.JFrame {

    private String usuario = null;
    private String clave = null;
    private String tipoDeUsuario = null;

    public Login() {
        initComponents();
        this.setResizable(false); //  no crezca la app
        this.setLocationRelativeTo(null); // aparezca en el centro
        grupoRadios();
    }

    // set and get 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return this.clave;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getTipoDeUsuario() {
        return this.tipoDeUsuario;
    }

    // funcion que ancla los radio buttons
    public void grupoRadios() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rb_admin);
        grupo.add(rb_trabajador);
    }

    // funcion que guarda el tipo de usuario
    public void guardaTipoDeUsuario() {
        if (rb_admin.isSelected()) {
            setTipoDeUsuario("Administrador");
        } else if (rb_trabajador.isSelected()) {
            setTipoDeUsuario("Trabajador");
        }
    }

    // function que captura los datos del formulario
    public void guardaDatos() {
        setUsuario(String.valueOf(jtf_usuario.getText()));
        setClave(String.valueOf(jtf_clave.getText()));
        guardaTipoDeUsuario();
    }

    // function que dice si los campos estan llenos o tiene algo
    // retorna 1 si estan llenos
    // retorna 0 sino lo estan
    public int estanLlenos() {
        if (getTipoDeUsuario() != null && getUsuario() != null && getClave() != null) {
            return 1;
        }
        return 0;

    }

    // funcion que arroja cual campo esta vacio
    public void mensajesDeCamposVacios() {
        if((getUsuario().equals("") || getUsuario() == null) || (getClave().equals("") || getClave() == null)){
            JOptionPane.showMessageDialog(null, "Falta llenar usuario o clave");            
        }else if((getTipoDeUsuario() == null)){
            JOptionPane.showMessageDialog(null, "Debe seleccionar su ROL");            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jl_logo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtf_usuario = new javax.swing.JTextField();
        jtf_clave = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        rb_admin = new javax.swing.JRadioButton();
        rb_trabajador = new javax.swing.JRadioButton();
        btn_entrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(73, 133, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/shop_80px.png"))); // NOI18N
        jPanel1.add(jl_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 90, 99));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 600));

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 36)); // NOI18N
        jLabel1.setText("Iniciar Sesion");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("Usuario o correo:");

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña:");

        jtf_usuario.setBorder(null);

        jtf_clave.setBorder(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email_30px.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/password_40px.png"))); // NOI18N
        jLabel5.setToolTipText("");

        jSeparator1.setBackground(new java.awt.Color(73, 133, 172));
        jSeparator1.setAlignmentY(1.0F);

        jSeparator2.setBackground(new java.awt.Color(73, 133, 172));

        rb_admin.setText("Administrador");
        rb_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_adminActionPerformed(evt);
            }
        });

        rb_trabajador.setText("Trabajador");

        btn_entrar.setText("Entrar");
        btn_entrar.setBorder(null);
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(22, 22, 22)
                                    .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_entrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_admin)
                        .addGap(18, 18, 18)
                        .addComponent(rb_trabajador)))
                .addGap(55, 55, 55))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(82, 82, 82)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jtf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_trabajador)
                    .addComponent(rb_admin))
                .addGap(61, 61, 61)
                .addComponent(btn_entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 560, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_adminActionPerformed

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        guardaDatos();
        if(estanLlenos() == 1){
            Database db = new Database();
            String query = "select usuario, clave, tipoDeUsuario from Acceso";
            ResultSet rs;
            try {
                rs = db.consulta(query);
                while(rs.next()){
                    if(getUsuario().equals(rs.getString("usuario")) && getClave().equals(rs.getString("clave"))){
                        if("Administrador".equals(rs.getString("tipoDeUsuario"))){
                            // aqui va el codigo que lleva al panel de administrador
                            PanelDeControl pc = new PanelDeControl();
                            pc.setVisible(true);                            
                            this.dispose();
                            break;
                        }else{
                            // aqui va el codigo que lleva al panel de trabajador
                            JOptionPane.showMessageDialog(null, "El trabajador existe");
                        }
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al intentar acceder");
            }
        }else{
            mensajesDeCamposVacios();
        }
    }//GEN-LAST:event_btn_entrarActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_entrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jl_logo;
    private javax.swing.JTextField jtf_clave;
    private javax.swing.JTextField jtf_usuario;
    private javax.swing.JRadioButton rb_admin;
    private javax.swing.JRadioButton rb_trabajador;
    // End of variables declaration//GEN-END:variables
}
