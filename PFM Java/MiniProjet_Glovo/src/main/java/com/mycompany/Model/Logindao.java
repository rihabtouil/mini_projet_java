/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;
import com.mycompany.Controller.Admin;
import java.sql.* ;
/**
 *
 * @author Rihab
 */
public class Logindao {
    Statement st ;
   public boolean isLogin(Admin a) throws SQLException
    {
        st =DBcnx.getConnection().createStatement();
        ResultSet res = st.executeQuery("select * from admins where user = '"+a.getUsername()+
                "' and password = '"+a.getPassword()+"'");
        if(res.next())
            return true ;
        return false ;
    }
}
