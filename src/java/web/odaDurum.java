/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.menu;
import dao.odaDurumDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Kullanici;
import model.OdaDurum;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class odaDurum extends HttpServlet{
    
    private odaDurumDAO odaDurumDAO;
    private Kullanici kullaniciList;
    private Gson gson;

    @Override
    public void init() {
        odaDurumDAO = new odaDurumDAO();
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
                case "combo":
                    listeCombo(request, response);
                    break;                    
                case "edit":
                    edit(request, response);
                    break;
                case "update":
                    duzelt(request, response);
                    break;
                default:
                    listesiAna(request, response);
                    break;
            }
        }   
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }    
    }
    
    private void listesiAna(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            HttpSession session = request.getSession(true);
            menu menu = new menu();
            String menuHtml = menu.menuOlustur((int) session.getAttribute("otelYetki"));
            request.setAttribute("gelenJAVA", "odaDurum"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "odaDurumListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    } 
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<OdaDurum> liste =  new odaDurumDAO().odaDurumListe();
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(liste);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }   
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID);        
        List<OdaDurum> liste =  new odaDurumDAO().selectDurum(ID);
        System.out.println(liste);        
        response.setContentType("application/json;charset=UTF-8");      
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(liste);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(userJsonString);
        out.flush();         
    }    
  

    private void duzelt(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int Id = Integer.parseInt(request.getParameter("Id"));
        String aciklama = request.getParameter("aciklama");
        OdaDurum newOda = new OdaDurum(Id,aciklama);    
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
            kayitvar = odaDurumDAO.aciklamaKontrol(aciklama);
            if (kayitvar)
            {
                sonuc=new kayitjson("Hayır","Oda Durum Listesi","Bu Oda Durum ile Kayıt Mevcut...");
            }
            else {
                odaDurumDAO.odaDurumDuzelt(newOda);
                sonuc=new kayitjson("Evet","Oda Durum Listesi","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Durum Listesi","Kayıt Düzeltme İşlemi Yapılamadı.");
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
        String combo="<option value=''>Oda Durumunu Seçiniz...</option>";
        List<OdaDurum> odadurumlar =  new odaDurumDAO().odaDurumListe();
	for(OdaDurum odadurum:odadurumlar)
	{
            combo = combo + "<option value='"+odadurum.getId()+"'>"+odadurum.getaciklama()+"</option>";
	}
        PrintWriter out = response.getWriter();
        out.print(combo);
        out.flush();
    }     
    
}