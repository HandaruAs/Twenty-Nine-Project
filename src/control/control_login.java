/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author handa
 */
public class control_login extends koneksi{
    public control_login(){
        super.setKoneksi();
    }
    public ResultSet login(String username,String password) throws SQLException{
        String sql ="SELECT * FROM user where username = '"+username+"' AND password = '"+password+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
}
