/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.kullaniciDAO;
import dao.menu;
import model.Kullanici;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author engin
 */
public class anasayfa extends HttpServlet{
    private kullaniciDAO kullaniciDAO;
    private Kullanici kullaniciList;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    if (request.getParameter("kullanici")!=null)
    {        
        String action = request.getServletPath();
        String username = request.getParameter("kullanici");
        String password = request.getParameter("sifre");     

        kullaniciList = new Kullanici();
        kullaniciList =  kullaniciDAO.selectKullanici(username, password);
        if (kullaniciList != null){
            session.setAttribute("otelUserName", kullaniciList.getadSoyad());
            session.setAttribute("otelYetki", kullaniciList.getyetki());
            menu menu = new menu();
            String menuHtml = menu.menuOlustur(kullaniciList.getyetki());
            request.setAttribute("gelenJAVA", "anasayfa"); 
            request.setAttribute("menuHtml", menuHtml);        
            request.setAttribute("menuDosya", "anasayfa.jsp"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("hata", "<div class=\"box-body pad res-tb-block\"><div class=\"alert alert-danger\">Kullanıcı Adı veya Şifre Hatalı!</div></div>"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);            
        }
    }
    else
        if (session.getAttribute("otelUserName")!=null)
        {
            menu menu = new menu();
            String menuHtml = menu.menuOlustur((int)session.getAttribute("otelYetki"));
            request.setAttribute("gelenJAVA", "anasayfa"); 
            request.setAttribute("menuHtml", menuHtml);        
            request.setAttribute("menuDosya", "anasayfa.jsp");           
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);             
        }   
    else
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }    
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
