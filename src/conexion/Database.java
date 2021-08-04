package conexion;

import java.sql.*;

public class Database {
    public String nameBD = "Tienda";
    public String user = "root";
    public String password = "12345";
    public String url = "jdbc:mysql://localhost:3306/" + nameBD; // versiones 5.7 o superior
    public String driver = "com.mysql.cj.jdbc.Driver"; // versiones 5.7 o superior com.mysql.jdbc.Driver
    public Connection con = null;
    
    // constructor que hace la conexion
    public Database(){
        try {
            // cargar driver
            Class.forName(driver);
            
            // establece la conexion
            this.con = DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();            
        }
    }
    /*
    public static void main(String[] args) {
        Database db = new Database();
        if(db.con != null){
            System.out.println("Conexion exitosa");
        }else{
            System.err.println("Error al conectar");
        }
    }
    */
    
    // funcion que realiza una consulta y retorna los datos en formato result set
    public ResultSet consulta(String query){       
        PreparedStatement ps;
        ResultSet rs;
        try{
            // preparar la consulta 
            ps = this.con.prepareStatement(query);
            
            // ejecutar y guardar
            rs = ps.executeQuery();
            
            // retorna datos
            return rs;
        }catch(SQLException error){
            error.printStackTrace();
        }
        return null;
    }
}
