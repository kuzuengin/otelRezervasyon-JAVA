/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.menu;
import dao.musteriListesiDAO;
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
import model.MusteriListesi;
import model.kayitjson;

/**
 *
 * @author engin
 */
public class musteriListesi  extends HttpServlet{
    
    private musteriListesiDAO musteriListesiDAO;
    private  Gson gson;

    @Override
    public void init() {
        musteriListesiDAO = new musteriListesiDAO();
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
            request.setAttribute("gelenJAVA", "musteriListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "musteriListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<MusteriListesi> list =  new musteriListesiDAO().musteriListe();
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
        List<MusteriListesi> sonuc =  new musteriListesiDAO().selectMusterilistesi(ID);
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
        String ad = request.getParameter("ad");
        String soyad = request.getParameter("soyad");
        String telefon = request.getParameter("telefon");
        String eposta = request.getParameter("eposta");
        MusteriListesi musteri = new MusteriListesi(0,ad,soyad,telefon,eposta);
        int kayitvar;
        kayitjson sonuc;
        try {
            kayitvar = musteriListesiDAO.musteriKontrol(telefon);
            if (kayitvar!=0)
            {
                sonuc=new kayitjson("Hayır","Müşteri Listesi","Bu Telefon ile Kayıt Mevcut...");
            }
            else {
                musteriListesiDAO.musteriEkle(musteri);
                sonuc=new kayitjson("Evet","Müşteri Listesi","Kayıt İşlemi Tamamlanmıştır.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Müşteri Listesi","Kayıt İşlemi Yapılamadı.");
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
            kayitsil = musteriListesiDAO.musteriSil(ID);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Müşteri Listesi","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Müşteri Listesi","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Müşteri Listesi","Silme İşlem Yapılamadı.");
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
        String ad = request.getParameter("ad");
        String soyad = request.getParameter("soyad");
        String telefon = request.getParameter("telefon");
        String eposta = request.getParameter("eposta");
        MusteriListesi musteri = new MusteriListesi(Id,ad,soyad,telefon,eposta);
        boolean kayitvar=false;
        kayitjson sonuc;
        try {
                musteriListesiDAO.musteriDuzelt(musteri);
                sonuc=new kayitjson("Evet","Müşteri Listesi","Kayıt Düzeltme İşlemi Tamamlanmıştır.");
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Müşteri Listesi","Kayıt Düzeltme İşlemi Yapılamadı.");
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