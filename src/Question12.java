

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Question1
 */
public class Question12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String DB_URL="jdbc:mysql://localhost/p";
	static final String USER="root";
	static final String PASSWORD="root";
	String				forwardUrl;
    public Question12() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String answer = request.getParameter("answer");
			HttpSession session = request.getSession(false);
			String username=(String) session.getAttribute("username");
			if(answer.equals("e842795b282293fd61bc294c49edb12b"))
			{
				try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn1= DriverManager.getConnection(DB_URL, USER, PASSWORD);
				String insertStatement="UPDATE userdetails set level=?";
				insertStatement+=" WHERE UserName=?";
				PreparedStatement pst = conn1.prepareStatement(insertStatement);
				pst.setInt(1, 12);
				pst.setString(2, username);
				boolean rowUpdated = pst.executeUpdate() > 0;
				if (rowUpdated == false) {
					forwardUrl = "/q12.jsp";
				}
				else
				{
					forwardUrl = "/q13.jsp";
					}
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			}
			else
			{
				forwardUrl = "/q12.jsp";
			}
			
				
			
		

		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardUrl);
		rd.forward(request, response);

		}
}

