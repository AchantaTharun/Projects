

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidatorAdmin
 */
@WebServlet("/LoginValidatorAdmin")
public class LoginValidatorAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidatorAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("a_name");
		String pword=request.getParameter("a_pword");
		if(name.equals("admin") && pword.equals("123")) {
			 RequestDispatcher rd=request.getRequestDispatcher("WelcomeAdmin");  
             rd.forward(request, response); 
		}
		else {
            //do something
       	 RequestDispatcher rd=request.getRequestDispatcher("Login_Admin.html");
				rd.include(request, response);
				out.println("Wrong details");
        }
	}

}
