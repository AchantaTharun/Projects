

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Advise
 */
@WebServlet("/Advise")
public class Advise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("Doctorheader.html").include(request, response);  
		 String pname=request.getParameter("pname");
	        String pid=request.getParameter("pid");
	        String a = request.getParameter("advise");
	        String advised="yes";
	    	HttpSession session=request.getSession(false);  
	    	 if(session!=null){  
	        String n1=(String)session.getAttribute("name"); 
	        try {
				Class.forName("com.mysql.jdbc.Driver");
			      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
			      Statement stmt = con.createStatement();
			     int i = stmt.executeUpdate("update appoint set advise='"+a+"' where Dname='"+n1+"' and Pname='"+pname+"' and PID='"+pid+"'") ;
			     if(i!=0) {
			    	 out.println("Successfully advised");
			    	 stmt.executeUpdate("update appoint set advised='"+advised+"' where Dname='"+n1+"' and Pname='"+pname+"' and PID='"+pid+"'") ;
			    	 //stmt.executeUpdate("update doctor set nDays='"+day+"' where dName='"+dname+"'") ;
			     }
			     else {
			    	 out.println("faileded");
			     }
			} catch (Exception e){
			      out.println(e);
			    }
	    	 }
	         else {
	         	  out.print("<h1>Please login first... </h1>");  
	               request.getRequestDispatcher("Login_Doctor.html").include(request, response);  
	           }
	}

}
