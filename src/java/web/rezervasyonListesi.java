/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.menu;
import dao.odaListesiDAO;
import dao.rezervasyonListesiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OdaListesi;
import model.RezervasyonListesi;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class rezervasyonListesi extends HttpServlet{
    
    private rezervasyonListesiDAO rezervasyonListesiDAO;
    private  Gson gson;
    private odaListesiDAO odaListesiDAO;

    @Override
    public void init() {
        rezervasyonListesiDAO = new rezervasyonListesiDAO();
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
                    case "insert":
                        ekle(request, response);
                        break;
                    case "delete":
                        sil(request, response);
                        break;
                    case "update":
                        duzelt(request, response);
                        break;
                    case "odaupdate":
                        odaduzelt(request, response);
                        break;                        
                    default:
                        ListeAna(request, response);
                        break;
                }   } catch (SQLException ex) {
                Logger.getLogger(kullaniciListesi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(rezervasyonListesi.class.getName()).log(Level.SEVERE, null, ex);
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
            request.setAttribute("gelenJAVA", "rezervasyonListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "rezervasyonListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<RezervasyonListesi> list =  new rezervasyonListesiDAO().odaDurumListe(request);
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(list);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }
  
    private void ekle(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        int musteriID= Integer.parseInt(request.getParameter("musteriID"));
        int odaID= Integer.parseInt(request.getParameter("odaID"));
        String baslangicTarihi=request.getParameter("baslangicTarihi");
        String bitisTarihi=request.getParameter("bitisTarihirequest");
        int toplamTutar = Integer.parseInt(request.getParameter("toplamTutar"));
        int durum = Integer.parseInt(request.getParameter("durum"));
        int aktifPasif= Integer.parseInt(request.getParameter("aktifPasif"));        
        RezervasyonListesi list = new RezervasyonListesi(0,musteriID,odaID,baslangicTarihi,bitisTarihi,toplamTutar,durum,aktifPasif);   
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                rezervasyonListesiDAO.rezervasyonEkle(list);
                sonuc=new kayitjson("Evet","Rezervasyon İşlemleri","Kayıt İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Rezervasyon İşlemleri","Kayıt İşlemi Yapılamadı.");
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
            kayitsil = rezervasyonListesiDAO.rezervasyonSil(ID);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Oda Listesi","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Oda Listesi","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Listesi","Silme İşlem Yapılamadı.");
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
    private void duzelt(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
        int Id = Integer.parseInt(request.getParameter("Id"));
        int musteriID= Integer.parseInt(request.getParameter("musteriID"));
        int odaID= Integer.parseInt(request.getParameter("odaID"));
        String baslangicTarihi=request.getParameter("baslangicTarihi");
        String bitisTarihi=request.getParameter("bitisTarihi");
        int toplamTutar = Integer.parseInt(request.getParameter("toplamTutar"));           
        int durum= Integer.parseInt(request.getParameter("durum"));
        int aktifPasif= Integer.parseInt(request.getParameter("aktifPasif"));
        RezervasyonListesi list = new RezervasyonListesi(Id,musteriID,odaID,baslangicTarihi,bitisTarihi,toplamTutar,durum,aktifPasif);   
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                rezervasyonListesiDAO.rezervasyonDuzelt(list);
                sonuc=new kayitjson("Evet","Oda Listesi","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Listesi","Kayıt Düzeltme İşlemi Yapılamadı.");
        }
        if (durum==3){
            odaduzelt(request, response);
        }
        else
        {
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
    private void odaduzelt(HttpServletRequest request, HttpServletResponse response) throws IOException{
        odaListesiDAO = new odaListesiDAO();
        int Id = Integer.parseInt(request.getParameter("Id"));
        String odaAdi = request.getParameter("odaAdi");
        int tipi = Integer.parseInt(request.getParameter("tipi"));
        int manzara = Integer.parseInt(request.getParameter("manzara"));
        int durum = Integer.parseInt(request.getParameter("durum"));
        OdaListesi odalist = new OdaListesi(Id,odaAdi,tipi,manzara,durum);   
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                odaListesiDAO.odaDuzelt(odalist);
                sonuc=new kayitjson("Evet","Oda Listesi","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Oda Listesi","Kayıt Düzeltme İşlemi Yapılamadı.");
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