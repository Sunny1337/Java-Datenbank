
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class meinShopRegistrierung extends HttpServlet
{
   public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
   {
	   
	  HttpSession session= req.getSession();
	  String jSessionID=session.getId();
	  ArrayList al = (ArrayList)session.getAttribute("TeeListe");
	  
      
	  
	  session.setAttribute("TeeListe",al);
	  String vorname = req.getParameter("vorname");
	  String username = req.getParameter("username");
	  String nachname = req.getParameter("nachname");
	  String strasse = req.getParameter("strasse");
	  String hausnummer = req.getParameter("hausnummer");
	  int hausnummerI = Integer.parseInt(hausnummer);
	  String stadt = req.getParameter("stadt");
	  String telefon = req.getParameter("telefon");
	  int telefonI = Integer.parseInt(telefon);
	  String email = req.getParameter("email");
	  String password = req.getParameter("pw");
	  session.setAttribute("username",username);
	  
	  
	  
      PrintWriter out =  res.getWriter();
      /
      Connection conn = null;
      Statement stmt = null;
	  
      try
      {
          Class.forName("org.gjt.mm.mysql.Driver");  
      }
      catch (ClassNotFoundException e)
      {
          out.println("DB-Treiber nicht da!");
      }
	  
	 try
      {
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dx47", "dx47", "UEZN");
        
         Statement st = con.createStatement(); 
         String sqlStr = "leer";
		 st.executeUpdate("INSERT INTO `dx47`.`Benutzer` (`Username`,`Password`) VALUES('"+username+"','"+password+"');");
         st.executeUpdate("INSERT INTO `dx47`.`Kunden` (`id`,`Username`, `Nachname`, `Vorname`, `Strasse`, `Hausnummer`, `Stadt`,"
         		+ " `Telefon`, `Email`) VALUES (NULL,'"+username+"', '"+nachname+"', '"+vorname+"', '"+strasse+"',"
         				+ " '"+hausnummerI+"', '"+stadt+"', '"+telefonI+"', '"+email+"');");
         
		
         
         out.println("<html><head><title>Weiterleitung</title><SCRIPT type=\"text/javascript\">window.location.href=\"/~dx47/meinShopLog.jsp\";</SCRIPT></head>");
        

         	
			
        
         out.println("<hr> </body> </html>");
         st.close();
         con.close();
     }
     catch (Exception e)
     {
         out.println(" MySQL Exception: " + e.getMessage());
     }
     
   }
     
   
   public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
   {
      doGet(req,res);
   }
    
}