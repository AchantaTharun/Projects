

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
 * Servlet implementation class SignUpDoctor
 */
@WebServlet("/SignUpDoctor")
public class SignUpDoctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpDoctor() {
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
		PrintWriter out=response.getWriter();
		 try{
		      String name = request.getParameter("d_name");
		      String pword = request.getParameter("d_pword");
		      String p1 = request.getParameter("d_pword1");
		      String mail= request.getParameter("d_mail");
		      String age=request.getParameter("d_age");
		      String pno=request.getParameter("d_phone");
		      String address=request.getParameter("d_address");
		    		  String sex=request.getParameter("sex");
		    		  String spec=request.getParameter("d_spec");
		    		  String hospital=request.getParameter("d_hospital");
		    		 String[] days=request.getParameterValues("aDays");
		    		 String aDays="";
		    		 for(int i=0;i<days.length;i++) {
		    			 aDays +=days[i];
		    		 }
		     int a=0;
		     int trigger=0;
		     try{
		    	 a=Integer.parseInt(age);
		    	}catch(NumberFormatException ex){ // handle your exception
		    	  //out.println("enter age");
		    	  trigger=1;
		    	}
		      Class.forName("com.mysql.jdbc.Driver");
		      Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/micro1","root","12345");
		      Statement stmt = con.createStatement();
		      ResultSet rs=stmt.executeQuery("select * from doctor where dName='"+name+"'");
		      String aname ="";
		      while(rs.next()) {
		    	  aname = rs.getString("dName");
		      }
		      int i=0;
		    
		      if(name=="" || pword =="" || mail=="" || age=="" || pno=="" || address =="" || sex=="" || spec=="" || hospital=="") {
		    	  
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Doctor.html");
					rd.include(request, response);
					out.println("<br>please fill all details correctly");
		      }
		      else if(pword.equals(p1)==false) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Patient.html");
					rd.include(request, response);
		    	  out.println("password does not match");
		      }
		      else if(trigger==1) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Doctor.html");
					rd.include(request, response);
		    	  out.println("<br>age invalid");
		      }
		      else if(name.equals(aname)) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Doctor.html");
					rd.include(request, response);
		    	  out.println("<br>Doctor name already taken");
		      }
		      else {
					 i = stmt.executeUpdate("insert into doctor(dName, pword, email, sex, phone, address, age,spec,hospital,aDays) values('"+name+"', '"+pword+"','"+mail+"','"+sex+"','"+pno+"','"+address+"','"+a+"','"+spec+"','"+hospital+"','"+aDays+"')");
		      
		      if(i!=0){
		        out.println("<br>Record has been inserted");
		        out.println("<br>Sign up success");
		        out.println("<a href='Login_Doctor.html'>Login as doctor</a>");
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
