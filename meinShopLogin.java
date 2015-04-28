
import java.io.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class meinShopLogin extends HttpServlet
{
   public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException
   {
	   
	  HttpSession session= req.getSession();
	  String jSessionID=session.getId();
	  ArrayList al = (ArrayList)session.getAttribute("TeeListe");
	  boolean fehler = false;
      
	  
	  session.setAttribute("TeeListe",al);
	  String password = req.getParameter("password");
	  String username = req.getParameter("username");
	  session.setAttribute("username",username);
	  String abfrage ="";
	  
	  
      PrintWriter out =  res.getWriter();
     
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
		 ResultSet rs = st.executeQuery("SELECT * FROM Benutzer WHERE  Username= '"+username+"';");
		 
		 if(rs.next())
         {
            abfrage = rs.getString("Password"); 
			
         }
		 
		 
		 
		 if(password.equals(abfrage)){
			out.println("<html><head><title>Weiterleitung</title><SCRIPT type=\"text/javascript\">window.location.href=\"/~dx47/meinShopKategorien.jsp\";</SCRIPT></head><body>");

		 }
		else{
			out.println("<html><head><title>Weiterleitung</title><<SCRIPT type=\"text/javascript\">window.location.href=\"/~dx47/meinShopLog.jsp\";</SCRIPT></head><body>");
			fehler = true;
		
		}
         
        session.setAttribute("fehler",fehler);

         	
			
        
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