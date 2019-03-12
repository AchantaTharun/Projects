

import java.io.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class report
 */
@WebServlet("/report")
public class report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public report() {
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
        PrintWriter out=response.getWriter();
        String dname=request.getParameter("dname");
        String lname=request.getParameter("lname");
        String pid=request.getParameter("pid");
        String day=request.getParameter("day");
        String did=request.getParameter("did");
        InputStream inputStream = null;
        Part filePart = request.getPart("pdf");
        if (filePart != null) {
            // prints out some information for debugging
            //System.out.println(filePart.getName());
           // System.out.println(filePart.getSize());
          //  System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        try {
			Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
		     int i = stmt.executeUpdate("insert into appoint(pdf) values('"+inputStream+"') where PID='"+pid+"' and DID='"+did+"'") ;
		     if(i!=0) {
		    	 out.println("Booked");
		    	 stmt.executeUpdate("update appoint set state='"+1+"' where dName='"+dname+"' and PID='"+pid+"'") ;
		     }
		     else {
		    	 out.println("faileded");
		     }
		} catch (Exception e){
		      out.println(e);
		    }
	}

}
