package codeu.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.time.Instant;
import codeu.model.data.User;
import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class ProfileServlet extends HttpServlet {

  /** Store class that gives access to Users. */
 	private UserStore userStore;

  /** Set up state for handling user requests. */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * This function fires when a user navigates to the profiles page. It gets the user's name from
   * the URL and then forwards to profile.jsp for rendering.
   */
 	@Override
 	public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
 	  String requestUrl = request.getRequestURI();
 	  String username = requestUrl.substring("/users/".length());
 		request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
	}

  /**
   * This function fires when user submits the form on the profiles page. It gets the user's username
   * from the session, assigns that username to the user, collects the about me content, and then
   * redirects back to the profile page with the displayed about me information.
  */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		  throws IOException, ServletException {

	  String username = (String)request.getSession().getAttribute("user");
	  User user = userStore.getUser(username);
	  String aboutMeContent = request.getParameter("About me");

	  response.sendRedirect("/users/" + username);
 	}
}
