package net.javamaster.blog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javamaster.blog.dao.SQLClass;
import net.javamaster.blog.model.Post;
import net.javamaster.blog.model.Tag;

@WebServlet({ "/post/create" })
public class PostCreate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tag> tags = SQLClass.getAllTags();
		request.setAttribute("tags", tags);
		request.setAttribute("currentPage", "page/posts_create.jsp");
		request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
	}

	public String getDatePub() {
		Date dateNow = new Date();
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = formatForDateNow.format(dateNow);
		return date;
	}

	public void createPost(String title, String slug, String body, String[] tagsSlug) {
		String datePub = getDatePub();
		Post post = new Post(title, slug, body, datePub);
		SQLClass.insertPost(post);
		if (tagsSlug != null) {
			for (int i = 0; i < tagsSlug.length; i += 1) {
				String tagSlug = tagsSlug[i];
				SQLClass.makeBoundPostTag(slug, tagSlug);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");
			String slug = request.getParameter("slug");
			String body = request.getParameter("body");
			String[] tagsSlug = request.getParameterValues("tags");
			Boolean postExists = false;
			Post post = SQLClass.getPost(slug);
			if (post != null) {
				postExists = true;
			}
			if (Post.validate(title, slug, body, postExists)) {
				createPost(title, slug, body, tagsSlug);
				response.sendRedirect("/posts");
			} else {
				Map<String, ArrayList<String>> errors = Post.getErrors(title, slug, body, postExists);
				List<Tag> tags = SQLClass.getAllTags();
				request.setAttribute("tags", tags);
				request.setAttribute("errors", errors);
				request.setAttribute("title", title);
				request.setAttribute("slug", slug);
				request.setAttribute("body", body);
				request.setAttribute("currentPage", "page/posts_create.jsp");
				request.getRequestDispatcher("/WEB-INF/JSP/template.jsp").forward(request, response);
			}
	}
}
