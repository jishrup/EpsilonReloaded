import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final String DB_URL="jdbc:mysql://localhost/p";
	static final String USER="root";
	static final String PASSWORD="root";
	String				forwardUrl;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn1= DriverManager.getConnection(DB_URL, USER, PASSWORD);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String insertStatement = "select * from userdetails where UserName=? AND Password=?";
			PreparedStatement pst = conn1.prepareStatement(insertStatement);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs == null) {
				forwardUrl = "/register.jsp";
			}
			else {
				int level;
				startSession(request, response,username);
				while(rs.next())
				{
					level=rs.getInt(6);
					switch(level)
					{
					case 0:forwardUrl = "/q1.jsp";break;
					case 1:forwardUrl = "/q2.jsp";break;
					case 2:forwardUrl = "/q3.jsp";break;
					case 3:forwardUrl = "/q4.jsp";break;
					case 4:forwardUrl = "/q5.jsp";break;
					case 5:forwardUrl = "/q6.jsp";break;
					case 6:forwardUrl = "/q7.jsp";break;
					case 7:forwardUrl = "/q8.jsp";break;
					case 8:forwardUrl = "/q9.jsp";break;
					case 9:forwardUrl = "/q10.jsp";break;
					case 10:forwardUrl = "/q11.jsp";break;
					case 11:forwardUrl = "/q12.jsp";break;
					case 12:forwardUrl = "/q13.jsp";break;
					case 13:forwardUrl = "/q14.jsp";break;
					case 14:forwardUrl = "/q15.jsp";break;
					case 15:forwardUrl = "/leaderboard.jsp";break;
					default:forwardUrl ="/register.jsp";break;
					}		
				
				
			}}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardUrl);
		rd.forward(request, response);

		}
	private void startSession(HttpServletRequest request, HttpServletResponse response,String username) {

		HttpSession session = request.getSession();

		// Adding the username to the session.
		session.setAttribute("username", username);
	}
}
	