

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LabTechProfile
 */
@WebServlet("/LabTechProfile")
public class LabTechProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabTechProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("header_LabTech.html").include(request, response);  
       // request.getRequestDispatcher("session_header.html").include(request, response);  
      //  ServletContext context=getServletContext();  
		//String name=(String)context.getAttribute("n");
		 HttpSession session=request.getSession(false);  
	        
         // out.println(name);
	        if(session!=null){  
	        	String name=(String)session.getAttribute("name"); 
          try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	      String n="";
      	      
      	      ResultSet rs=stmt.executeQuery("select * from labtech where lName='"+name+"'");
      	      rs.next();
      	      n=rs.getString("lName");
      	    //  String mail=rs.getString("email");
      	   // String sex=rs.getString("sex");
      	  String phone=rs.getString("phno");
      //	String address=rs.getString("address");
    //  	String age=(String)rs.getString("age");
      	      out.println("<br>Name = '"+n+"'<br>");
      	     // out.println("<br>mail='"+mail+"'<br>");
      	     // out.println("<br>gender = '"+sex+"'<br>");
      	      out.println("<br>phone no. ='"+phone+"'<br>");
      	     // out.println("<br>address = '"+address+"'<br>");
      	    //  out.println("<br>age = '"+age+"'<br>");
          } catch (Exception e){
		      out.println(e);
		    }
	        }
          else {
        	  out.print("<h1>Please login first... </h1>");  
              request.getRequestDispatcher("Login_LabTech.html").include(request, response);  
          }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
