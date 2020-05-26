package net.javamaster.blog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Tag;

@WebServlet({ "/tag/create" })
public class TagCreate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("currentPage", "page/tag_create.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public void createTag(String title, String slug) {
		Tag tag = new Tag(title, slug);
		SQLClass.insertTag(tag);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String slug = request.getParameter("slug");
		Boolean tagExists = false;
		Tag tag = SQLClass.getTag(slug);
		if (tag != null) {
			tagExists = true;
		}
		if (Tag.validate(title, slug, tagExists)) {
			createTag(title, slug);
			response.sendRedirect("/tags");
		} else {
			Map<String, ArrayList<String>> errors = Tag.getErrors(title, slug, tagExists);
			request.setAttribute("title", title);
			request.setAttribute("slug", slug);
			request.setAttribute("errors", errors);
			request.setAttribute("currentPage", "page/tag_create.jsp");
			request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
		}
	}
}
