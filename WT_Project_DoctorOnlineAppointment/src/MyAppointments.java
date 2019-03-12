

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
 * Servlet implementation class MyAppointments
 */
@WebServlet("/MyAppointments")
public class MyAppointments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAppointments() {
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
        request.getRequestDispatcher("Doctorheader.html").include(request, response);  
		HttpSession session=request.getSession(false);  
		 if(session!=null){  
        String n1=(String)session.getAttribute("name"); 
        String p=(String)session.getAttribute("pword"); 
        //out.println(n1+" "+p);
        
        try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	      ResultSet rs1=stmt.executeQuery("select * from appoint where Dname='"+n1+"'");
      	    if(rs1.next()) {
      	    	 ResultSet rs=stmt.executeQuery("select * from appoint where Dname='"+n1+"'");
      	     while(rs.next()) {
      	    	 String pname=rs.getString("Pname");
      	    	String pid=rs.getString("PID");
      	    	String day=rs.getString("day");
      	    	String state =rs.getString("state");
      	    	String advised =rs.getString("advised");
      	    	 String days="";
      	    	 out.println("<br>patient ='"+pname+"'");
      	    	out.println("<br>patient id ='"+pid+"'");
      	    	out.println("<br>state ='"+state+"'");
      	    	out.println("<br>Advised? ='"+advised+"'");
      	    	if(day.equals("0")) {
         			days="Monday";
         		}
         		else if(day.equals("1")) {
         			days="Tuesday";
         		}
               else if(day.equals("2")) {
            	   days="Wednesday";
         		}
               else if(day.equals("3")) {
            	   days="Thursday";
        		}
               else if(day.equals("4")) {
            	   days="Friday";
        		}
               else if(day .equals("5")) {
            	   days="Saturday";
        		}
               else if(day.equals("6")) {
            	   days="Sunday";
        		}
      	    	out.println("<br>day of appintment ='"+days+"'");
      	    	out.println("<hr>");
      	    	 
      	     }
      	   out.print("<br><br><br>");
      	   out.print("<form action='Advise' method='post'>");
      	  out.print("Enter advise<textarea rows='3' cols='30' name='advise'></textarea><br>");
      	 out.print("Enter patient id<input type='text' name='pid'>");
      	 out.print("Enter patient name<input type='text' name='pname'>");
      	 out.print("<input type='submit' value='Book'/>");
      	  }else {
   	    	 out.println("No Appointments available currently");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
