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
import model.MusteriListesi;

/**
 *
 * @author engin
 */
public class musteriListesiDAO extends DAO {
public musteriListesiDAO() {}
    private static final String SELECT_ALL = "SELECT * FROM musteri ORDER BY ad,soyad"; 
    private static final String SELECT_ID = "SELECT * FROM musteri WHERE Id =?;";  
    private static final String SELECT_KONTROL= "SELECT * FROM musteri WHERE telefon =?;";  
    private static final String UPDATE_SQL = "UPDATE musteri set ad=?, soyad=?, telefon=?, eposta=? WHERE Id = ?;";
    private static final String INSERT_SQL = "INSERT INTO musteri (Id,ad,soyad,telefon,eposta) VALUES(0,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM musteri WHERE Id = ?;";    

    public List<MusteriListesi> musteriListe() {
        List<MusteriListesi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String telefon = rs.getString("telefon");
                String eposta = rs.getString("eposta");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new MusteriListesi(Id,ad,soyad,telefon,eposta,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     
public List<MusteriListesi> selectMusterilistesi(int ID) {
         List<MusteriListesi> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);         
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String telefon = rs.getString("telefon");
                String eposta = rs.getString("eposta");
                list.add(new MusteriListesi(Id,ad,soyad,telefon,eposta));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    } 
public int musteriKontrol(String tel) {
        int ID = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONTROL);) {
            preparedStatement.setString(1, tel);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            ID = rs.getInt("Id");
        } catch (SQLException e) {
            printSQLException(e);
        }
    return ID;
    }  

    public void musteriEkle(MusteriListesi musteri) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, musteri.getad());
            preparedStatement.setString(2, musteri.getsoyad());
            preparedStatement.setString(3, musteri.gettelefon());
            preparedStatement.setString(4, musteri.geteposta());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    public void musteriDuzelt(MusteriListesi musteri) throws SQLException {
        System.out.println(UPDATE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, musteri.getad());
            preparedStatement.setString(2, musteri.getsoyad());
            preparedStatement.setString(3, musteri.gettelefon());
            preparedStatement.setString(4, musteri.geteposta());
            preparedStatement.setInt(5, musteri.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }    
    public boolean musteriSil(int ID) throws SQLException {
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
