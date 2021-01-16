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
import model.OdaListesi;

/**
 *
 * @author engin
 */
public class odaListesiDAO extends DAO {
public odaListesiDAO() {}
    private static final String SELECT_ALL = "SELECT a.*,b.adi,b.fiyat,b.yetiskin,b.cocuk,c.manzaraAdi,d.aciklama\n" +
"FROM oda a LEFT JOIN odatipi b ON a.tipi=b.Id LEFT JOIN odamanzara c ON a.manzara=c.Id LEFT JOIN odadurum d ON a.durum=d.Id;"; 
    private static final String SELECT_ID = "SELECT * FROM oda WHERE Id =?;";  
    private static final String SELECT_KONTROL= "SELECT * FROM oda WHERE odaAdi =?;";  
    private static final String UPDATE_SQL = "UPDATE oda set odaAdi=?, tipi=?, manzara=?, durum=? WHERE Id = ?;";
    private static final String INSERT_SQL = "INSERT INTO oda (Id,odaAdi,tipi,manzara,durum) VALUES(0,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM oda WHERE Id = ?;";    
    public List<OdaListesi> odaListe() {
            
        List<OdaListesi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String odaAdi = rs.getString("odaAdi");
                int tipi = rs.getInt("tipi");
                String adi = rs.getString("adi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");                
                int manzara = rs.getInt("manzara");
                String manzaraAdi = rs.getString("manzaraAdi");
                int durum = rs.getInt("durum");
                String durumaciklama = rs.getString("aciklama");                
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new OdaListesi(Id,odaAdi,tipi,adi,fiyat,yetiskin,cocuk,manzara,manzaraAdi,durum,durumaciklama,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     

public boolean odaKontrol(String odaAdi) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONTROL);) {
            preparedStatement.setString(1, odaAdi);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    }  
public List<OdaListesi> selectOdalistesi(int ID) {
         List<OdaListesi> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);         
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String odaAdi = rs.getString("odaAdi");
                int tipi = rs.getInt("tipi");
                int manzara = rs.getInt("manzara");
                int durum = rs.getInt("durum");
                list.add(new OdaListesi(Id,odaAdi,tipi,manzara,durum));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }        

    public void OdaEkle(OdaListesi odalistesi) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, odalistesi.getodaAdi());
            preparedStatement.setInt(2, odalistesi.gettipi());
            preparedStatement.setInt(3, odalistesi.getmanzara());
            preparedStatement.setInt(4, odalistesi.getdurum());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    public void odaDuzelt(OdaListesi odalistesi) throws SQLException {
        System.out.println(UPDATE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, odalistesi.getodaAdi());
            preparedStatement.setInt(2, odalistesi.gettipi());
            preparedStatement.setInt(3, odalistesi.getmanzara());
            preparedStatement.setInt(4, odalistesi.getdurum());
            preparedStatement.setInt(5, odalistesi.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }    
    public boolean odaSil(int ID) throws SQLException {
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
