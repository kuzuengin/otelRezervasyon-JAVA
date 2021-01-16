/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.menu;
import dao.odaTipiDAO;
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
import model.OdaTipi;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class odaTipi extends HttpServlet{
    
    private odaTipiDAO odaTipiDAO;
    private  Gson gson;

    @Override
    public void init() {
        odaTipiDAO = new odaTipiDAO();
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
            try {
                String action = request.getParameter("action");
                if (action==null) action="";
                switch (action) {
                    case "select":
                        liste(request, response);
                        break;
                    case "combo":
                        listeCombo(request, response);
                        break;
                    case "insert":
                        ekle(request, response);
                        break;
                    case "delete":
                        sil(request, response);
                        break;
                    case "edit":
                        edit(request, response);
                        break;
                    case "update":
                        duzelt(request, response);
                        break;
                    default:
                        ListeAna(request, response);
                        break;
                }   } catch (SQLException ex) {
                Logger.getLogger(kullaniciListesi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }    
    }
    
    private void ListeAna(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            HttpSession session = request.getSession(true);
            menu menu = new menu();
            String menuHtml = menu.menuOlustur((int) session.getAttribute("otelYetki"));
            request.setAttribute("gelenJAVA", "odaTipiListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "odaTipiListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<OdaTipi> list =  new odaTipiDAO().odaTipiListe();
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(list);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID);        
        List<OdaTipi> sonuc =  new odaTipiDAO().selectOdaTipi(ID);
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
    private void ekle(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        String adi = request.getParameter("adi");
        String fiyat = request.getParameter("fiyat");
        int yetiskin = Integer.parseInt(request.getParameter("yetiskin"));
        int cocuk = Integer.parseInt(request.getParameter("cocuk"));
        OdaTipi odatipi = new OdaTipi(0,adi,fiyat,yetiskin,cocuk);
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
            kayitvar = odaTipiDAO.odaTipiKontrol(adi);
            if (kayitvar)
            {
                sonuc=new kayitjson("Hayır","Oda Tipi Listesi","Bu Oda Tipi Adı ile Kayıt Mevcut...");
            }
            else {
                odaTipiDAO.OdaTipiEkle(odatipi);
                sonuc=new kayitjson("Evet","Oda Tipi Listesi","Kayıt İşlemi Tamamlanmıştır.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Tipi Listesi","Kayıt İşlemi Yapılamadı.");
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
    private void sil(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID); 
        boolean kayitsil=false;
        kayitjson sonuc;
        try {
            kayitsil = odaTipiDAO.OdaTipiSil(ID);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Oda Tipi Listesi","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Oda Tipi Listesi","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Tipi Listesi","Silme İşlem Yapılamadı.");
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
    private void duzelt(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int Id = Integer.parseInt(request.getParameter("Id"));
        String adi = request.getParameter("adi");
        String fiyat = request.getParameter("fiyat");
        int yetiskin = Integer.parseInt(request.getParameter("yetiskin"));
        int cocuk = Integer.parseInt(request.getParameter("cocuk"));
        OdaTipi odatipi = new OdaTipi(Id,adi,fiyat,yetiskin,cocuk);   
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                odaTipiDAO.OdaTipiDuzelt(odatipi);
                sonuc=new kayitjson("Evet","Oda Tipi Listesi","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Tipi Listesi","Kayıt Düzeltme İşlemi Yapılamadı.");
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
 private void listeCombo(HttpServletRequest request, HttpServletResponse response) throws IOException{
      response.setContentType("text/html;charset=UTF-8");
        String combo="<option value=''>Oda Tipi Seçiniz...</option>";
        List<OdaTipi> odatipleri =  new odaTipiDAO().odaTipiListe();
	for(OdaTipi odatipi:odatipleri)
	{
            combo = combo + "<option value='"+odatipi.getId()+"'>"+odatipi.getadi()+"</option>";
	}
        PrintWriter out = response.getWriter();
        out.print(combo);
        out.flush();
    }     
    
}
