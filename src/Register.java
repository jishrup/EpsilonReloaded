

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterServlet
 */
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String DB_URL="jdbc:mysql://localhost/p";
	static final String USER="root";
	static final String PASSWORD="root";
	String				forwardUrl;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1= DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String insertStatement = "insert into userdetails (Name,UserName,Email,College,Password) values (?,?,?,?,?)";
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String college = request.getParameter("college");
			/*java.util.Date utilDate = new Date();
			// Convert it to java.sql.Date
			java.sql.Date date = new java.sql.Date(utilDate.getTime());*/
			PreparedStatement pst = conn1.prepareStatement(insertStatement);
			pst.setString(1, name);
			pst.setString(2, username);
			pst.setString(3, email);
			pst.setString(4, college);
			pst.setString(5, password);
			int result = pst.executeUpdate();
			conn1.close();
			if (result > 0) {
				flag= true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (flag == true) {
			forwardUrl = "/login.jsp";

		} else {
			forwardUrl = "/register.jsp";
		}
		

		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardUrl);
		rd.forward(request, response);

	}	}

	