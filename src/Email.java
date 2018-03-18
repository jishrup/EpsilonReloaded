import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet")
public class Email extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String recipient = request.getParameter("email");
        String name = request.getParameter("name");
        String content = request.getParameter("message");
        String subject="Epsilon Reloaded";
        String message="Dear "+name+",\n"
        		+"\n"
        		+"Your query has been recorded.\n"
        		+"We'll get back to you soon.\n"
        		+"\n"
        		+"Team IEEE UVCE\n";
        String resultMessage = "";
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient,subject,
                    message);
            EmailUtility.sendEmail(host, port, user, pass, "jishnusm1997@gmail.com",subject,
                    content);
            EmailUtility.sendEmail(host, port, user, pass, "epsilonreloaded18@gmail.com",subject,
                    content);
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/Result.jsp").forward(
                    request, response);
        } finally {
        	String forwardUrl="/index.jsp";
        	RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardUrl);
    		rd.forward(request, response);
        }
    }
}