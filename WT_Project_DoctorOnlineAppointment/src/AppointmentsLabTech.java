

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
 * Servlet implementation class AppointmentsLabTech
 */
@WebServlet("/AppointmentsLabTech")
public class AppointmentsLabTech extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentsLabTech() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("header_LabTech.html").include(request, response);  
		HttpSession session=request.getSession(false);  
		 if(session!=null){  
        String n1=(String)session.getAttribute("name"); 
        String p=(String)session.getAttribute("pword"); 
        //out.println(n1+" "+p);
        
        try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	      ResultSet rs1=stmt.executeQuery("select * from appoint");
      	    if(rs1.next()) {
      	    	 ResultSet rs=stmt.executeQuery("select * from appoint");
      	     while(rs.next()) {
      	    	 String pname=rs.getString("Pname");
      	    	String dname=rs.getString("Dname");
      	    	String did=rs.getString("DID");
      	    	String pid=rs.getString("PID");
      	    	String day=rs.getString("day");
      	    	String a=rs.getString("advise");
      	    	 String days="";
      	    	 out.println("<br>patient ='"+pname+"'");
      	    	out.println("<br>patient id ='"+pid+"'");
      	    	 out.println("<br>doctor ='"+dname+"'");
       	    	out.println("<br>doctor id ='"+did+"'");
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
      	    	
      	    	out.println("<br>advise ='"+a+"'");
      	    	out.println("<hr>");
      	    	 
      	     }
      	   out.print("<br><br><br>");
      	   out.print("<form action='report' method='post'>");
      	  out.print("Enter Doctor id<input type='test' name='did'><br>");
      	out.print("Enter Doctor Name<input type='test' name='dname'><br>");
      	 out.print("Enter Patient id<input type='test' name='pid'><br>");
      	out.print("Select a day of appointment<select name='day'>");
      			out.print("<option value='0'>Monday</option>");
      					out.print("<option value='1'>Tuesday</option>");
      							out.print("<option value='2'>Wednesday</option>");
      									out.print("<option value='3'>Thursday</option>");
      											out.print("<option value='4'>Friday</option>");
      													out.print("<option value='5'>Saturday</option>");
      															out.print("<option value='4'>Sunday</option>");
      																	out.print("</select><br>");
      																	out.print("<input type='file' name='pdf'size='100'>");
      	 out.print("<input type='hidden' name='lname' value='"+n1+"'>");
      	 out.print("<input type='submit' value='upload'/>");
      	  }else {
   	    	 out.println("No Appointments available currently");
   	     }
      	     
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
