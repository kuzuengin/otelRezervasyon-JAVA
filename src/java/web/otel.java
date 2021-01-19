/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.musteriListesiDAO;
import dao.otelDAO;
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
import model.MusteriListesi;
import model.OdaListesi;
import model.RezervasyonListesi;
import model.kayitjson;
import static web.MD5.getMd5;

/**
 *
 * @author engin
 */
public class otel extends HttpServlet{
    
    private otelDAO otelDAO;
    private  Gson gson;
    private musteriListesiDAO musteriListesiDAO;



    @Override
    public void init() {
        otelDAO = new otelDAO();
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
        String action = request.getParameter("action");
        if (action==null) action="";
         try {
        switch (action) {
            case "select": liste(request, response); break;
            case "insert": ekle(request, response);  break;
            case "edit": edit(request, response);    break;
            case "delete": sil(request, response);    break;                
            default: ListeAna(request, response);    break;
        }
        } catch (SQLException ex) {
            Logger.getLogger(otel.class.getName()).log(Level.SEVERE, null, ex);
        }        
   
    }
    
    private void ListeAna(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/otel.jsp");
            dispatcher.forward(request, response);           
    }
    private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        String btarih=request.getParameter("btarih");
        String starih=request.getParameter("starih");        
        List<RezervasyonListesi> list =  new otelDAO().otelDurumListe(btarih,starih);
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(list);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }
  
    private void ekle(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
    {
        String ad = request.getParameter("ad");
        String soyad = request.getParameter("soyad");
        String telefon = request.getParameter("telefon");
        String eposta = request.getParameter("eposta");
        int odaID= Integer.parseInt(request.getParameter("Id"));
        int toplamTutar = Integer.parseInt(request.getParameter("toplamTutar"));            
        String baslangicTarihi=request.getParameter("baslangicTarihi");
        String bitisTarihi=request.getParameter("bitisTarihi");
        
        int kayitvar; 
        int musteriID;
        musteriID= musteriListesiDAO.musteriKontrol(telefon);
        if (musteriID==0)
        {
            MusteriListesi musteri = new MusteriListesi(0,ad,soyad,telefon,eposta);
            musteriListesiDAO.musteriEkle(musteri);
            musteriID = musteriListesiDAO.musteriKontrol(telefon);
        }
         kayitjson sonuc;
        String md5;
        md5=getMd5(baslangicTarihi+bitisTarihi+ad+soyad);         
        RezervasyonListesi list = new RezervasyonListesi(0,musteriID,odaID,baslangicTarihi,bitisTarihi,toplamTutar,2,1,md5);   
        otelDAO.rezervasyonEkle(list);

        sonuc=new kayitjson("Evet","Rezervasyon İşlemleri","Kayıt İşlemi Tamamlanmıştır.",md5);
        
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
        String tar1 = request.getParameter("btarih");
        String tar2 = request.getParameter("starih");
        System.out.println("Gelen id:" + ID);        
        List<OdaListesi> sonuc =  new otelDAO().selectOdalistesi(ID,tar1,tar2);
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
        String KOD = request.getParameter("kod");
        boolean kayitsil=false;
        kayitjson sonuc;
        try {
            kayitsil = otelDAO.rezervasyonSil(KOD);
            if (kayitsil)
            {
                sonuc=new kayitjson("Evet","Rezervasyon  Listesi","Silme İşlem Tamamlanmıştır.");
            }
            else {
                sonuc=new kayitjson("Hayır","Rezervasyon Listesi","Silme İşlem Yapılamadı.");
            }
        } catch (SQLException ex) {
            sonuc=new kayitjson("Hayır","Rezervasyon Listesi","Silme İşlem Yapılamadı.");
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