package com.rodri.cacproyectojava.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConnection {
    
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    
    public String servidor, baseDatos, usuario, clave, ejecutar;
    
    public Connection conectarse() throws ClassNotFoundException, SQLException{
       

        try{
            Class.forName("com.mysql.jdbc.Driver");
            servidor = "localhost:3307/proyectocac"; 
            usuario = "root";
            clave = "";
            cn = DriverManager.getConnection("jdbc:mysql://" + servidor +  "?autoReconnect=true&useSSL=false", usuario, clave);
            
            System.out.println("LA CONEXION FUE EXITOSA");
        
        }catch (ClassNotFoundException ex){
            System.out.println("ERROR EN LA CONEXION");
            Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cn;
        
    }
    
    
    //***********************************************************************************************
    
    public ResultSet consultaSQL(String busqueda){
        
        try {
            ps = conectarse().prepareStatement(busqueda);
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
        
    }
    
    
}
