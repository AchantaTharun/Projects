

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
 * Servlet implementation class WelcomePatient
 */
@WebServlet("/WelcomePatient")
public class WelcomePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomePatient() {
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
	    PrintWriter out = response.getWriter();  
	    request.getRequestDispatcher("header.html").include(request, response);  
	    HttpSession session=request.getSession(false);  
        
       
	 //   out.println("<br><a href='PatientProfile'>My Profile</a><br>");
	 //   ServletContext context=getServletContext();  
	//	context.setAttribute("n",n1);
	    if(session!=null){  
	    	String n1=(String)session.getAttribute("name"); 
	    	 String name=request.getParameter("p_name"); 
		    out.print("Welcome "+n1);  
		    out.println("<br>");
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select pID from patient where pName='"+n1+"'");
		String id="";
while(rs.next()) {
			
			
            id=rs.getString("pID");    
		}

		session.setAttribute("pid",id);
		//HttpSession session=request.getSession();  
       // session.setAttribute("uname",name); 
		out.println("<br><a href='" + getServletContext().getContextPath() + "/PatientProfile'>My Profile</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/BookAppointment.html'>Book Appointment</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/MyAppointmentsPatient'>My Appointments</a><br>");
		} catch (Exception e){
		      out.println(e);
		    }
	}else{
		out.print("<h1>Please login first... </h1>");  
        request.getRequestDispatcher("Login_Patient.html").include(request, response);  	
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
