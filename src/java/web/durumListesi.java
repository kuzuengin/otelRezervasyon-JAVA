/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.kullaniciDAO;
import dao.menu;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Kullanici;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class durumListesi extends HttpServlet{
    
    private kullaniciDAO kullaniciDAO;
    private Kullanici kullaniciList;
    private  Gson gson;

    @Override
    public void init() {
        kullaniciDAO = new kullaniciDAO();
    }
   
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("otelUserName")!=null)
        {
            String action = request.getParameter("action");
            if (action==null) action="";
            switch (action) {
                case "select":
                    liste(request, response);
                    break;
                case "edit":
                    edit(request, response);
                    break;
                default:
                    kullaniciListesiAna(request, response);
                    break;
            }
        }   
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }    
    }
    
    private void kullaniciListesiAna(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            HttpSession session = request.getSession(true);
            menu menu = new menu();
            String menuHtml = menu.menuOlustur((int) session.getAttribute("otelYetki"));
            request.setAttribute("gelenJAVA", "kullaniciListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "kullaniciListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<Kullanici> user =  new kullaniciDAO().kullaniciListe();
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(user);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID);        
        List<Kullanici> sonuc =  new kullaniciDAO().kullaniciID(ID);
        System.out.println(sonuc);        
        response.setContentType("application/json;charset=UTF-8");      
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(sonuc);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userJsonString);
        out.flush();         
    }    

    private void sil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID); 
        boolean kayitsil=false;
        kayitjson sonuc;
        try {
            kayitsil = kullaniciDAO.kullaniciSil(ID);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Kullanıcı Listesi","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Kullanıcı Listesi","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Kullanıcı Listesi","Silme İşlem Yapılamadı.");
        }
        
        
        response.setContentType("application/json;charset=UTF-8");      
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(sonuc);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userJsonString);
        out.flush();        
    }
   
    
}
