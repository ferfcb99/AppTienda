
package paneldecontrol;

import conexion.Database;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AdministraClientes extends javax.swing.JFrame {
    
    private int idCliente;
    private String nombre;
    private String apellido;
    private int credito;

    public AdministraClientes() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        llenarTabla();
    }
    
    // setters
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public void setCredito(int credito){
        this.credito = credito;
    }
            
    
    // getters
    public int getIdCliente(){
        return this.idCliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public int getCredito() {
        return this.credito;
    }
    
    
    // funcion que llena la tabla apenas inicia la app en esta pantalla
    public void llenarTabla(){        
        DefaultTableModel tabla = (DefaultTableModel)tabla_clientes.getModel();
        tabla.setRowCount(0);
        Database db = new Database();
        ResultSet rs;
        ResultSetMetaData rsmd;
        String query = "select codCliente, nombre, apellido, credito from Cliente";
        try {
            rs = db.consulta(query);
            rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int i=0; i<fila.length; i++){
                    fila[i] = rs.getObject(i+1);
                }
                tabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla. Tipo de error: SQL");
        }
    }
    
    
    // funcion que almacena los datos de un cliente dado un codCliente
    public void setInformacionCliente(int codCliente){
        String query = "select codCliente, nombre, apellido, credito from Cliente where codCliente = ?";
        Database db = new Database();
        try {
            PreparedStatement ps = db.con.prepareStatement(query);  
            ResultSet rs;
            ps.setInt(1, codCliente);
            rs = ps.executeQuery();
            while(rs.next()){
                setIdCliente(rs.getInt("codCliente"));
                setNombre(rs.getString("nombre"));
                setApellido(rs.getString("apellido"));
                setCredito(rs.getInt("credito"));
            }
            llenaFormulario();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al traspasar la informacion");
        }
    }
    
    // funcion que guarda los datos en sus correspondientes campos
    public void llenaFormulario(){
        jlabel_codCliente.setText(String.valueOf(getIdCliente()));
        jtf_nombre.setText(getNombre());
        jtf_apellido.setText(getApellido());
        jtf_credito.setText(String.valueOf(getCredito()));
    }
    
    // funcion que limpia el formulario
    public void limpiaFormulario(){
        jlabel_codCliente.setText("");
        jtf_nombre.setText("");
        jtf_apellido.setText("");
        jtf_credito.setText("");
    }
    
    // funcion que dice mediante true o false si una cantidad es menor o igual a 200
    public boolean cumpleCredito(int cantidad, int limite){
        return cantidad <= limite;
    }
    
    // funcion que diga si un caracter es un numero
    public boolean esNumero(String numeroTexto){
        int numero;
        try {
            numero = Integer.parseInt(numeroTexto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // funcion que valida que el credito sea numerico entero y que ademas sea menor o igual a 200
    public boolean creditoValido(){
        if(esNumero(jtf_credito.getText())){
            if(cumpleCredito(Integer.parseInt(jtf_credito.getText()), 200)){   
                setCredito(Integer.parseInt(jtf_credito.getText()));
                return true;
            }
        }
        return false;
    }
    
    // funcion que verifica y guarda si los campos estan llenos
    public boolean camposLlenos(String nombre, String apellido){
        if(nombre.length() > 0 && apellido.length() > 0){
            setNombre(nombre);
            setApellido(apellido);
            return true;
        }
        return false;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jlabel_codCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jtf_apellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtf_credito = new javax.swing.JTextField();
        btn_eliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(73, 133, 172));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agrega, modifica y elimina Clientes");

        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Credito"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_clientes);
        if (tabla_clientes.getColumnModel().getColumnCount() > 0) {
            tabla_clientes.getColumnModel().getColumn(0).setResizable(false);
            tabla_clientes.getColumnModel().getColumn(1).setResizable(false);
            tabla_clientes.getColumnModel().getColumn(2).setResizable(false);
            tabla_clientes.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Codigo de Cliente");

        jlabel_codCliente.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jlabel_codCliente.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre: ");

        jtf_nombre.setBorder(null);

        jtf_apellido.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido:");

        jLabel6.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Credito:");

        jtf_credito.setToolTipText("");
        jtf_credito.setBorder(null);

        btn_eliminar.setText("Eliminar");
        btn_eliminar.setBorder(null);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jButton1.setText("Registrar");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/people_100px.png"))); // NOI18N

        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(38, 38, 38)
                                    .addComponent(jlabel_codCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtf_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jlabel_codCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtf_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtf_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        // codigo para cuando el admin da click lleve los datos del cliente a el formulario
        int fila = tabla_clientes.getSelectedRow();
        int codCliente = Integer.parseInt(tabla_clientes.getValueAt(fila, 0).toString());
        setIdCliente(codCliente);
        setInformacionCliente(getIdCliente());
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        String query = "DELETE FROM Cliente WHERE codCliente = ?";
        PreparedStatement ps;
        Database db = new Database();
        try {
            ps = db.con.prepareStatement(query);
            ps.setInt(1, getIdCliente());            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            llenarTabla();
            limpiaFormulario();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se logro eliminar");
        }        
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(camposLlenos(jtf_nombre.getText(), jtf_apellido.getText())){
            if(creditoValido()){
                CallableStatement cs;
                String query = "{CALL sp_NuevoCliente(?,?,?)}";
                Database db = new Database();
                try {
                    cs = db.con.prepareCall(query);
                    cs.setString(1, getNombre());
                    cs.setString(2, getApellido());
                    cs.setInt(3, getCredito());
                    cs.execute();
                    JOptionPane.showMessageDialog(null, "Cliente Registrado");
                    llenarTabla();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El credito debe ser un entero menor o igual a 200");    
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe escribir su nombre y apellido");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        PanelDeControl pc = new PanelDeControl();
        this.dispose();
        pc.setVisible(true);
    }//GEN-LAST:event_btn_regresarActionPerformed

    /**
     * @param args the command line arguments
     */
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
//            java.util.logging.Logger.getLogger(AdministraClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdministraClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdministraClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdministraClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdministraClientes().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel_codCliente;
    private javax.swing.JTextField jtf_apellido;
    private javax.swing.JTextField jtf_credito;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTable tabla_clientes;
    // End of variables declaration//GEN-END:variables
}
