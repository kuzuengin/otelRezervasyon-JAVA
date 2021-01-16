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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author engin
 */
public class menu extends DAO {
    
      private static final String SELECT_MENU1 = "SELECT a.Id,a.menuad  FROM menu a LEFT JOIN yetkimenu b ON b.menuID=a.Id  WHERE a.ustid=0 AND b.yetkiID=?"; 
      private static final String SELECT_MENU2 = "SELECT dosya,menuad  FROM menu  WHERE ustid=?"; 
public menu() {}      

    /**
     *
     * @param request
     * @return
     */
    public String menuOlustur(int otelYetki)  {
    String menuHtml = "";
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU1);) {
            preparedStatement.setInt(1, otelYetki);
         
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String menuad = rs.getString("menuad");
                menuHtml = menuHtml + "<li class='treeview'><a href='#'><i class='icon-grid'></i><span>"+menuad+"</span><span class='pull-right-container'>";
                menuHtml = menuHtml + "<i class='fa fa-angle-right pull-right'></i>";
                menuHtml = menuHtml + "</span></a><ul class='treeview-menu'>";
                menuHtml = menuHtml + menuOlusturAlt(Id);
                menuHtml = menuHtml +"</ul></li>";
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menuHtml;    

}   
    public String menuOlusturAlt(int ustID)  {
 String menuHtml = "";
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU2);) {
            preparedStatement.setInt(1, ustID);
         
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String dosya = rs.getString("dosya");
                String menuad = rs.getString("menuad");
                menuHtml = menuHtml + "<li><a href='"+dosya+"'>"+menuad+"</a></li>";
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menuHtml;    

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

