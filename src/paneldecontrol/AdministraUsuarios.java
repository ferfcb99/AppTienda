package paneldecontrol;

import java.sql.*;
import conexion.Database;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdministraUsuarios extends javax.swing.JFrame {

    private String usuarioParaEliminar = null;
    private String claveParaEliminar = null;
    private String usuarioNuevo = null;
    private String claveNueva = null;
    private String rol = null;

    public String getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(String usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public AdministraUsuarios() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        llenarTabla();
        grupoDeRadios();
    }

    // creando getters
    public String getUsuarioParaEliminar() {
        return this.usuarioParaEliminar;
    }

    public String getClaveParaEliminar() {
        return this.claveParaEliminar;
    }

    // creando setters
    public void setUsuarioParaEliminar(String usuario) {
        this.usuarioParaEliminar = usuario;
    }

    public void setClaveParaEliminar(String clave) {
        this.claveParaEliminar = clave;
    }

    // funcion que verifica si se selecciono un trabajador o administrador para eliminar
    public boolean seleccionoUsuario(String usuario, String clave) {
        return (usuario != null) && (clave != null);
    }

    // function o metodo que llena la tabla de usuarios
    public void llenarTabla() {
        DefaultTableModel tabla = (DefaultTableModel) table_usuarios.getModel();
        tabla.setRowCount(0);
        String query = "select usuario, clave, tipoDeUsuario from Acceso";
        ResultSet rs;
        Database db = new Database();
        ResultSetMetaData rsmd;
        try {
            rs = db.consulta(query);
            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount(); // obtiene las columnas
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                tabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla, intente reingresar a esta ventana");
        }
    }

    // funcion o metodo que ancla los dos radio button
    public void grupoDeRadios() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rb_admin);
        grupo.add(rb_trabajador);
    }

    // funcion que captura los datos de registro
    public void capturaDatosParaRegistro() {
        setUsuarioNuevo(String.valueOf(jtf_usuario.getText()));
        setClaveNueva(String.valueOf(jtf_clave.getText()));
        if (rb_admin.isSelected()) {
            setRol("Administrador");
        } else if (rb_trabajador.isSelected()) {
            setRol("Trabajador");
        }
    }

    // funcion que valida formulario para el registro
    public boolean camposLlenos() {
        return !((getUsuarioNuevo().equals("") || getUsuarioNuevo() == null) || (getClaveNueva().equals("") || getClaveNueva() == null) || getRol() == null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_usuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jtf_usuario = new javax.swing.JTextField();
        jtf_clave = new javax.swing.JTextField();
        rb_admin = new javax.swing.JRadioButton();
        rb_trabajador = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(73, 133, 172));

        table_usuarios.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        table_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Clave", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_usuarios);
        if (table_usuarios.getColumnModel().getColumnCount() > 0) {
            table_usuarios.getColumnModel().getColumn(0).setResizable(false);
            table_usuarios.getColumnModel().getColumn(1).setResizable(false);
            table_usuarios.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 36)); // NOI18N
        jLabel1.setText("Eliminar trabajador");

        btn_eliminar.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(null);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtf_usuario.setBorder(null);

        jtf_clave.setBorder(null);

        rb_admin.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        rb_admin.setText("Administrador");
        rb_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_adminActionPerformed(evt);
            }
        });

        rb_trabajador.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        rb_trabajador.setText("Trabajador");
        rb_trabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_trabajadorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel2.setText("Usuario: ");

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel3.setText("Clave: ");

        jLabel4.setFont(new java.awt.Font("Gadugi", 0, 36)); // NOI18N
        jLabel4.setText("Agregar Trabajador");

        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(291, 291, 291))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(193, 193, 193)
                                .addComponent(rb_admin)
                                .addGap(18, 18, 18)
                                .addComponent(rb_trabajador)
                                .addGap(53, 53, 53))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_admin)
                    .addComponent(rb_trabajador)
                    .addComponent(jLabel2))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_usuariosMouseClicked
        // evento a cuando se da click a algun elemento de la tabla
        int fila = table_usuarios.getSelectedRow();
        String usuarioAux = String.valueOf(table_usuarios.getValueAt(fila, 0));
        String claveAux = (String.valueOf(table_usuarios.getValueAt(fila, 1)));

        // setear los valos
        setUsuarioParaEliminar(usuarioAux);
        setClaveParaEliminar(claveAux);
    }//GEN-LAST:event_table_usuariosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PanelDeControl pc = new PanelDeControl();
        pc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        String delete = "delete from Acceso where usuario = ? and clave = ?";
        PreparedStatement ps;
        Database db = new Database();

        try {
            ps = db.con.prepareStatement(delete);
            ps.setString(1, getUsuarioParaEliminar());
            ps.setString(2, getClaveParaEliminar());
            int registrosEliminados = ps.executeUpdate();
            llenarTabla();
            JOptionPane.showMessageDialog(null, registrosEliminados + " Fueron eliminados");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo llevar a cabo el borrado");
        }


    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void rb_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_adminActionPerformed

    private void rb_trabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_trabajadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_trabajadorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        capturaDatosParaRegistro();
        if(camposLlenos()){
            CallableStatement cs;
            Database db = new Database();
            String query = "call sp_NuevoUsuario(?,?,?)";
            try {
                cs = db.con.prepareCall(query);
                cs.setString(1, getUsuarioNuevo());
                cs.setString(2, getClaveNueva());
                cs.setString(3, getRol());
                cs.execute();
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                llenarTabla();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar: " + e + " o registro repetido");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe llenar y seleccionar todos los campos");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AdministraUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdministraUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdministraUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdministraUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdministraUsuarios().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jtf_clave;
    private javax.swing.JTextField jtf_usuario;
    private javax.swing.JRadioButton rb_admin;
    private javax.swing.JRadioButton rb_trabajador;
    private javax.swing.JTable table_usuarios;
    // End of variables declaration//GEN-END:variables
}
