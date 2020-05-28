package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Tag;

/**
 * @author Kirill
 * Класс отвечает за вывод всех тегов по пути /tags
 */
@WebServlet({ "/tags" })
public class TagsList extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tag> tags = SQLClass.getAllTags();
		request.setAttribute("tags", tags);
		request.setAttribute("currentPage", "page/tags_list.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}
}
