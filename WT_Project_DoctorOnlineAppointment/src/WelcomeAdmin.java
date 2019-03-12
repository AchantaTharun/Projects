

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeAdmin
 */
@WebServlet("/WelcomeAdmin")
public class WelcomeAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeAdmin() {
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
	    PrintWriter out = response.getWriter();  
		out.println("<br><a href='" + getServletContext().getContextPath() + "/DoctorsAdmin'>Doctors</a><br>");
	//    out.print("<form action='DoctorsAdmin' method='post'>");
     //	 out.print("<input type='submit' value='Doctors'/><br>" );
		out.println("<br><a href='" + getServletContext().getContextPath() + "/PatientsAdmin'>Patients</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/AppointmentsAdmin'>Appointments</a><br>");
		out.println("<br><a href='" + getServletContext().getContextPath() + "/LabtechsAdmin'>Lab Technicians</a><br>");
		
	}

}
