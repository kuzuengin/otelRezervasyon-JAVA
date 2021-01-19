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
import dao.otelRezervasyonIslemleriDAO;
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
import model.RezervasyonListesi;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class otelRezervasyonIslemleri extends HttpServlet{
    
    private otelRezervasyonIslemleriDAO otelRezervasyonIslemleriDAO;
    private  Gson gson;
    private odaListesiDAO odaListesiDAO;

    @Override
    public void init() {
        otelRezervasyonIslemleriDAO = new otelRezervasyonIslemleriDAO();
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
            request.setAttribute("gelenJAVA", "view_otelRezervasyonListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "view_otelRezervasyonListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        String btarih=request.getParameter("btarih");
        String starih=request.getParameter("starih");           
        List<RezervasyonListesi> list =  new otelRezervasyonIslemleriDAO().otelDurumListe(btarih, starih);
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
        String bitisTarihi=request.getParameter("bitisTarihi");
        int toplamTutar = Integer.parseInt(request.getParameter("toplamTutar"));            
    
        RezervasyonListesi list = new RezervasyonListesi(0,musteriID,odaID,baslangicTarihi,bitisTarihi,toplamTutar,2,1);   
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                otelRezervasyonIslemleriDAO.rezervasyonEkle(list);
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
            kayitsil = otelRezervasyonIslemleriDAO.rezervasyonSil(ID);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Rezervasyon İşlemleri","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Rezervasyon İşlemleri","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Rezervasyon İşlemleri","Silme İşlem Yapılamadı.");
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
        RezervasyonListesi list = new RezervasyonListesi(Id,musteriID,odaID,baslangicTarihi,bitisTarihi,toplamTutar,durum,1);   
        kayitjson sonuc;
        try {
                otelRezervasyonIslemleriDAO.rezervasyonDuzelt(list);
                sonuc=new kayitjson("Evet","Rezervasyon İşlemleri","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Rezervasyon İşlemleri","Kayıt Düzeltme İşlemi Yapılamadı.");
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
 
      private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int ID = Integer.parseInt(request.getParameter("id"));
        System.out.println("Gelen id:" + ID);        
        List<RezervasyonListesi> sonuc =  new otelRezervasyonIslemleriDAO().selectOdalistesi(ID);
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
}