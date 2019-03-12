

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpLabTech
 */
@WebServlet("/SignUpLabTech")
public class SignUpLabTech extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpLabTech() {
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
		 try{
		      String name = request.getParameter("l_name");
		      String pword = request.getParameter("l_pword");
		      String p1=request.getParameter("l_pword1");
		    //  String mail= request.getParameter("l_mail");
		   //   String age=request.getParameter("l_age");
		      String pno=request.getParameter("l_phone");
		      //String address=request.getParameter("l_address");
		    		 // String sex=request.getParameter("l_sex");
		    // int a=Integer.parseInt(age);
		  //  		  int a=0;
		    //		  int trigger=0;
		//     try{
		    	// a=Integer.parseInt(age);
		//    	}catch(NumberFormatException ex){ // handle your exception
		    	  //out.println("enter age");
		    //	  trigger=1;
		    //	}
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
		      ResultSet rs=stmt.executeQuery("select * from labtech where lName='"+name+"'");
		      String aname ="";
		      while(rs.next()) {
		    	  aname = rs.getString("lName");
		      }
		      int i=0;
		      if(name=="" || pword ==""  || pno==""  ) {
		    	  
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_LabTech.html");
					rd.include(request, response);
					out.println("<br>please fill all details correctly");
		      }
		      else if(pword.equals(p1)==false) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_LabTech.html");
					rd.include(request, response);
		    	  out.println("password does not match");
		      }
		     else if(name.equals(aname)) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_LabTech.html");
					rd.include(request, response);
		    	  out.println("<br>Technician name already taken");
		      }
		      else {
				i = stmt.executeUpdate("insert into labtech(lName, pword, phno) values('"+name+"', '"+pword+"','"+pno+"')");
		      
		      if(i!=0){
		        out.println("<br>Record has been inserted");
		        out.println("<br>Sign up success");
		        out.println("<a href='Login_LabTech.html'>Login as LabTech</a>");
		      }
		      else{
		        out.println("failed to insert the data");
		      }
		      }
		    }
		    catch (Exception e){
		      out.println(e);
		    }
	}

}
