/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import dao.parametreDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Parametre;

/**
 *
 * @author engin
 */
public class parametre extends HttpServlet{
    private parametreDAO parametreDAO;
    private Parametre parametreList;
   
@Override
    public void init() {
        parametreDAO = new parametreDAO();
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
        int kod1 = Integer.parseInt(request.getParameter("kod"));
        if (action==null) action="";
        switch (action) {
            case "combo":
                listeCombo(request, response,kod1);
                break;                
        }
                
          
    }   
    else
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }    
    }   

  
 private void listeCombo(HttpServletRequest request, HttpServletResponse response, int kod1) throws IOException{
      response.setContentType("text/html;charset=UTF-8");
        String combo="<option value=''>Seçiniz...</option>";
        List<Parametre> parametreler =  new parametreDAO().liste(kod1);
	for(Parametre parametre:parametreler)
	{
            combo = combo + "<option value='"+parametre.getkod2()+"'>"+parametre.getaciklama()+"</option>";
	}
        PrintWriter out = response.getWriter();
        out.print(combo);
        out.flush();
    }  
   
}
