package net.javamaster.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Tag;

/**
 * @author Kirill
 * Класс отвечает за удаление тега по пути /tag/delete
 */
@WebServlet({ "/tag/delete" })
public class TagDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String slug = request.getParameter("tag");
		Tag tag = SQLClass.getTag(slug);
		String title = tag.getTitle();
		request.setAttribute("title", title);
		request.setAttribute("currentPage", "page/tag_delete.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public void deleteTag(String tagSlug) {
		SQLClass.deleteBoundsTagsWithPosts(tagSlug);
		SQLClass.deleteTag(tagSlug);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String slug = request.getParameter("tag");
			deleteTag(slug);
			response.sendRedirect("/tags");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
