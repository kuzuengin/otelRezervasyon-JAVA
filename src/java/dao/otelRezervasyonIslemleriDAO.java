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
import static java.sql.Types.NULL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.OdaListesi;
import model.RezervasyonListesi;

/**
 *
 * @author engin
 */
public class otelRezervasyonIslemleriDAO  extends DAO {
public otelRezervasyonIslemleriDAO() {}
    private static final String SELECT_ALL = "SELECT  a.Id AS odaID,a.odaAdi,a.tipi,a.manzara,a.durum,  " +
"b.Id,b.musteriID,DATE_FORMAT(b.baslangicTarihi, '%d/%m/%Y')AS baslangicTarihi,DATE_FORMAT(b.bitisTarihi, '%d/%m/%Y')AS bitisTarihi,(CASE WHEN  b.durum>0 THEN b.durum ELSE a.durum END)AS odaDurumu,b.toplamTutar,b.aktifPasif,  " +
"c.ad,c.soyad,c.telefon,c.eposta,  " +
"d.adi AS tipadi,d.fiyat,d.yetiskin,d.cocuk,  " +
"e.manzaraAdi,f.aciklama AS durumAciklama,g.aciklama AS odaDurumAciklama,datediff(DATE_FORMAT(NOW(), '%Y-%m-%d'),b.baslangicTarihi) AS GUN   " +
"FROM oda a  " +
"LEFT JOIN rezervasyon b ON  a.Id=b.odaID AND aktifPasif=1 AND " +
"( (b.baslangicTarihi BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')) " +
"OR (b.bitisTarihi BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')) " +
"OR (b.baslangicTarihi<=STR_TO_DATE(?,'%Y-%m-%d') AND b.bitisTarihi>=STR_TO_DATE(?,'%Y-%m-%d')) " +
") "+
"LEFT JOIN musteri c ON c.Id=b.musteriID  " +
"LEFT JOIN odatipi d ON d.Id=a.tipi  " +
"LEFT JOIN odamanzara e ON e.Id=a.manzara  " +
"LEFT JOIN odadurum f ON f.Id=a.durum " +
"LEFT JOIN odadurum g ON g.Id=(CASE WHEN  b.durum>0 THEN b.durum ELSE a.durum END) " +
"ORDER BY odaAdi; "; 
    private static final String SELECT_ID = "SELECT a.Id,a.musteriID,a.odaID,a.baslangicTarihi,a.bitisTarihi,a.toplamTutar,a.durum,a.aktifPasif,b.ad,b.soyad,b.telefon,b.eposta,c.odaAdi,c.manzara,c.tipi, " +
"d.adi AS tipadi,d.fiyat,d.yetiskin,d.cocuk,e.manzaraAdi,f.aciklama AS durumAciklama ,datediff(a.bitisTarihi,a.baslangicTarihi)+1 AS GUN " +
"FROM rezervasyon a " +
"LEFT JOIN musteri b ON a.musteriID=b.Id " +
"LEFT JOIN oda c ON a.odaID=c.Id " +
"LEFT JOIN odatipi d ON d.Id=c.tipi  " +
"LEFT JOIN odamanzara e ON e.Id=c.manzara  " +
"LEFT JOIN odadurum f ON f.Id=a.durum " +
"WHERE a.Id=?;";   
private static final String SELECT_KONTROL= "SELECT * FROM musteri WHERE telefon =?;"; 
    private static final String UPDATE_SQL = "UPDATE rezervasyon set  musteriID=?, odaID=?, baslangicTarihi=?, bitisTarihi=?, durum=?,toplamTutar=?,aktifPasif=? WHERE Id = ?;";
    private static final String INSERT_SQL = "INSERT INTO rezervasyon (Id,musteriID,odaID,baslangicTarihi,bitisTarihi,durum,toplamTutar,aktifPasif,islemTarihi) VALUES(0,?,?,?,?,?,?,1,NOW());";
    private static final String DELETE_SQL = "DELETE FROM rezervasyon WHERE Id = ?;";    

public List<RezervasyonListesi> otelDurumListe(String tar1,String tar2) {
        List<RezervasyonListesi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             preparedStatement.setString(1,tar1);
             preparedStatement.setString(2, tar2);
             preparedStatement.setString(3,tar1);
             preparedStatement.setString(4, tar2);
             preparedStatement.setString(5,tar1);
             preparedStatement.setString(6, tar2);             
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                int musteriID = rs.getInt("musteriID");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String telefon = rs.getString("telefon");
                String eposta = rs.getString("eposta");  
                int odaID = rs.getInt("odaID");                
                String odaAdi = rs.getString("odaAdi");
                int tipi = rs.getInt("tipi");
                String tipadi = rs.getString("tipadi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");                
                int manzara = rs.getInt("manzara");
                String manzaraAdi = rs.getString("manzaraAdi");
                String baslangicTarihi = rs.getString("baslangicTarihi");
                String bitisTarihi = rs.getString("bitisTarihi");
                int toplamTutar = rs.getInt("toplamTutar");
                int durum = rs.getInt("odaDurumu");
                String durumAciklama = rs.getString("odaDurumAciklama");  
                int aktifPasif = rs.getInt("aktifPasif");
                int GUN = rs.getInt("GUN");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                if (musteriID==0)
                {ad="-";soyad="-";telefon="-";eposta="-";baslangicTarihi="-";bitisTarihi="-";Duzelt="";Sil="";}                
                if(GUN>0){Sil="";}
                liste.add(new RezervasyonListesi(Id,musteriID,ad,soyad,telefon,eposta,odaID,odaAdi,tipi,tipadi,fiyat,yetiskin,cocuk,manzara,manzaraAdi,baslangicTarihi,bitisTarihi,toplamTutar,durum,durumAciklama,aktifPasif,Duzelt,Sil,GUN));


            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     

public List<RezervasyonListesi> selectOdalistesi(int ID) {
         List<RezervasyonListesi> liste = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);            
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            int SN = 0;
           while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                int musteriID = rs.getInt("musteriID");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String telefon = rs.getString("telefon");
                String eposta = rs.getString("eposta");  
                int odaID = rs.getInt("odaID");                
                String odaAdi = rs.getString("odaAdi");
                int tipi = rs.getInt("tipi");
                String tipadi = rs.getString("tipadi");
                String fiyat = rs.getString("fiyat");                
                int yetiskin = rs.getInt("yetiskin");
                int cocuk = rs.getInt("cocuk");                
                int manzara = rs.getInt("manzara");
                String manzaraAdi = rs.getString("manzaraAdi");
                String baslangicTarihi = rs.getString("baslangicTarihi");
                String bitisTarihi = rs.getString("bitisTarihi");
                int toplamTutar = rs.getInt("toplamTutar");
                int durum = rs.getInt("durum");
                String durumAciklama = rs.getString("durumAciklama");  
                int aktifPasif = rs.getInt("aktifPasif");
                int GUN = rs.getInt("GUN");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new RezervasyonListesi(Id,musteriID,ad,soyad,telefon,eposta,odaID,odaAdi,tipi,tipadi,fiyat,yetiskin,cocuk,manzara,manzaraAdi,baslangicTarihi,bitisTarihi,toplamTutar,durum,durumAciklama,aktifPasif,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
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
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }  
    public void rezervasyonDuzelt(RezervasyonListesi revlistesi) throws SQLException, ParseException {
        System.out.println(UPDATE_SQL);
        try (    
            Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setInt(1, revlistesi.getmusteriID());
            preparedStatement.setInt(2, revlistesi.getodaID());
            preparedStatement.setString(3, revlistesi.getbaslangicTarihi());
            preparedStatement.setString(4, revlistesi.getbitisTarihi());
            preparedStatement.setInt(5, revlistesi.getdurum());
            preparedStatement.setInt(6, revlistesi.gettoplamTutar());
            preparedStatement.setInt(7, revlistesi.getaktifPasif());
            preparedStatement.setInt(8, revlistesi.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }    
    public boolean rezervasyonSil(int ID) throws SQLException {
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

    private boolean isNullOrEmpty(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

