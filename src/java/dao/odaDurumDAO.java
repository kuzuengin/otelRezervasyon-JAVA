/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OdaDurum;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class odaDurumDAO extends DAO {
public odaDurumDAO() {}
    private static final String SELECT_ALL = "SELECT * FROM odadurum ORDER BY Id;"; 
    private static final String SELECT_ID = "SELECT * FROM odadurum WHERE Id =?;";  
    private static final String SELECT_KONTROL= "SELECT * FROM odadurum WHERE aciklama =?;";  
    private static final String UPDATE_USERS = "UPDATE odadurum set aciklama = ? WHERE Id = ?;";

    
    public List<OdaDurum> odaDurumListe() {
            
        List<OdaDurum> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String aciklama = rs.getString("aciklama");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new OdaDurum(Id,aciklama,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }    
    public boolean aciklamaKontrol(String aciklama) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONTROL);) {
            preparedStatement.setString(1, aciklama);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    }  
    
public List<OdaDurum> selectDurum(int ID) {
         List<OdaDurum> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);         
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String aciklama = rs.getString("aciklama");
                list.add(new OdaDurum(Id,aciklama));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }
    public void odaDurumDuzelt(OdaDurum odaDurum) throws SQLException {
        System.out.println(UPDATE_USERS);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS)) {
            preparedStatement.setString(1, odaDurum.getaciklama());
            preparedStatement.setInt(2, odaDurum.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
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
