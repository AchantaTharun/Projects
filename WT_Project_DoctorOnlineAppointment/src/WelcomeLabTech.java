

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
 * Servlet implementation class WelcomeLabTech
 */
@WebServlet("/WelcomeLabTech")
public class WelcomeLabTech extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeLabTech() {
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
	    PrintWriter out = response.getWriter();  
	    request.getRequestDispatcher("header_LabTech.html").include(request, response);  
	    HttpSession session=request.getSession(false);  
        
       
	 //   out.println("<br><a href='PatientProfile'>My Profile</a><br>");
	 //   ServletContext context=getServletContext();  
	//	context.setAttribute("n",n1);
	    if(session!=null){  
	    	String n=(String)session.getAttribute("name"); 
	    	 String name=request.getParameter("l_name"); 
		    out.print("Welcome "+n);  
		    out.println("<br>");
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select lID from labtech where lName='"+n+"'");
		String id="";
while(rs.next()) {
			
			
            id=rs.getString("lID");    
		}

		session.setAttribute("lid",id);
		//HttpSession session=request.getSession();  
       // session.setAttribute("uname",name); 
		out.println("<br><a href='" + getServletContext().getContextPath() + "/LabTechProfile'>My Profile</a><br>");
		//out.println("<br><a href='" + getServletContext().getContextPath() + "/BookAppointment.html'>Book Appointment</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/AppointmentsLabTech'>Appointments</a><br>");
		} catch (Exception e){
		      out.println(e);
		    }
	}else{
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
