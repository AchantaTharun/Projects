

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

@WebServlet("/SignUpPatient")
public class SignUpPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SignUpPatient() {
        super();    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		 try{
		      String name = request.getParameter("p_name");
		      String pword = request.getParameter("p_pword");
		      String p1=request.getParameter("p_pword1");
		      String mail= request.getParameter("p_mail");
		      String age=request.getParameter("p_age");
		      String pno=request.getParameter("p_phone");
		      String address=request.getParameter("p_address");
		    		  String sex=request.getParameter("p_sex");
		    // int a=Integer.parseInt(age);
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
		      ResultSet rs=stmt.executeQuery("select * from patient where pName='"+name+"'");
		      String aname ="";
		      while(rs.next()) {
		    	  aname = rs.getString("pName");
		      }
		      int i=0;
		      if(name=="" || pword =="" || mail=="" || age=="" || pno=="" || address =="" || sex=="") {
		    	  
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Patient.html");
					rd.include(request, response);
					out.println("<br>please fill all details correctly");
		      }
		      else if(pword.equals(p1)==false) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Patient.html");
					rd.include(request, response);
		    	  out.println("password does not match");
		      }
		      else if(trigger==1) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Patient.html");
					rd.include(request, response);
		    	  out.println("<br>age invalid");
		      }else if(name.equals(aname)) {
		    	  RequestDispatcher rd=request.getRequestDispatcher("Register_Patient.html");
					rd.include(request, response);
		    	  out.println("<br>Patient name already taken");
		      }
		      else {
				i = stmt.executeUpdate("insert into patient(pName, pword, email, sex, phone, address, age) values('"+name+"', '"+pword+"','"+mail+"','"+sex+"','"+pno+"','"+address+"','"+a+"')");
		      
		      if(i!=0){
		        out.println("<br>Record has been inserted");
		        out.println("<br>Sign up success");
		        out.println("<a href='Login_Patient.html'>Login as patient</a>");
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
