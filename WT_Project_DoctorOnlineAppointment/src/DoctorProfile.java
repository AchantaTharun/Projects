

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DoctorProfile
 */
@WebServlet("/DoctorProfile")
public class DoctorProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorProfile() {
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
       // request.getRequestDispatcher("session_header.html").include(request, response);  
      //  ServletContext context=getServletContext();  
		//String name=(String)context.getAttribute("dn");
         // out.println(name);
        request.getRequestDispatcher("Doctorheader.html").include(request, response);  
        HttpSession session=request.getSession(false);  
        if(session!=null){  
        	String name=(String)session.getAttribute("name"); 
          try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	      String n="";
      	      
      	      ResultSet rs=stmt.executeQuery("select * from doctor where dName='"+name+"'");
      	      rs.next();
      	      n=rs.getString("dName");
      	      String mail=rs.getString("email");
      	    String sex=rs.getString("sex");
      	  String phone=rs.getString("phone");
      	String address=rs.getString("address");
      	String age=(String)rs.getString("age");
      	String hospital =rs.getString("hospital");
      	String spec=rs.getString("spec");
     	String aDays =rs.getString("aDays");
     	char d[]=aDays.toCharArray();
     	String days[]=new String[d.length];
     	for(int i=0;i<d.length;i++) {
     		if(d[i] =='0') {
     			days[i]="Monday";
     		}
     		else if(d[i] =='1') {
     			days[i]="Tuesday";
     		}
           else if(d[i] =='2') {
        	   days[i]="Wednesday";
     		}
           else if(d[i] =='3') {
        	   days[i]="Thursday";
    		}
           else if(d[i] =='4') {
        	   days[i]="Friday";
    		}
           else if(d[i] =='5') {
        	   days[i]="Saturday";
    		}
           else if(d[i] =='6') {
        	   days[i]="Sunday";
    		}
     		
     	}
      	      out.println("<br>Name = '"+n+"'<br>");
      	      out.println("<br>mail='"+mail+"'<br>");
      	      out.println("<br>gender = '"+sex+"'<br>");
      	      out.println("<br>phone no. ='"+phone+"'<br>");
      	      out.println("<br>address = '"+address+"'<br>");
      	      out.println("<br>age = '"+age+"'<br>");
      	    out.println("<br>hospital = '"+hospital+"'<br>");
      	  out.println("<br>spec = '"+spec+"'<br>");
      	  out.println("<br>Available Days :   ");
      	  for(int i=0;i<days.length;i++) {
      		out.println("'"+days[i]+"'  ,");
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
