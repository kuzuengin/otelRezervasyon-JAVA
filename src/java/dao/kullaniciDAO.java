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

/**
 *
 * @author engin
 */
public class kullaniciDAO extends DAO {
public kullaniciDAO() {}
    private static final String SELECT_USER_SIFRE = "SELECT * FROM kullanici WHERE kullanici =? AND sifre=?;";  
    private static final String SELECT_USER_KONTROL = "SELECT * FROM kullanici WHERE kullanici =? AND Id<>?;"; 
    private static final String SELECT_USER_KONTROL_YENI = "SELECT * FROM kullanici WHERE kullanici =?;";     
    private static final String SELECT_USER_ID = "SELECT * FROM kullanici WHERE Id =?;";  
    private static final String SELECT_USER = "SELECT a.*,b.aciklama AS durumAciklama ,c.yetki AS yetkiAciklama " +
"FROM kullanici a LEFT JOIN parametre b ON kod1=1 AND a.durum=b.kod2 " +
"LEFT JOIN yetki c ON c.Id=a.yetki ORDER BY Id;";      
    private static final String INSERT_SQL = "INSERT INTO kullanici (adSoyad, kullanici, sifre, yetki, durum) VALUES(?, ?, ?, ?, ?);";
    private static final String UPDATE_USERS = "UPDATE kullanici set adSoyad = ?,kullanici= ?, yetki =?, durum =? where Id = ?;";
    private static final String DELETE_SQL = "DELETE FROM kullanici WHERE Id = ?;";


    public Kullanici selectKullanici(String username,String pass) {
        Kullanici user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SIFRE);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pass);            
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String adSoyad = rs.getString("adSoyad");
                String kullanici = rs.getString("kullanici");
                String sifre = rs.getString("sifre");
                int yetki = rs.getInt("yetki");
                int durum = rs.getInt("durum");
                user = new Kullanici(adSoyad,yetki);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    public boolean kullaniciKontrol(Kullanici kullanici) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_KONTROL);) {
            preparedStatement.setString(1, kullanici.getkullanici());
            preparedStatement.setInt(1, kullanici.getId());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    } 
    public boolean kullaniciKontrolYeni(Kullanici kullanici) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_KONTROL_YENI);) {
            preparedStatement.setString(1, kullanici.getkullanici());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    }     
    public List<Kullanici> kullaniciID(int ID) {
        List<Kullanici> users = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String adSoyad = rs.getString("adSoyad");
                String kullanici = rs.getString("kullanici");
                int yetki = rs.getInt("yetki");
                int durum = rs.getInt("durum");
                users.add(new Kullanici(Id,adSoyad,kullanici,yetki,durum));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    
    public List<Kullanici> kullaniciListe() {
            
        List<Kullanici> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String adSoyad = rs.getString("adSoyad");
                String kullanici = rs.getString("kullanici");
                int yetki = rs.getInt("yetki");
                String yetkiAciklama = rs.getString("yetkiAciklama");                
                int durum = rs.getInt("durum");
                String durumAciklama = rs.getString("durumAciklama");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                users.add(new Kullanici(Id,adSoyad,kullanici,yetki,yetkiAciklama,durum,durumAciklama,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public void kullaniciEkle(Kullanici kullanici) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, kullanici.getadSoyad());
            preparedStatement.setString(2, kullanici.getkullanici());
            preparedStatement.setString(3, kullanici.getsifre());
            preparedStatement.setInt(4, kullanici.getyetki());
            preparedStatement.setInt(5, kullanici.getdurum());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }  
        
    public void kullaniciDuzelt(Kullanici kullanici) throws SQLException {
        System.out.println(UPDATE_USERS);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS)) {
            preparedStatement.setString(1, kullanici.getadSoyad());
            preparedStatement.setString(2, kullanici.getkullanici());
            preparedStatement.setInt(3, kullanici.getyetki());
            preparedStatement.setInt(4, kullanici.getdurum());
            preparedStatement.setInt(5, kullanici.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }  
    public boolean kullaniciSil(int ID) throws SQLException {
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
