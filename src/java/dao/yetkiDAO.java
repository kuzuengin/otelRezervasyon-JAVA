/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Kullanici;
import model.Yetki;

/**
 *
 * @author engin
 */
public class yetkiDAO extends DAO{
public yetkiDAO() {}
    private static final String SELECT_ALL_YETKI = "SELECT * FROM yetki ORDER BY Id";      
 
public List<Yetki> liste() {
        List<Yetki> yetkiListesi = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_YETKI);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int Id = 0;
            while (rs.next()) {
                Id++;
                String yetki = rs.getString("yetki");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                yetkiListesi.add(new Yetki(Id,yetki,Duzelt));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return yetkiListesi;
    }

 private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
