

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchDoctor
 */
@WebServlet("/SearchDoctor")
public class SearchDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDoctor() {
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
        
        request.getRequestDispatcher("header.html").include(request, response);  
    	HttpSession session=request.getSession(false);  
  //      ServletContext context=getServletContext();  
  //		String name=(String)context.getAttribute("n");
  String pid=(String)session.getAttribute("pid");
  	//	 context.setAttribute("pn",name);
  	
        String n1=(String)session.getAttribute("name"); 
     //   session.setAttribute("un",name); 
        String spec=request.getParameter("d_spec");
        
        try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	     // String n="";
      	    String[] days=request.getParameterValues("aDays");
   		 String aDays="";
   		 for(int i=0;i<days.length;i++) {
   			 aDays +=days[i];
   		 }
      	      ResultSet rs=stmt.executeQuery("select * from doctor where spec='"+spec+"'");
      	
      	     while(rs.next()) {
      	    	 out.println("name= '"+n1+"'<br>");
      	    String n =rs.getString("dName");
      		String address=rs.getString("address");
      		String hospital =rs.getString("hospital");
      		String did =rs.getString("dID");
      		String Days =rs.getString("aDays");
         	char d[]=Days.toCharArray();
         	String day[]=new String[d.length];
         	for(int i=0;i<d.length;i++) {
         		if(d[i] =='0') {
         			day[i]="Monday";
         		}
         		else if(d[i] =='1') {
         			day[i]="Tuesday";
         		}
               else if(d[i] =='2') {
            	   day[i]="Wednesday";
         		}
               else if(d[i] =='3') {
            	   day[i]="Thursday";
        		}
               else if(d[i] =='4') {
            	   day[i]="Friday";
        		}
               else if(d[i] =='5') {
            	   day[i]="Saturday";
        		}
               else if(d[i] =='6') {
            	   day[i]="Sunday";
        		}
         		
         	}
         	out.println("<br>ID = '"+did+"'<br>");
         	out.println("<br>Name = '"+n+"'<br>");
         	out.println("<br>address = '"+address+"'<br>");
         	out.println("<br>Available Days :   ");
        	  for(int i=0;i<day.length;i++) {
        		out.println("'"+day[i]+"'  ,");
        	  }
        	 
      //	out.println("<br><a href='" + getServletContext().getContextPath() + "/BookAppointment'>Book Appointment</a><br>");
        	//  RequestDispatcher rd = request.getRequestDispatcher("BookAppointment");
         	 //rd.forward(request, response);
        	 
      	     }
      	     out.print("<br><br><br>");
      	   out.print("<form action='BookAppointment' method='post'>");
      	  out.print("Enter Doctor id<input type='test' name='did'><br>");
      	out.print("Enter Doctor Name<input type='test' name='dname'><br>");
      	out.print("Select a day of appointment<select name='day'>");
      			out.print("<option value='0'>Monday</option>");
      					out.print("<option value='1'>Tuesday</option>");
      							out.print("<option value='2'>Wednesday</option>");
      									out.print("<option value='3'>Thursday</option>");
      											out.print("<option value='4'>Friday</option>");
      													out.print("<option value='5'>Saturday</option>");
      															out.print("<option value='4'>Sunday</option>");
      																	out.print("</select><br>");
      	 out.print("<input type='hidden' name='pid' value='"+pid+"'>");
      	 out.print("<input type='hidden' name='pname' value='"+n1+"'>");
      	 out.print("<input type='submit' value='Book'/>");
      	  
     	
        	  
        } catch (Exception e){
		      out.println(e);
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
