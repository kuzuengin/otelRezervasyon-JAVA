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
import model.Parametre;

/**
 *
 * @author engin
 */
public class parametreDAO extends DAO{
public parametreDAO() {}
    private static final String SELECT_YETKI_KOD1 = "SELECT * FROM parametre WHERE kod1=? ORDER BY aciklama";      
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";    

 
public List<Parametre> liste(int kod) {
        List<Parametre> parametreListesi = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_YETKI_KOD1);) {
            preparedStatement.setInt(1, kod);
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int Id = 0;
            while (rs.next()) {
                Id++;
                int kod1 = rs.getInt("kod1");
                int kod2 = rs.getInt("kod2");
                String aciklama = rs.getString("aciklama");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                parametreListesi.add(new Parametre(Id,kod1,kod2,aciklama,Duzelt));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return parametreListesi;
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
