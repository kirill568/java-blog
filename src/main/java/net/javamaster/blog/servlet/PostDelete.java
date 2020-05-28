package net.javamaster.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;


/**
 * @author Kirill
 * Класс отвечает за удаление поста по пути /post/delete
 */
@WebServlet({ "/post/delete" })
public class PostDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String slug = request.getParameter("post");
		Post post = SQLClass.getPost(slug);
		String title = post.getTitle();
		request.setAttribute("slug", slug);
		request.setAttribute("title", title);
		request.setAttribute("currentPage", "page/post_delete.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public void deletePost(String postSlug) {
		SQLClass.deleteBoundsPostWithTags(postSlug);
		SQLClass.deletePost(postSlug);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String slug = request.getParameter("post");
		deletePost(slug);
		response.sendRedirect("/posts");
	}
}
