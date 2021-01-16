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
import model.OdaTipi;

/**
 *
 * @author engin
 */
public class odaTipiDAO extends DAO {
public odaTipiDAO() {}
    private static final String SELECT_ALL = "SELECT * FROM odatipi ORDER BY Id;"; 
    private static final String SELECT_ID = "SELECT * FROM odatipi WHERE Id =?;";  
    private static final String SELECT_KONTROL= "SELECT * FROM odatipi WHERE adi =?;";  
    private static final String UPDATE_SQL = "UPDATE odatipi set adi=?, fiyat=?, yetiskin=?, cocuk=? WHERE Id = ?;";
    private static final String INSERT_SQL = "INSERT INTO odatipi (Id,adi,fiyat,yetiskin,cocuk) VALUES(0,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM odatipi WHERE Id = ?;";    
    public List<OdaTipi> odaTipiListe() {
            
        List<OdaTipi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String adi = rs.getString("adi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new OdaTipi(Id,adi,fiyat,yetiskin,cocuk,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     

public boolean odaTipiKontrol(String adi) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONTROL);) {
            preparedStatement.setString(1, adi);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    }  
public List<OdaTipi> selectOdaTipi(int ID) {
         List<OdaTipi> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);         
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String adi = rs.getString("adi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");
                list.add(new OdaTipi(Id,adi,fiyat,yetiskin,cocuk));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }        

    public void OdaTipiEkle(OdaTipi odatipi) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, odatipi.getadi());
            preparedStatement.setString(2, odatipi.getfiyat());
            preparedStatement.setInt(3, odatipi.getyetiskin());
            preparedStatement.setInt(4, odatipi.getcocuk());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    public void OdaTipiDuzelt(OdaTipi odatipi) throws SQLException {
        System.out.println(UPDATE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, odatipi.getadi());
            preparedStatement.setString(2, odatipi.getfiyat());
            preparedStatement.setInt(3, odatipi.getyetiskin());
            preparedStatement.setInt(4, odatipi.getcocuk());
            preparedStatement.setInt(5, odatipi.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }    
    public boolean OdaTipiSil(int ID) throws SQLException {
        System.out.println(DELETE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            printSQLException(e);
            return false;
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
