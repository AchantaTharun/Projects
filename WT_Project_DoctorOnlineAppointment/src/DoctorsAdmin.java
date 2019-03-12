

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

/**
 * Servlet implementation class DoctorsAdmin
 */
@WebServlet("/DoctorsAdmin")
public class DoctorsAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorsAdmin() {
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
        try {
      		Class.forName("com.mysql.jdbc.Driver");
      	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
      	      Statement stmt = con.createStatement();
      	    ResultSet rs=stmt.executeQuery("select * from doctor");
      	  while(rs.next()) {
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
          	out.println("<br>Hospital=  '"+hospital+"'<br>");
          	out.println("<br>Available Days :   ");
          	
         	  for(int i=0;i<day.length;i++) {
         		out.println("'"+day[i]+"'  ,");
         	  }
         	 out.println("<br><br>");
         	 
      	  }
      	 out.print("<form action='AdminDoctor' method='post'>");
     	  out.print("Enter Doctor id<input type='test' name='did'><br>");
     	out.print("Enter Doctor Name<input type='test' name='dname'><br>");
     	out.print("<input type='submit' value='Delete Doctor'/>");
      	  
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
