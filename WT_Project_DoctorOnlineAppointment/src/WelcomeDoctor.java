

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WelcomeDoctor
 */
@WebServlet("/WelcomeDoctor")
public class WelcomeDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeDoctor() {
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
	    request.getRequestDispatcher("Doctorheader.html").include(request, response);  
	          String pword=request.getParameter("d_pword");
	    String name=request.getParameter("d_name");  
	    HttpSession session=request.getSession(false);  
       // session.setAttribute("dname",name); 
        
        if(session!=null){  
        	 String dname=(String)session.getAttribute("name");  
        	 session.setAttribute("pword",pword); 
	    out.print("Welcome "+dname);  
	    out.println("<br>");
	 //   out.println("<br><a href='PatientProfile'>My Profile</a><br>");
	//    ServletContext context=getServletContext();  
		//context.setAttribute("dn",name);
		out.println("<br><a href='" + getServletContext().getContextPath() + "/DoctorProfile'>My Profile</a><br>");
		//out.println("<br><a href='" + getServletContext().getContextPath() + "/BookAppointment'>Book Appointment</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/MyAppointments'>My Appointments</a><br>");
	}
	else{
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