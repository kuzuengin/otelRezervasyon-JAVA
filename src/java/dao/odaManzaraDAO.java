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
import model.OdaManzara;

/**
 *
 * @author engin
 */
public class odaManzaraDAO extends DAO {
public odaManzaraDAO() {}
    private static final String SELECT_ALL = "SELECT * FROM odamanzara ORDER BY Id;"; 
    private static final String SELECT_ID = "SELECT * FROM odamanzara WHERE Id =?;";  
    private static final String SELECT_KONTROL= "SELECT * FROM odamanzara WHERE manzaraAdi =?;";  
    private static final String UPDATE_SQL = "UPDATE odamanzara set manzaraAdi = ? WHERE Id = ?;";
    private static final String INSERT_SQL = "INSERT INTO odamanzara (Id,manzaraAdi) VALUES(0,?);";
    private static final String DELETE_SQL = "DELETE FROM odamanzara WHERE Id = ?;";    
    public List<OdaManzara> odaManzaraListe() {
            
        List<OdaManzara> liste = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
             ResultSet rs = preparedStatement.executeQuery();
             int SN = 0;
            while (rs.next()) {
                SN++;
                int Id = rs.getInt("Id");
                String manzaraAdi = rs.getString("manzaraAdi");
                String Duzelt ="<img src='fon/duzelt.png' data-toggle=\"modal\" data-target=\"#modal-kayit\" onclick=\"duzelt("+rs.getInt("Id")+")\" />";
                String Sil ="<img src='fon/sil.png' onclick=\"kayitSil("+rs.getInt("Id")+")\" />";
                liste.add(new OdaManzara(Id,manzaraAdi,Duzelt,Sil,SN));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return liste;
    }     

        public boolean manzaraKontrol(String manzaraAdi) {
        int size = 0;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KONTROL);) {
            preparedStatement.setString(1, manzaraAdi);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             rs.last();
            size = rs.getRow();
        } catch (SQLException e) {
            printSQLException(e);
        }
    return size>0;
    }  
public List<OdaManzara> selectManzara(int ID) {
         List<OdaManzara> list = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);) {
            preparedStatement.setInt(1, ID);         
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String manzaraAdi = rs.getString("manzaraAdi");
                list.add(new OdaManzara(Id,manzaraAdi));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }        

    public void manzaraEkle(OdaManzara odamanzara) throws SQLException {
        System.out.println(INSERT_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, odamanzara.getmanzaraAdi());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    public void manzaraDuzelt(OdaManzara odamanzara) throws SQLException {
        System.out.println(UPDATE_SQL);
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, odamanzara.getmanzaraAdi());
            preparedStatement.setInt(2, odamanzara.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }    
    public boolean manzaraSil(int ID) throws SQLException {
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
