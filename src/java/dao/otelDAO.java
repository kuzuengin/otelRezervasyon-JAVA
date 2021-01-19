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
import model.RezervasyonListesi;

/**
 *
 * @author engin
 */
public class otelDAO extends DAO {
public otelDAO() {}
    private static final String SELECT_ALL = "SELECT  a.Id AS odaID,a.odaAdi,a.tipi,a.manzara,a.durum,  \n" +
"d.adi AS tipadi,d.fiyat,d.yetiskin,d.cocuk, e.manzaraAdi,f.aciklama AS durumAciklama " +
"FROM oda a  \n" +
"LEFT JOIN odatipi d ON d.Id=a.tipi  \n" +
"LEFT JOIN odamanzara e ON e.Id=a.manzara  \n" +
"LEFT JOIN odadurum f ON f.Id=a.durum \n" +
"WHERE a.durum=0 \n" +
"AND a.Id NOT IN (SELECT odaID FROM rezervasyon b WHERE  \n" +
"(b.baslangicTarihi BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')OR b.bitisTarihi BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d'))\n" +
"OR (b.baslangicTarihi<=STR_TO_DATE(?,'%Y-%m-%d') AND b.bitisTarihi>=STR_TO_DATE(?,'%Y-%m-%d')) AND aktifPasif=1);"; 
    private static final String SELECT_ID = "SELECT a.*,b.adi,b.fiyat,b.yetiskin,b.cocuk,c.manzaraAdi,d.aciklama, datediff(?,?)+1 AS GUN " +
"FROM oda a LEFT JOIN odatipi b ON a.tipi=b.Id LEFT JOIN odamanzara c ON a.manzara=c.Id LEFT JOIN odadurum d ON a.durum=d.Id WHERE a.Id=?;";   
private static final String SELECT_KONTROL= "SELECT * FROM musteri WHERE telefon =?;"; 

    private static final String INSERT_SQL = "INSERT INTO rezervasyon (Id,musteriID,odaID,baslangicTarihi,bitisTarihi,durum,toplamTutar,aktifPasif,islemTarihi,ref) VALUES(0,?,?,?,?,?,?,1,NOW(),?);";
    private static final String DELETE_SQL = "DELETE FROM rezervasyon WHERE ref = ?;";    

public List<RezervasyonListesi> otelDurumListe(String tar1,String tar2) {
        List<RezervasyonListesi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             preparedStatement.setString(1,tar1);
             preparedStatement.setString(2, tar2);
             preparedStatement.setString(3, tar1);
             preparedStatement.setString(4, tar2); 
             preparedStatement.setString(5, tar1);
             preparedStatement.setString(6, tar2);   
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int odaID = rs.getInt("odaID");                
                String odaAdi = rs.getString("odaAdi");
                int tipi = rs.getInt("tipi");
                String tipadi = rs.getString("tipadi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");                
                int manzara = rs.getInt("manzara");
                String manzaraAdi = rs.getString("manzaraAdi");
                int durum = rs.getInt("durum");
                String durumAciklama = rs.getString("durumAciklama");  
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("odaID")+")\" />";
                String Sil = "";
                liste.add(new RezervasyonListesi(odaID,odaAdi,tipi,tipadi,fiyat,yetiskin,cocuk,manzara,manzaraAdi,durum,durumAciklama,Duzelt,Sil,SN));


            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     

public List<OdaListesi> selectOdalistesi(int ID,String tar1,String tar2) {
         List<OdaListesi> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setString(1, tar2);
            preparedStatement.setString(2,tar1);
            preparedStatement.setInt(3, ID);            
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
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
                String Sil =rs.getString("GUN"); ;
                list.add(new OdaListesi(Id,odaAdi,tipi,adi,fiyat,yetiskin,cocuk,manzara,manzaraAdi,durum,durumaciklama,Duzelt,Sil,ID));

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
    public void rezervasyonEkle(RezervasyonListesi revlistesi) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setInt(1, revlistesi.getmusteriID());
            preparedStatement.setInt(2, revlistesi.getodaID());
            preparedStatement.setString(3, revlistesi.getbaslangicTarihi());
            preparedStatement.setString(4, revlistesi.getbitisTarihi());
            preparedStatement.setInt(5, revlistesi.getdurum());
            preparedStatement.setInt(6, revlistesi.gettoplamTutar());
            preparedStatement.setString(7, revlistesi.getmd5());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
   
    public boolean rezervasyonSil(String KOD) throws SQLException {
        System.out.println(DELETE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setString(1, KOD);
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

