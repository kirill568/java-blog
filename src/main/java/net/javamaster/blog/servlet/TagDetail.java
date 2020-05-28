package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;
import net.javamaster.blog.model.Tag;

/**
 * @author Kirill
 * Класс отвечает за вывод связанных постов с тегом по пути /tag/detail
 */
@WebServlet({ "/tag/detail" })
public class TagDetail extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String slug = request.getParameter("tag");
		Tag tag = SQLClass.getTag(slug);
		if (slug == null || tag == null) {
			response.sendError(404, "Page not found");
		} else {
			List<Post> posts = SQLClass.getPostsByTag(slug);
			request.setAttribute("posts", posts);
			request.setAttribute("tag", tag);
			request.setAttribute("currentPage", "page/tag_detail.jsp");
			request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
		}
	}
}
