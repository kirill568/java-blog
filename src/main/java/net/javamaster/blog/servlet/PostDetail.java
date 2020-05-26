package net.javamaster.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;

@WebServlet({ "/post/detail" })
public class PostDetail extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String slug = request.getParameter("post");
		Post post = SQLClass.getPost(slug);
		if ((slug == null) || (post == null)) {
			response.sendError(404, "Page not found");
		} else {
			request.setAttribute("post", post);
			request.setAttribute("currentPage", "page/post_detail.jsp");
			request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
		}
	}
}
