package pedido;

import java.sql.*;
import conexion.Database;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Paso2 extends javax.swing.JFrame {
    
   
    private String fecha;
    private String hora;
    private int cantidadArticulos = 0;
    private double costo;
    private int iva = 1;
    private int descuento = 0;
    private double costoTotal;
    public int codCliente;
    private int codPedido;
    // public Date date;
    // arreglo que almacena temporalmente los datos del producto
    Object[] datosProducto = new Object[3]; // 0 1 2
    // lista que guarda los articulos en ese momento
    List<String> codigosArticulos = new ArrayList<>();
    
    
    public Paso2() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        llenaArticulos();
    }
    
    public void setCodPedido(int codigo){
        this.codPedido = codigo;
    }

    public int getCodPedido(){
        return this.codPedido;
    }
    
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public String getFecha(){
        return this.fecha;
    }
    
    public String getHora(){
        return this.hora;
    }
    
    
    public int getCantidadArticulos(){
        return this.cantidadArticulos;
    }
    
     public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
    
    public double getCosto(){
        return this.costo;
    }
    
    public int getIva(){
        return this.iva;
    }
    
    public double getCostoTotal(){
        return this.costoTotal;
    }
    // funcion que setea la variable codCliente con el ultimo codCliente
    public void setMaxCodCliente(){
        Database db = new Database();
        ResultSet rs;
        String query = "select max(codCliente) as ultimoCliente from Cliente";
        try {
            rs = db.consulta(query);
            while(rs.next()){
                setCodCliente(rs.getInt("ultimoCliente"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al capturar el codCliente");
        }
    }    
    
    
    public void llenaArticulos(){
        String query = "SELECT nombre FROM Articulo";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                cb_articulos.addItem(rs.getString("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar los productos");
        }
    }
    
    // funcion que captura la informacion del producto agregado
    public void capturaProducto(String nombreProducto){
        String query = "select codArticulo, nombre, precio from Articulo WHERE nombre = ?";
        Database db = new Database();
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = db.con.prepareStatement(query);            
            ps.setString(1, nombreProducto);            
            rs = ps.executeQuery();
            
            while(rs.next()){
               datosProducto[0] = rs.getString("codArticulo");
               datosProducto[1] = rs.getString("nombre");
               datosProducto[2] = rs.getString("precio");
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al capturar la informacion : Error de consulta");
        }
    }
    
    // funcion que agrega una fila a la tabla
    public void agregaFila(Object[] fila){
        DefaultTableModel tabla = (DefaultTableModel)tabla_pedido.getModel();
        tabla.addRow(fila);
    }
    
    
    // funcion que añade un codigo a la lista
    public void addCodigo(String codigo){
        codigosArticulos.add(codigo);        
    }
    
    // funcion que captura los codigos
    public void capturaCodigos(){
        for(int i = 0; i<tabla_pedido.getRowCount(); i++){
            addCodigo(String.valueOf(tabla_pedido.getValueAt(i, 0)));            
        }
    }
    
    
    public void setCantidadArticulos(){
        this.cantidadArticulos = 0;
        for(int i = 0; i<tabla_pedido.getRowCount(); i++){            
            this.cantidadArticulos = this.cantidadArticulos + 1;
        }
    }
    
    
    
    // funcion que setea La fecha
    public void setFecha(){
        LocalDateTime objDate = LocalDateTime.now(); // 2021-08-09T08:14:53:123
        // yyyy-MM-dd
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fecha = String.valueOf(objDate.format(dtf));
    }
    
    public void setHora(){
        LocalDateTime objHour = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora = String.valueOf(objHour.format(dtf));
    }
    
    /*
    public void setCostos(){
        BigDecimal suma = BigDecimal.ZERO;
        for(int i = 0; i<tabla_pedido.getRowCount(); i++){
            BigDecimal costoActual = (BigDecimal)tabla_pedido.getValueAt(i, 2);
            suma.add(costoActual);            
        }
        this.costo = Double.parseDouble(suma.toString());
        setEtiquetas();
    }*/
    
    
    
    public void setCostos(){
        double suma = 0;
        DecimalFormat df = new DecimalFormat("#.00");
        for(int i = 0; i<tabla_pedido.getRowCount(); i++){
            suma = suma + Double.parseDouble(String.valueOf(tabla_pedido.getValueAt(i, 2)));
        }
        String sumaParcial = df.format(suma);
        this.costo = Double.parseDouble(sumaParcial);
        // colocando el costo total
        // costo + (costo * (iva / 100)) -> 0.15
        double iva = this.iva / 100;
        this.costoTotal = this.costo + iva*this.costo;       
        setEtiquetas();
        
        
    }
    
    
    public void setEtiquetas(){
        label_precio.setText(String.valueOf(getCosto()));
        label_iva.setText(String.valueOf(getIva()));  
        label_costoTotal.setText(String.valueOf(getCostoTotal()));
    }
    
    // funcion que retorna el ultimo idPedido
    public void setIdUltimoPedido(){ // O(n) -> 
        String query = "Select max(idPedido) as ultimo from Pedido";
        Database db = new Database();
        ResultSet rs;
        try {
            rs = db.consulta(query);
            while(rs.next()){
                setCodPedido(rs.getInt("ultimo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al capturar el codigo de pedido");
        }       
    }
    
    public void registraUnaRelacion(String codArticulo, int idPedido){
        CallableStatement cs;
        String query = "{CALL sp_NuevaRelacionPedidoArticulo(?, ?)}";
        Database db = new Database();
        try {
            cs = db.con.prepareCall(query);
            cs.setString(1, codArticulo);
            cs.setInt(2, idPedido);
            
            cs.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar los articulos en el pedido-articulo");
        }
    }
    
    public void registraRelaciones(){
        // captura el codigo de pedido el pedido inmediato anterior
        setIdUltimoPedido();
        int idPedido = getCodPedido();
        for(String codigo : this.codigosArticulos){
            registraUnaRelacion(codigo, idPedido);
        }        
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_nuevoPedido = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cb_articulos = new javax.swing.JComboBox<>();
        btn_agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pedido = new javax.swing.JTable();
        btn_confirmar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_precio = new javax.swing.JLabel();
        label_iva = new javax.swing.JLabel();
        label_costoTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(73, 133, 172));

        btn_nuevoPedido.setText("Registrar pedido");
        btn_nuevoPedido.setBorder(null);
        btn_nuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoPedidoActionPerformed(evt);
            }
        });

        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        tabla_pedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_pedido);
        if (tabla_pedido.getColumnModel().getColumnCount() > 0) {
            tabla_pedido.getColumnModel().getColumn(0).setResizable(false);
            tabla_pedido.getColumnModel().getColumn(1).setResizable(false);
            tabla_pedido.getColumnModel().getColumn(2).setResizable(false);
        }

        btn_confirmar.setText("Confirmar pedido");
        btn_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Precio:");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Iva:");

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Costo Total: ");

        label_precio.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        label_precio.setForeground(new java.awt.Color(255, 255, 255));

        label_iva.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        label_iva.setForeground(new java.awt.Color(255, 255, 255));

        label_costoTotal.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        label_costoTotal.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(55, 55, 55)
                .addComponent(label_costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_nuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_articulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_agregar))
                        .addGap(184, 223, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(label_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(label_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(btn_nuevoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(label_costoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Paso1 p1 = new Paso1();
        this.dispose();
        p1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_nuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoPedidoActionPerformed
        Database db = new Database();
        CallableStatement cs;
        String query = "{CALL sp_NuevoPedido(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        try {
            cs = db.con.prepareCall(query);
            cs.setString(1, getFecha());
            cs.setString(2, getHora());
            cs.setInt(3, getCantidadArticulos());
            cs.setDouble(4, getCosto());
            cs.setInt(5, getIva());
            cs.setInt(6, getDescuento());
            cs.setDouble(7, getCostoTotal());
            cs.setInt(8, getCodCliente());
            
            cs.execute(); // transacciones 
                        
            registraRelaciones(); // COMMIT -> EXITOSA
                                  // ROLLBACK -> ERROR            
            JOptionPane.showMessageDialog(null, "Pedido registrado");
            
            Paso1 p1 = new Paso1();
            this.dispose();
            p1.setVisible(true);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar pedido");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_nuevoPedidoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // boton que agrega la informacion del articulo
        capturaProducto(String.valueOf(cb_articulos.getSelectedItem()));       
        agregaFila(this.datosProducto);                
       
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmarActionPerformed
        capturaCodigos();
        setCantidadArticulos();
        setCostos();
        setFecha();
        setHora();
        System.out.println(getCantidadArticulos());
        System.out.println(getCosto());
        System.out.println(getIva());
        System.out.println(getCostoTotal());
        System.out.println(getFecha());
        System.out.println(getHora());
        System.out.println(getDescuento());        
    }//GEN-LAST:event_btn_confirmarActionPerformed
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
//            java.util.logging.Logger.getLogger(Paso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Paso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Paso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Paso2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Paso2().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_confirmar;
    private javax.swing.JButton btn_nuevoPedido;
    private javax.swing.JComboBox<String> cb_articulos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_costoTotal;
    private javax.swing.JLabel label_iva;
    private javax.swing.JLabel label_precio;
    private javax.swing.JTable tabla_pedido;
    // End of variables declaration//GEN-END:variables
}
