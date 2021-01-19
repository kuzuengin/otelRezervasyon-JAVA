/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
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
            case "combo":
                listeCombo(request, response);
                break;                
        }
                
          
    }   
    else
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }    
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
     
}
