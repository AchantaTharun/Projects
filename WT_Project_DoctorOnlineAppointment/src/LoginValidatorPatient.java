

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
 * Servlet implementation class LoginValidatorPatient
 */
@WebServlet("/LoginValidatorPatient")
public class LoginValidatorPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidatorPatient() {
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
		try {
		Class.forName("com.mysql.jdbc.Driver");
	      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
	      Statement stmt = con.createStatement();
		String name=request.getParameter("p_name");
		String pword =request.getParameter("p_pword");
		
		ResultSet rs=stmt.executeQuery("select pword from patient where pName='"+name+"'");
		//String n="";
		String p="";
	/*	if(rs.next()) {
			if(rs.getString(1)==name) {
			out.println("Login success");
			 RequestDispatcher rd=request.getRequestDispatcher("HomePage.html");
				rd.include(request, response);
			}
			else
				out.println("Login failed");
		}
		else {
			 RequestDispatcher rd=request.getRequestDispatcher("Login_Patient.html");
				rd.include(request, response);
				out.println("Wrong details");
		}
		*/
		
	
		while(rs.next()) {
			
			//n = rs.getString("pName");
            p= rs.getString("pword");
            
		}
		
		if(p!="") {
		 if (p.equals(pword)) {
			 
             //do something
             out.println("Login success");
             HttpSession session=request.getSession();  
 	        session.setAttribute("name",name.trim()); 
             RequestDispatcher rd=request.getRequestDispatcher("WelcomePatient");  
             rd.forward(request, response); 
             
         } else {
             //do something
        	 RequestDispatcher rd=request.getRequestDispatcher("Login_Patient.html");
				rd.include(request, response);
				out.println("Wrong details");
         }
		}else {
			 RequestDispatcher rd=request.getRequestDispatcher("Login_Patient.html");
				rd.include(request, response);
				out.println("Wrong details");
		}
	
		} catch (Exception e){
		      out.println(e);
		    }
		   
		
	}

}
