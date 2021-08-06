

package paneldecontrol;

import java.sql.*;
import javax.swing.JOptionPane;
import conexion.Database;

public class AdministraArticulos extends javax.swing.JFrame {
    
    private String nombreArticulo;
    
    
    private float precio;
    private String codigo;
    private String nombre;

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }
    private int peso;
    private String codProveedor;

    /** Creates new form AdministraArticulos */
    public AdministraArticulos() {
        initComponents();
        llenaCheckArticulos();
        llenaCheckProveedores();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        //llenaCheckPrueba();
    }
    
    
    // getters
    public String getNombreArticulo(){
        return this.nombreArticulo;
    }
    
    
    // setters
    public void setNombreArticulo(String nombreArticulo){
        this.nombreArticulo = nombreArticulo;
    }

    
    // funcion que llena el check
    public void llenaCheckArticulos(){   
        cb_articulo.removeAllItems();
        String query = "select nombre from Articulo";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                cb_articulo.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar el combo de articulos");
        }
    }
    
    
    public void llenaCheckProveedores(){   
        cb_provedores.removeAllItems();
        String query = "select codProveedor from proveedor;";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                cb_provedores.addItem(rs.getString("codProveedor"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar el combo de articulos");
        }
    }
    
    // funcion que valida si una cadena es un numero decimal
    public boolean esDecimal(String textoNumero){
        float numero;
        try {
            numero = Float.parseFloat(textoNumero);
            setPrecio(numero);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // funcion que valida si el codigo de articulo para el registro es correcto
    public boolean codigoValido(){
        if(jtf_codigo.getText().length() <= 3){
            setCodigo(jtf_codigo.getText());
            return true;
        }
        return false;
    }
    
    // funcion que valida si el nombre esta lleno
    public int campoLleno(String cadena){
        if (cadena.length() > 0){
            return 1;
        }else{
            return 0;
        }
    }
    
    
    // funcion que captura el articulo que esta seleccionado
    public void capturaArticulo(){
        setNombreArticulo(String.valueOf(cb_articulo.getSelectedItem()));
    }

    // funcion que retorna el codArticulo de un articulo concreto
    public String getCodArticulo(){
        capturaArticulo();
        String query = "select codArticulo, nombre from Articulo";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                if(getNombreArticulo().equals(rs.getString("nombre"))){
                    return String.valueOf(rs.getString("codArticulo"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al capturar el codigo del articulo");
        }
        return null;
    }
    
    // funcion que guarda el peso del producto
    public void guardaPeso(){
        setPeso(js_peso.getValue());
    }
    
    
    // funcion que limpia el formulario
    public void limpiaFormulario(){
        jtf_codigo.setText("");
        jtf_nombre.setText("");
        jtf_precio.setText("");
    }
    
    
    
    // funcion termporal
    /*
      public void llenaCheckPrueba(){
        
        String query = "select codArticulo, nombre, precio from Articulo";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                cb_prueba.addItem(rs.getString("codArticulo").trim() + " - " + rs.getString("nombre").trim() + " - " + rs.getString("precio").trim());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar el combo de articulos");
        }
    }*/
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        js_peso = new javax.swing.JSlider();
        cb_provedores = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jtf_codigo = new javax.swing.JTextField();
        jtf_nombre = new javax.swing.JTextField();
        jtf_precio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cb_articulo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(73, 133, 172));

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 24)); // NOI18N
        jLabel1.setText("Inserta un Nuevo Articulo");

        jLabel3.setText("Codigo");

        jLabel4.setText("Nombre");

        jLabel5.setText("Precio");

        jLabel6.setText("Peso (Kg)");

        js_peso.setMajorTickSpacing(1);
        js_peso.setMaximum(5);
        js_peso.setMinimum(1);
        js_peso.setPaintLabels(true);
        js_peso.setPaintTicks(true);

        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jtf_codigo.setBorder(null);

        jLabel7.setText("Proveedor");

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(js_peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jtf_precio, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtf_nombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_provedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_provedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(js_peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 569));

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 24)); // NOI18N
        jLabel2.setText("Elimina un articulo");

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_articulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(127, 127, 127))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addGap(131, 131, 131)
                .addComponent(cb_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 450, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String query = "DELETE FROM Articulo WHERE codArticulo = ?";
        PreparedStatement ps;
        Database db = new Database();
        try {
            ps = db.con.prepareStatement(query);
            ps.setString(1, getCodArticulo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se elimino el registro de forma correcta");
            llenaCheckArticulos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(codigoValido()){
            if(campoLleno(jtf_nombre.getText()) == 1){
                setNombre(jtf_nombre.getText());
                if(esDecimal(jtf_precio.getText())){
                    // guarda el peso
                    guardaPeso();
                    // guarda el codigo proveedor
                    setCodProveedor(String.valueOf(cb_provedores.getSelectedItem()));
                    // objetos para la insercion
                    Database db = new Database();
                    CallableStatement cs;
                    String query = "{CALL sp_NuevoArticulo(?,?,?,?,?)}";
                    try {
                        cs = db.con.prepareCall(query);
                        cs.setString(1, getCodigo());
                        cs.setString(2, getNombre());
                        cs.setFloat(3, getPrecio());
                        cs.setInt(4, getPeso());
                        cs.setString(5, getCodProveedor());
                        
                        cs.execute();
                        JOptionPane.showMessageDialog(null, "Articulo registrado");
                        llenaCheckArticulos();
                        limpiaFormulario();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error al insertar el articulo");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "El precio debe insertarlo como un numero");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Debe insertar el nombre del articulo");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El codigo debe tener maximo 3 caracteres");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PanelDeControl pc = new PanelDeControl();
        this.dispose();
        pc.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
//
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
//            java.util.logging.Logger.getLogger(AdministraArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AdministraArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AdministraArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AdministraArticulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AdministraArticulos().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_articulo;
    private javax.swing.JComboBox<String> cb_provedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider js_peso;
    private javax.swing.JTextField jtf_codigo;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_precio;
    // End of variables declaration//GEN-END:variables

}
