package webapp.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6966089425557535912L;
	private UserValidationService service = new UserValidationService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if (service.isUserValid(name, password)) {
			request.setAttribute("name", name);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errorMessage", "Invalid Credential!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}
