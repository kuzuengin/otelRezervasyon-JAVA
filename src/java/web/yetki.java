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
import dao.yetkiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Kullanici;
import model.Yetki;

/**
 *
 * @author engin
 */
public class yetki extends HttpServlet{
    private yetkiDAO yetkiDAO;
    private Yetki yetkiList;
    private  Gson gson;    
@Override
    public void init() {
        yetkiDAO = new yetkiDAO();
    }
   
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
            case "insert":
                ekle(request, response);
                break;
            case "delete":
                sil(request, response);
                break;
            case "edit":
                bilgi(request, response);
                break;
            case "update":
                duzelt(request, response);
                break;
            default:
                anaListesi(request, response);
                break;
        }
                
          
    }   
    else
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }    
    }    
  private void anaListesi(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
            HttpSession session = request.getSession(true);
            menu menu = new menu();
            String menuHtml = menu.menuOlustur((int) session.getAttribute("otelYetki"));
            request.setAttribute("gelenJAVA", "yetkiListesi"); 
            request.setAttribute("menuHtml", menuHtml); 
            request.setAttribute("menuDosya", "yetkiListesi.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);    
    }
  
  private void liste(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        List<Yetki> yetki =  new yetkiDAO().liste();
        gson = new GsonBuilder().create();
        String userJsonString = gson.toJson(yetki);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("{\"data\": "+userJsonString+"}");
        out.flush();
    }
  private void listeCombo(HttpServletRequest request, HttpServletResponse response) throws IOException{
      response.setContentType("text/html;charset=UTF-8");
        String combo="<option value=''>Yetki Seçiniz...</option>";
        List<Yetki> yetkiler =  new yetkiDAO().liste();
	for(Yetki yetki:yetkiler)
	{
            combo = combo + "<option value='"+yetki.getId()+"'>"+yetki.getyetki()+"</option>";
	}
        PrintWriter out = response.getWriter();
        out.print(combo);
        out.flush();
    }  
    private void bilgi(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
    }    
    private void ekle(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
    }
    private void sil(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
    }
    private void duzelt(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
    }        
}
