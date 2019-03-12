

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookAppointment
 */
@WebServlet("/BookAppointment")
public class BookAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("header.html").include(request, response);  
    /*    ServletContext context=getServletContext();  
		String name=(String)context.getAttribute("pn");
		HttpSession session=request.getSession(false);  
        String n=(String)session.getAttribute("un");
        out.println(n);
        out.println("name= '"+n+"'<br>");
        */
        String dname=request.getParameter("dname");
        String pname=request.getParameter("pname");
        String pid=request.getParameter("pid");
        String day=request.getParameter("day");
        String did=request.getParameter("did");
        String state = "active";
        String advised ="no";
      /*  out.println("name= '"+name+"'<br>");
        out.println("name= '"+pname+"'<br>");
        out.println("patient id= '"+pid+"'<br>");
        out.println("day= '"+day+"'<br>");
	    if(name!=null) {
	    	out.println("success");
	    }
	    else {
	    	out.println("unsuccess");
	    }
	    */
        try {
			Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
		     int i = stmt.executeUpdate("insert into appoint(PID,DID,Pname,Dname,day,state,advised) values('"+pid+"', '"+did+"', '"+pname+"', '"+dname+"', '"+day+"','"+state+"','"+advised+"')") ;
		     if(i!=0) {
		    	 out.println("Booked");
		    	 stmt.executeUpdate("update doctor set nDays='"+day+"' where dName='"+dname+"'") ;
		     }
		     else {
		    	 out.println("faileded");
		     }
		} catch (Exception e){
		      out.println(e);
		    }
		
	}
	
	

}
