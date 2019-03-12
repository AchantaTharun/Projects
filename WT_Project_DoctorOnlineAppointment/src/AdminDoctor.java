

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

/**
 * Servlet implementation class AdminDoctor
 */
@WebServlet("/AdminDoctor")
public class AdminDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDoctor() {
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
        String name=request.getParameter("dname");
        String id=request.getParameter("did");
        
        try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	      int i=stmt.executeUpdate("delete from doctor where dName='"+name+"' and dID='"+id+"'");
      	      if(i!=0) {
      	    	out.println("<br>Doctor deleted");
		        out.println("<br>success");
      	      }
      	      else {
      	    	out.println("<br>failed");
      	      }
        } catch (Exception e){
		      out.println(e);
		    }
	}

}
