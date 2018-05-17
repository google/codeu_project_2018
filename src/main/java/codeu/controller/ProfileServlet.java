package codeu.controller;

import java.io.IOException;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/MyProfile")
public class ProfileServlet extends HttpServlet {

	private UserStore userStore;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws IOException, ServletException {
			request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
		}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		// get username
		String username = (String) request.getSession().getAttribute("user");

		User user = userStore.getUser(username);
		// user is not logged in, do not allow access  
		if (user == null) {
			response.sendRedirect("/MyProfile");
			return;
		}

	}

}
