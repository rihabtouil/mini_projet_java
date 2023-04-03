/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;
import java.sql.* ;
/**
 *
 * @author Rihab
 */
public class DBcnx {
        private static final String HOST ="127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "beispiel" ;
    private static final String USERNAME= "root" ;
    private static final String PASSWORD ="";
    
    private static Connection connection ;

    public DBcnx() {
    }
    
   public static Connection getConnection() throws SQLException{
      connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);

     return connection ;}
    
    public static void closeConnection()
    {
        if(connection!= null)
        {
            connection = null ;
        }
    }

}
 /* public static void setConnection(String url, String user, String password) throws SQLException {
    connection = DriverManager.getConnection(url, user, password);
}*/